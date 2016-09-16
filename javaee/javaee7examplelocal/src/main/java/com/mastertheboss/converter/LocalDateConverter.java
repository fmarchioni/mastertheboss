package com.mastertheboss.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
 

@SuppressWarnings("UnusedDeclaration")
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<java.time.LocalDate, java.sql.Date> {
 

    @Override
    public java.sql.Date convertToDatabaseColumn(java.time.LocalDate attribute) {
       
        return attribute == null ? null : java.sql.Date.valueOf(attribute);
    }

    @Override
    public java.time.LocalDate convertToEntityAttribute(java.sql.Date dbData) {
     
        return dbData == null ? null : dbData.toLocalDate();
    }
}