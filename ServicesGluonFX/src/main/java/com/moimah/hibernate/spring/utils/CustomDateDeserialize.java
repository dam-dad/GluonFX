package com.moimah.hibernate.spring.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;


/**
 * Clase de deserializador personalizado de fecha
 * se aplica la anotacion a las entidades, en los atributos
 * que reciban fecha: @JsonDeserialize(using= CustomDateDeserialize.class)	
 * 
 * 
 * @author moimah
 *
 */
public class CustomDateDeserialize extends JsonDeserializer<Date> {


	//Atributo que se le pasa el patron de formato de fecha
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
            

    /**
     * Metodo que realiza la deserializacion al formato especificado anteriorment
     */
    @Override
    public Date deserialize(JsonParser paramJsonParser,
            DeserializationContext paramDeserializationContext)
            throws IOException, JsonProcessingException {
        String str = paramJsonParser.getText().trim();
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            // Handle exception here
        }
        return paramDeserializationContext.parseDate(str);
    }
}
