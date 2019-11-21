package br.com.condoux.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoEnvio {

    SIMPLE(1, "Simples"),
    TWOWAY(2, "com Resposta"),
    VOICE(3, "Voz");

    @Getter
    private Integer value;

    @Getter
    private String label;

    public static TipoEnvio valueof(Integer value) {
        for (TipoEnvio tipo : TipoEnvio.values()) {
            if (tipo.getValue().equals(value))
                return tipo;
        }

        throw new RuntimeException("Invalid Enum Value -> " + value);
    }

}
