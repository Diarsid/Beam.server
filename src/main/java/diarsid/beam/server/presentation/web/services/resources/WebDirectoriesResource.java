/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.services.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import diarsid.beam.server.domain.services.webobjects.UserWebObjectsService;
import diarsid.beam.server.presentation.web.json.dto.JsonPayload;
import diarsid.beam.server.presentation.web.json.dto.JsonWebDirectory;
import diarsid.beam.server.presentation.web.json.util.JavaObjectToJsonConverter;
import diarsid.beam.server.presentation.web.services.filters.bindings.AuthenticationRequired;

import static java.util.stream.Collectors.toList;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;

import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;

import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;

import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;

import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;


import static javax.ws.rs.core.Response.ok;

import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;

import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;

import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;

import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;

import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;

import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.ok;

/**
 *
 * @author Diarsid
 */

@Component
@Path("/users/{id}/{place}")
@AuthenticationRequired
public class WebDirectoriesResource {
    
    private static final Logger logger = LoggerFactory.getLogger(WebDirectoriesResource.class);
    
    private final UserWebObjectsService webObjects;
    private final JavaObjectToJsonConverter toJsonConverter;
    
    public WebDirectoriesResource(
            UserWebObjectsService service,
            JavaObjectToJsonConverter toJsonConverter) {
        this.webObjects = service;
        this.toJsonConverter = toJsonConverter;
    }
    
    @GET
    @Path("/directories")
    @Produces(APPLICATION_JSON)
    public Response getDirectories(
            @PathParam("id") int userId, 
            @PathParam("place") String place) {
        List<JsonWebDirectory> dirs = this.webObjects
                .getUserWebDirectoriesInPlace(userId, place)
                .stream()
                .map(persistableDir -> new JsonWebDirectory(persistableDir))
                .collect(toList());
        return ok(this.toJsonConverter.jsonizeToString(dirs), APPLICATION_JSON).build();        
    }
    
    @POST
    @Path("/directories")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response createDirectory(
            @PathParam("id") int userId, 
            @PathParam("place") String place,
            JsonPayload payload) {
        boolean created = false;
        logger.info("Create dir -> " +
                "user:" + userId + 
                ", place:"+ place + 
                ", name:" + payload.get());
        created = this.webObjects.createUserWebDirectory(userId, place, payload.get());
        if ( created ) {
            logger.info("...dir created.");
            return Response.ok().build();
        } else {
            logger.info("...dir creation fails.");
            return jsonResponseWith(
                    SC_INTERNAL_SERVER_ERROR, 
                    "Directory creation fails due to unknown reason.");
        } 
    }
}
