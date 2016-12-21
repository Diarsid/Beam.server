/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.services.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
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
import diarsid.beam.server.presentation.web.services.JAXRSAutoRegistrableComponent;
import diarsid.beam.server.presentation.web.services.filters.bindings.AuthenticationRequired;

import static java.lang.Integer.valueOf;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import static diarsid.beam.server.domain.entities.WebPlacement.placementOf;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.okJsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.okResponse;

/**
 *
 * @author Diarsid
 */

@Path("/users/{id}/{place}/directories/{name}")
@Component
@AuthenticationRequired
public class SingleWebDirectoryResource implements JAXRSAutoRegistrableComponent {
    
    private static final Logger logger = LoggerFactory.getLogger(SingleWebDirectoryResource.class);
    
    private final UserWebObjectsService webObjects;
    private final JavaObjectToJsonConverter toJsonConverter;
    
    public SingleWebDirectoryResource(
            UserWebObjectsService webObjects, JavaObjectToJsonConverter toJsonConverter) {
        this.webObjects = webObjects;
        this.toJsonConverter = toJsonConverter;
    }
    
    @GET
    @Produces(APPLICATION_JSON)
    public Response getDirectory(
            @PathParam("id") int userId, 
            @PathParam("place") String place,
            @PathParam("name") String name) {
        logger.info("get dir -> " +
                "user:" + userId +
                ", dir:" + name +
                ", place:" + place);
        JsonWebDirectory dir = new JsonWebDirectory(
                this.webObjects.getUserWebDirectory(userId, place, name));
        return okJsonResponseWith(this.toJsonConverter.jsonizeToString(dir));
    }
    
    @PUT
    @Path("/name")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response renameDirectory(
            @PathParam("id") int userId, 
            @PathParam("place") String place, 
            @PathParam("name") String name,
            JsonPayload payload) {
        boolean done = false;
        logger.info("rename dir -> " + 
                "user:" + userId +
                ", dir:" + name +
                ", place:" + place);
        logger.info("...to name: " + payload.get());
        done = this.webObjects.renameUserWebDirectory(
                userId, place, name, payload.get());
        if ( done ) {
            logger.info("...rename ok.");
            return okResponse();
        } else {
            logger.info("...dir renaming fails.");
            return jsonResponseWith(
                    SC_INTERNAL_SERVER_ERROR, 
                    "Directory renaming fails due to unknown reason.");                
        }
    }
    
    @PUT
    @Path("/placement")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response moveDirectoryToPlacement(
            @PathParam("id") int userId, 
            @PathParam("place") String place, 
            @PathParam("name") String name,
            JsonPayload payload) {
        boolean done = false;
        logger.info("move dir -> " + 
                "user:" + userId +
                ", dir:" + name +
                ", place:" + place);
        logger.info("...to placement: " + placementOf(payload.get()));
        done = this.webObjects.moveUserWebDirectoryIntoPlace(
                userId, place, payload.get(), name);
        if ( done ) {
            logger.info("...movement ok.");
            return okResponse();
        } else {
            logger.info("...dir movement fails.");
            return jsonResponseWith(
                    SC_INTERNAL_SERVER_ERROR, 
                    "Directory movement fails due to unknown reason.");
        }
    }    
    
    @PUT
    @Path("/order")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response reorderDirectory(
            @PathParam("id") int userId, 
            @PathParam("place") String place, 
            @PathParam("name") String name,
            JsonPayload payload) {
        boolean reordered = false;
        logger.info("Dir reordering -> " +
                "user:" + userId +
                ", dir:" + name +
                ", place:" + place);
        logger.info("...to new order: " + payload.get());
        reordered = this.webObjects.reorderUserWebDirectory(
                userId, place, name, valueOf(payload.get()));
        if ( reordered ) {
            logger.info("...dir reordering ok.");
            return okResponse();
        } else {
            logger.info("...dir reordering fails.");
            return jsonResponseWith(
                    SC_INTERNAL_SERVER_ERROR, 
                    "Directory reordering fails due to unknown reason.");
        }
    }
    
    @DELETE
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response deleteDirectory(
            @PathParam("id") int userId, 
            @PathParam("place") String place, 
            @PathParam("name") String name) {
        boolean done = false;
        logger.info("delete dir -> "+
                "user:" + userId +
                ", dir:" + name +
                ", place:" + place);
        done = this.webObjects.deleteUserWebDirectory(userId, place, name);
        if ( done ) {
            logger.info("...dir deleted.");
            return okResponse();
        } else {
            logger.info("...dir is not deleted.");
            return jsonResponseWith(
                    SC_INTERNAL_SERVER_ERROR, 
                    "Directory deleting fails due to unknown reason.");
        }
    }
}
