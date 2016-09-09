/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.services.resources;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import diarsid.beam.server.domain.entities.WebPlacement;
import diarsid.beam.server.domain.services.users.UsersService;
import diarsid.beam.server.domain.services.webobjects.UserWebObjectsService;
import diarsid.beam.server.presentation.web.json.dto.entities.JsonWebDirectory;
import diarsid.beam.server.presentation.web.json.util.JavaObjectToJsonConverter;

import static java.lang.Integer.valueOf;
import static java.util.stream.Collectors.toList;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.ok;

import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.composeResponseFrom;

/**
 *
 * @author Diarsid
 */

@Component
@Path("/users/{id}/{place}")
public class WebDirectoriesResource {
    
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
            @PathParam("id") String userIdString, 
            @PathParam("place") String place) {
        Optional<WebPlacement> placement = WebPlacement.valueOfIgnoreCase(place);
        users.checkUser(valueOf(userIdString));
        if ( placement.isPresent() ) {
            List<JsonWebDirectory> dirs = this.webObjects
                    .getUserWebDirectoriesInPlace(valueOf(userIdString), placement.get())
                    .stream()
                    .map(persistableDir -> new JsonWebDirectory(persistableDir))
                    .collect(toList());
            return ok(this.toJsonConverter.jsonizeToString(dirs), APPLICATION_JSON).build();
        } else {
            return composeResponseFrom(SC_BAD_REQUEST, place + " is invalid placement name.");
        }        
    }
}
