package br.com.condoux.model.entities;

import br.com.condoux.model.entities.converter.TipoEnvioConverter;
import br.com.condoux.model.enums.TipoEnvio;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "trafego_sms")
@Data
public class TrafegoSms implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cd_trafego_sms")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dc_from")
    private String from;

    @Column(name = "dc_to")
    private String to;

    @Column(name = "dc_msg")
    private String msg;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_recebimento")
    private Date dataRecebimento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_confirmacao")
    private Date dataConfirmacao;

    @Column(name = "dc_msg_resposta")
    private String msgResposta;

    @Column(name = "dc_msg_confirmacao")
    private String msgConfirmacao;

    @Column(name = "cd_sms")
    private String codigoSms;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_solicitante")
    private Solicitante solicitante;

    @Convert(converter = TipoEnvioConverter.class)
    @Column(name = "id_tipo_envio")
    private TipoEnvio tipoEnvio;

    @Column(name = "dc_resposta_twoway")
    private String respostaTwoWay;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_resposta_twoway")
    private Date dataRespostaTwoWay;


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TrafegoSms other = (TrafegoSms) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
