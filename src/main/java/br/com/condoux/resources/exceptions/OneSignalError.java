package br.com.condoux.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OneSignalError {

    private Integer status;
    private String msg;
    private String oneSignalMessage;
    private Long timestamp;
}
