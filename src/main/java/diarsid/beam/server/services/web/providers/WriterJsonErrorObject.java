/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.web.providers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import diarsid.beam.server.util.JavaObjectToJsonConverter;
import diarsid.beam.server.util.JsonErrorObject;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 *
 * @author Diarsid
 */

@Provider
@Produces(APPLICATION_JSON)
@Component
public class WriterJsonErrorObject implements MessageBodyWriter<JsonErrorObject> {
    
    private final JavaObjectToJsonConverter converter;
    
    public WriterJsonErrorObject(JavaObjectToJsonConverter converter) {
        this.converter = converter;
    }
    
    @Override
    public boolean isWriteable(
            Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        
        return type.equals(JsonErrorObject.class);
    }

    @Override
    public long getSize(
            JsonErrorObject t, 
            Class<?> type, 
            Type genericType, 
            Annotation[] annotations, 
            MediaType mediaType) {
        
        return -1;
    }

    @Override
    public void writeTo(
            JsonErrorObject target,
            Class<?> type, 
            Type genericType, 
            Annotation[] annotations,
            MediaType mediaType, 
            MultivaluedMap<String, Object> httpHeaders,
            OutputStream entityStream) 
                    throws IOException, WebApplicationException {
        
        entityStream.write(this.converter.jsonizeToBytes(target));
    }
}
