/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.resources.objects;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import diarsid.beam.server.data.ObjectDataProvider;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 *
 * @author Diarsid
 */

@Component
@Path("/objects")
public class ObjectResource {
    
    private final static Logger LOGGER;
    static {
        LOGGER = LoggerFactory.getLogger(ObjectResource.class);
    }
    
    private final ObjectDataProvider provider;
    
    public ObjectResource(ObjectDataProvider provider) {
        LOGGER.info("created.");
        this.provider = provider;
    }
    
    
    @GET 
    @Path("/object")
    @Produces(APPLICATION_JSON)
    public Response getObject() {
        return Response.ok(this.provider.newObjectData(), APPLICATION_JSON).build();
    }    
}
