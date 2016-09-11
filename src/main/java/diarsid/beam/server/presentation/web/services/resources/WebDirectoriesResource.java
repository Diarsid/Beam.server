/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.services.resources;

import java.util.List;
import java.util.Optional;

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

import diarsid.beam.server.domain.entities.WebPlacement;
import diarsid.beam.server.domain.services.users.UsersService;
import diarsid.beam.server.domain.services.webobjects.UserWebObjectsService;
import diarsid.beam.server.presentation.web.json.dto.JsonPayload;
import diarsid.beam.server.presentation.web.json.dto.entities.JsonWebDirectory;
import diarsid.beam.server.presentation.web.json.util.JavaObjectToJsonConverter;

import static java.util.stream.Collectors.toList;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.ok;

import static diarsid.beam.server.domain.entities.WebPlacement.valueOfIgnoreCase;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.composeResponseFrom;

/**
 *
 * @author Diarsid
 */

@Component
@Path("/users/{id}/{place}")
public class WebDirectoriesResource {
    
    private static final Logger logger = LoggerFactory.getLogger(WebDirectoriesResource.class);
    
    private final UserWebObjectsService webObjects;
    private final UsersService users;
    private final JavaObjectToJsonConverter toJsonConverter;
    
    public WebDirectoriesResource(
            UserWebObjectsService service, 
            UsersService users, 
            JavaObjectToJsonConverter toJsonConverter) {
        this.webObjects = service;
        this.users = users;
        this.toJsonConverter = toJsonConverter;
    }
    
    @GET
    @Path("/directories")
    @Produces(APPLICATION_JSON)
    public Response getDirectories(
            @PathParam("id") int userId, 
            @PathParam("place") String place) {
        Optional<WebPlacement> placement = valueOfIgnoreCase(place);
        users.checkUser(userId);
        if ( placement.isPresent() ) {
            List<JsonWebDirectory> dirs = this.webObjects
                    .getUserWebDirectoriesInPlace(userId, placement.get())
                    .stream()
                    .map(persistableDir -> new JsonWebDirectory(persistableDir))
                    .collect(toList());
            return ok(this.toJsonConverter.jsonizeToString(dirs), APPLICATION_JSON).build();
        } else {
            return composeResponseFrom(SC_BAD_REQUEST, place + " is invalid placement name.");
        }        
    }
    
    @POST
    @Path("/directories")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response createDirectory(
            @PathParam("id") int userId, 
            @PathParam("place") String place,
            JsonPayload payload) {
        Optional<WebPlacement> placement = valueOfIgnoreCase(place);
        boolean created = false;
        if ( placement.isPresent() ) {   
            logger.info("Create dir -> " +
                    "user:" + userId + 
                    ", place:"+ placement.get().name() + 
                    ", name:" + payload.get());
            created = this.webObjects.createUserWebDirectory(userId, placement.get(), payload.get());
            if ( created ) {
                logger.info("...dir created.");
                return Response.ok().build();
            } else {
                logger.info("...dir creation fails.");
                return composeResponseFrom(
                        SC_INTERNAL_SERVER_ERROR, 
                        "Directory creation fails due to unknown reason.");
            }
        } else {
            return composeResponseFrom(SC_BAD_REQUEST, place + " is invalid placement name.");
        }        
    }
}
