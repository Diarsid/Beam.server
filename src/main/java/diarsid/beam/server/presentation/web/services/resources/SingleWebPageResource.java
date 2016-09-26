/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.services.resources;

import java.util.Optional;

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
import diarsid.beam.server.presentation.web.json.dto.JsonPlacementAndDirectory;
import diarsid.beam.server.presentation.web.json.dto.JsonWebPage;
import diarsid.beam.server.presentation.web.json.util.JavaObjectToJsonConverter;
import diarsid.beam.server.presentation.web.services.filters.bindings.AuthenticationRequired;

import static java.lang.Integer.valueOf;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.okResponse;

import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;

import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.okJsonResponseWith;

import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;

import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;

import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;

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

import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;


import static java.lang.Integer.valueOf;

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

import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;

import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;

import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;

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

import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;
import static java.lang.Integer.valueOf;

/**
 *
 * @author Diarsid
 */

@Component
@Path("/users/{id}/{place}/directories/{dirName}/pages/{pageName}")
@AuthenticationRequired
public class SingleWebPageResource {
    
    private static final Logger logger = LoggerFactory.getLogger(SingleWebPageResource.class);
    
    private final UserWebObjectsService webObjects;
    private final JavaObjectToJsonConverter toJsonConverter;
    
    public SingleWebPageResource(
            UserWebObjectsService webObjects, 
            JavaObjectToJsonConverter toJsonConverter) {
        this.webObjects = webObjects;
        this.toJsonConverter = toJsonConverter;
    }
    
    @PUT
    @Path("/name")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response editPageName(
            @PathParam("id") int userId, 
            @PathParam("place") String place, 
            @PathParam("dirName") String dirName, 
            @PathParam("pageName") String pageName, 
            JsonPayload payload) {
        boolean done;
        logger.info("page "+ pageName+"rename to: " + payload.get());
        done = this.webObjects.renameUserWebPage(userId, place, dirName, pageName, payload.get());
        if ( done ) {
            logger.info("...renamed.");
            return okResponse();
        } else {
            logger.info("...rename fails.");
            return jsonResponseWith(SC_INTERNAL_SERVER_ERROR, "cannot rename page.");
        }
    }
    
    @PUT
    @Path("/url")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response editPageUrl(
            @PathParam("id") int userId, 
            @PathParam("place") String place, 
            @PathParam("dirName") String dirName, 
            @PathParam("pageName") String pageName, 
            JsonPayload payload) {
        boolean done;
        logger.info("page "+ pageName+" change url to: " + payload.get());
        done = this.webObjects.redirectUserWebPageUrl(
                userId, place, dirName, pageName, payload.get());
        if ( done ) {
            logger.info("...url changed.");
            return okResponse();
        } else {
            logger.info("...rename fails.");
            return jsonResponseWith(SC_INTERNAL_SERVER_ERROR, "cannot change page url.");
        }
    }
    
    @PUT
    @Path("/directory")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response movePageToDirectory(
            @PathParam("id") int userId, 
            @PathParam("place") String place, 
            @PathParam("dirName") String dirName, 
            @PathParam("pageName") String pageName, 
            JsonPayload payload) {
        boolean done;
        logger.info("move page " + pageName + " to dir:" + payload.get());
        done = this.webObjects.moveUserWebPageIntoDirectory(
                userId, place, dirName, payload.get(), pageName);
        if ( done ) {
            logger.info("...page moved.");
            return okResponse();
        } else {
            logger.info("...page movement fails.");
            return jsonResponseWith(SC_INTERNAL_SERVER_ERROR, "cannot move page.");
        }
    }
    
    @PUT
    @Path("/place-and-directory")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response movePageToPlaceAndDirectory(
            @PathParam("id") int userId, 
            @PathParam("place") String place, 
            @PathParam("dirName") String dirName, 
            @PathParam("pageName") String pageName, 
            JsonPlacementAndDirectory moveInfo) {
        boolean done;
        logger.info("move page:" + pageName);
        logger.info("   -from place:"+place+ ", dir:"+dirName);
        logger.info("   -into place:"+moveInfo.getPlace()+ ", dir:"+moveInfo.getDirectory());
        done = this.webObjects.moveUserWebPageIntoDirectoryAndPlace(
                userId, place, moveInfo.getPlace(), dirName, moveInfo.getDirectory(), pageName);
        if ( done ) {
            logger.info("...page moved.");
            return okResponse();
        } else {
            logger.info("...page movement fails.");
            return jsonResponseWith(SC_INTERNAL_SERVER_ERROR, "cannot move page.");
        }
    }
        
    @PUT
    @Path("/order")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response editPageOrder(
            @PathParam("id") int userId, 
            @PathParam("place") String place, 
            @PathParam("dirName") String dirName, 
            @PathParam("pageName") String pageName, 
            JsonPayload payload) {
        boolean done;
        logger.info("page "+ pageName+" change order to: " + payload.get());
        done = this.webObjects.reorderUserWebPageOrder(
                userId, place, dirName, pageName, valueOf(payload.get()));
        if ( done ) {
            logger.info("...page reordered.");
            return okResponse();
        } else {
            logger.info("...page reordering fails fails.");
            return jsonResponseWith(SC_INTERNAL_SERVER_ERROR, "cannot reorder page.");
        }
    } 
    
    @GET
    @Produces(APPLICATION_JSON)
    public Response getPage(
            @PathParam("id") int userId, 
            @PathParam("place") String place, 
            @PathParam("dirName") String dirName, 
            @PathParam("pageName") String pageName) {
        Optional<JsonWebPage> page = this.webObjects
                .getUserWebDirectory(userId, place, dirName)
                .getPages()
                .stream()
                .filter(persistedPage -> persistedPage.getName().equals(pageName))
                .map(persistedPage -> new JsonWebPage(persistedPage))
                .findFirst();
        if ( page.isPresent() ) {
            return okJsonResponseWith(this.toJsonConverter.jsonizeToString(page.get()));
        } else {
            return Response.status(NOT_FOUND).build();
        }
    }
    
    @DELETE
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response deletePage(
            @PathParam("id") int userId, 
            @PathParam("place") String place, 
            @PathParam("dirName") String dirName, 
            @PathParam("pageName") String pageName) {
        boolean done;
        logger.info("delete page:" + pageName);
        done = this.webObjects.deleteUserWebPage(userId, place, dirName, pageName);
        if ( done ) {
            logger.info("...page deleted.");
            return okResponse();
        } else {
            logger.info("...page deletion fails.");
            return jsonResponseWith(SC_INTERNAL_SERVER_ERROR, "cannot delete page.");
        }
    }
}
