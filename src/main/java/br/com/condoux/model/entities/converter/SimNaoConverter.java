package br.com.condoux.model.entities.converter;

import br.com.condoux.model.enums.SimNao;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class SimNaoConverter implements AttributeConverter<SimNao, Integer> {

    public Integer convertToDatabaseColumn(SimNao value) {
        if (value == null)
            return SimNao.NAO.getValue();
        return value.getValue();
    }

    public SimNao convertToEntityAttribute(Integer value) {
        if (value == null)
            return SimNao.NAO;
        return SimNao.valueof(value.intValue());
    }

}
