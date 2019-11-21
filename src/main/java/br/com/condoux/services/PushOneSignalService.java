package br.com.condoux.services;


import br.com.condoux.model.entities.Solicitante;
import br.com.condoux.model.repositories.SolicitanteRepository;
import br.com.condoux.payload.ContentMessage;
import br.com.condoux.payload.DataInformation;
import br.com.condoux.payload.MessageOneSignal;
import br.com.condoux.payload.Notification;
import br.com.condoux.services.exceptions.ObjectNotFoundException;
import br.com.condoux.services.exceptions.OneSignalException;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
import java.util.Scanner;

@Service
@Data
public class PushOneSignalService {

    private final static Logger log = LoggerFactory.getLogger(PushOneSignalService.class);

    @Setter
    @Getter
    @Value("${push.addressOne}")
    private String addressOne;

    private Optional<Solicitante> solicitante;


    @Autowired
    private SolicitanteRepository repository;

    public void enviaNotificacao(Notification notification) {

        this.solicitante = repository.findById(notification.getCodCondominio());

        if (!this.solicitante.isPresent()) {
            throw new ObjectNotFoundException("Solicitante não encontrado Id: " + notification.getCodCondominio());
        }

        try {

            HttpURLConnection conn = this.createConn();
            this.sendPush("", conn, notification.getMessage(), notification.getIds());
            int response = conn.getResponseCode();

            if (HttpStatus.BAD_REQUEST.value() == response) {
                throw new OneSignalException("Não foi possível enviar a notificação;" + mountResponse(conn, response));
            }

            if (response >= HttpURLConnection.HTTP_OK) {
                log.info(mountResponse(conn, response));

            }

        } catch (Exception e) {
            throw new OneSignalException(e.getMessage());
        }


    }

    private HttpURLConnection createConn() throws IOException {
        URL url = new URL(this.addressOne);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setUseCaches(false);
        con.setDoOutput(true);
        con.setDoInput(true);

        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("Authorization", this.solicitante.get().getKeyFireSend());
        con.setRequestMethod("POST");
        return con;
    }

    private void sendPush(final String title,
                          final HttpURLConnection conn,
                          final String description,
                          final String[] ids) throws IOException {

        MessageOneSignal message = MessageOneSignal.builder()
                .appId(this.solicitante.get().getKeyOneSignal())
                .includePlayerIds(ids)
                .data(DataInformation.builder()
                        .build())
                .headings(ContentMessage.builder()
                        .pt(title)
                        .en(title)
                        .build())
                .contents(ContentMessage.builder()
                        .pt(description)
                        .en(description)
                        .build())
                .build();

        Gson builderUnderscore = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        byte[] bytes = builderUnderscore.toJson(message).getBytes("UTF-8");
        conn.setFixedLengthStreamingMode(bytes.length);

        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(bytes);
    }

    private String mountResponse(HttpURLConnection con, int httpResponse) throws IOException {
        String jsonResponse;
        if (httpResponse >= HttpURLConnection.HTTP_OK
                && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
            Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
            scanner.close();
        } else {
            Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
            scanner.close();
        }
        return jsonResponse;
    }

}
