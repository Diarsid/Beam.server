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
import diarsid.beam.server.presentation.web.json.dto.JsonWebPage;
import diarsid.beam.server.presentation.web.json.util.JavaObjectToJsonConverter;

import static java.util.stream.Collectors.toList;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.composeOkResponseWithJson;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.composeResponseFrom;


/**
 *
 * @author Diarsid
 */

@Component
@Path("/users/{id}/{place}/directories/{dirName}/pages")
public class WebPagesResource {
    
    private static final Logger logger = LoggerFactory.getLogger(WebDirectoriesResource.class);
    
    private final UserWebObjectsService webObjects;
    private final JavaObjectToJsonConverter toJsonConverter;
    
    public WebPagesResource(
            UserWebObjectsService webObjects,
            JavaObjectToJsonConverter toJsonConverter) {
        this.webObjects = webObjects;
        this.toJsonConverter = toJsonConverter;
    }
    
    @GET
    @Produces(APPLICATION_JSON)
    public Response getAllPagesInDirectory(
            @PathParam("id") int userId, 
            @PathParam("place") String place, 
            @PathParam("dirName") String dirName) {
        logger.info("get from place:" + place + ", dir:" + dirName);
        List<JsonWebPage> pages = this.webObjects
                .getUserWebDirectory(userId, place, dirName)
                .getPages()
                .stream()
                .map(persistablePage -> new JsonWebPage(persistablePage))
                .collect(toList());
        logger.info("..." + pages.size() + " pages obtained.");
        return composeOkResponseWithJson(this.toJsonConverter.jsonizeToString(pages));
    }
    
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response createNewPageInDirectory(
            @PathParam("id") int userId, 
            @PathParam("place") String place, 
            @PathParam("dirName") String dirName, 
            JsonWebPage pageData) {
        boolean done;
        logger.info("create new page:" + pageData.getName() + " in place:" + place + ", dir:" + dirName);
        done = this.webObjects.createUserWebPage(
                userId, place, dirName, pageData.getName(), pageData.getUrl());
        if ( done ) {
            logger.info("...page created.");
            return Response.ok().build();
        } else {
            logger.info("...page creation fails.");
            return composeResponseFrom(
                    SC_INTERNAL_SERVER_ERROR, 
                    "WebPage creation fails due to unknown reason.");
        }       
    }
}
