package br.com.condoux.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class Notification {

    @JsonProperty("device_ids")
    @ApiModelProperty(notes = "Identificador de dispositivo celular")
    @ApiParam(name = "Identificador de dispositivo celular")
    private String[] ids;

    @JsonProperty("condominio")
    @ApiModelProperty(notes = "Código do condomínio")
    private Integer codCondominio;

    @JsonProperty("message")
    @ApiModelProperty(notes = "Mensagem a ser enviada")
    private String message;

}
