package br.com.condoux.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SimNao {

    SIM(1, "Sim"),
    NAO(0, "Não");

    @Getter
    private Integer value;

    @Getter
    private String label;

    public static SimNao valueof(Integer value) {
        if (value == 1)
            return SIM;
        else if (value == 0)
            return NAO;

        throw new RuntimeException("Invalid Enum Value -> " + value);
    }

    public static SimNao valueof(Boolean value) {
        if (value == true)
            return SIM;
        else
            return NAO;
    }

    public boolean getBooleanValue() {
        if (getValue() == 1)
            return true;
        return false;
    }
}

