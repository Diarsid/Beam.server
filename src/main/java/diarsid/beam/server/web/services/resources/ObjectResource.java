/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.web.services.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import diarsid.beam.server.data.ObjectDataServiceWorker;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import static diarsid.beam.server.web.services.auth.InnerHttpRequestUserHeaders.BEAM_USER_ID;
import static diarsid.beam.server.web.services.auth.InnerHttpRequestUserHeaders.BEAM_USER_NICKNAME;
import static diarsid.beam.server.web.services.auth.InnerHttpRequestUserHeaders.BEAM_USER_ROLE;

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
    
    private final ObjectDataServiceWorker provider;
    
    public ObjectResource(ObjectDataServiceWorker provider) {
        LOGGER.info("created.");
        this.provider = provider;
    }
    
    
    @GET 
    @Path("/object")
    @Produces(APPLICATION_JSON)
    public Response getObject(@Context ContainerRequestContext request) {
        MultivaluedMap<String, String> headers = request.getHeaders();
        LOGGER.info("request from user: "); 
        LOGGER.info("id: " + headers.getFirst(BEAM_USER_ID.getHeader())); 
        LOGGER.info("nick: " + headers.getFirst(BEAM_USER_NICKNAME.getHeader())); 
        LOGGER.info("role: " + headers.getFirst(BEAM_USER_ROLE.getHeader())); 
        return Response.ok(this.provider.newObjectData(), APPLICATION_JSON).build();
    }    
}
