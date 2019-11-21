package br.com.condoux.services.exceptions;

public class OneSignalException extends RuntimeException {

    public OneSignalException(String msg) {
        super(msg);
    }

    public OneSignalException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
