/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.services.providers;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import diarsid.beam.server.presentation.web.json.dto.JsonUserLogin;
import diarsid.beam.server.presentation.web.json.util.JsonToJavaObjectConverter;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 *
 * @author Diarsid
 */

@Component
@Provider
@Consumes(APPLICATION_JSON)
public class ReaderJsonUserLogin implements MessageBodyReader<JsonUserLogin> {
    
    private final JsonToJavaObjectConverter converter;
    
    public ReaderJsonUserLogin(JsonToJavaObjectConverter converter) {
        this.converter = converter;
    }
    
    @Override
    public boolean isReadable(
            Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        
        return type.equals(JsonUserLogin.class);
    }

    @Override
    public JsonUserLogin readFrom(
            Class<JsonUserLogin> type, 
            Type genericType, 
            Annotation[] annotations, 
            MediaType mediaType, 
            MultivaluedMap<String, String> httpHeaders, 
            InputStream entityStream)
            throws IOException, WebApplicationException {
        
        return (JsonUserLogin) this.converter
                .objectivize(entityStream, JsonUserLogin.class);
    }
}
