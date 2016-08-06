/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.web.services.jaxrsproviders;

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

import diarsid.beam.server.data.entities.ObjectData;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import static diarsid.beam.server.util.GsonJsonConverter.jsonizeToBytes;

/**
 *
 * @author Diarsid
 */

@Provider
@Produces(APPLICATION_JSON)
public class ObjectDataJAXRSWriter implements MessageBodyWriter<ObjectData> {
    
    public ObjectDataJAXRSWriter() {
    }

    @Override
    public boolean isWriteable(
            Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        
        return type.equals(ObjectData.class);
    }

    @Override
    public long getSize(
            ObjectData t, 
            Class<?> type, 
            Type genericType, 
            Annotation[] annotations, 
            MediaType mediaType) {
        
        return -1;
    }

    @Override
    public void writeTo(
            ObjectData target,
            Class<?> type, 
            Type genericType, 
            Annotation[] annotations,
            MediaType mediaType, 
            MultivaluedMap<String, Object> httpHeaders,
            OutputStream entityStream) 
                    throws IOException, WebApplicationException {
        
        entityStream.write(jsonizeToBytes(target));
    }
}
