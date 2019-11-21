package br.com.condoux.resources.exceptions;

import br.com.condoux.services.exceptions.ObjectNotFoundException;
import br.com.condoux.services.exceptions.OneSignalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(OneSignalException.class)
    public ResponseEntity<OneSignalError> oneSignalException(OneSignalException e, HttpServletRequest request) {
        String[] message = e.getMessage().split(";");
        OneSignalError err = new OneSignalError(HttpStatus.BAD_REQUEST.value(), message[0], message[1], System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}
