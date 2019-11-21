package br.com.condoux.model.entities.converter;

import br.com.condoux.model.enums.TipoEnvio;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TipoEnvioConverter implements AttributeConverter<TipoEnvio, Integer> {

    public Integer convertToDatabaseColumn(TipoEnvio value) {
        if (value == null)
            return null;
        return value.getValue();
    }

    public TipoEnvio convertToEntityAttribute(Integer value) {
        if (value == null)
            return null;
        return TipoEnvio.valueof(value.intValue());
    }

}
