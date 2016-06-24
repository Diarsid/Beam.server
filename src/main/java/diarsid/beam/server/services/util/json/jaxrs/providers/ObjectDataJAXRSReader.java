/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.util.json.jaxrs.providers;

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

import diarsid.beam.server.entities.ObjectData;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import static diarsid.beam.server.util.GsonJsonConverter.objectivizeObjectData;

/**
 *
 * @author Diarsid
 */

@Provider
@Consumes(APPLICATION_JSON)
class ObjectDataJAXRSReader implements MessageBodyReader<ObjectData> {
    
    ObjectDataJAXRSReader() {
    }

    @Override
    public boolean isReadable(
            Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        
        return type.equals(ObjectData.class);
    }

    @Override
    public ObjectData readFrom(
            Class<ObjectData> type, 
            Type genericType, 
            Annotation[] annotations, 
            MediaType mediaType, 
            MultivaluedMap<String, String> httpHeaders, 
            InputStream entityStream)
            throws IOException, WebApplicationException {
        
        return objectivizeObjectData(entityStream);
    }
    
    
}
