package br.com.condoux.resources.notifications;


import br.com.condoux.payload.Notification;
import br.com.condoux.services.PushOneSignalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
@ApiOperation(value = "Envia notificação", notes = "Envia notificação", response = ResponseEntity.class)
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Notificação enviada com sucesso"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
public class NotificacaoResource {

    @Autowired
    private PushOneSignalService service;

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity sendMessage(@RequestBody Notification notification) {
        service.enviaNotificacao(notification);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
