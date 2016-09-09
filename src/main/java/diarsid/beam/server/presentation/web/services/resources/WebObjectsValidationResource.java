/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.services.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import diarsid.beam.server.presentation.web.json.dto.JsonPayload;
import diarsid.beam.server.domain.services.validation.ValidationResult;
import diarsid.beam.server.domain.services.validation.WebObjectsValidationService;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.OK;

import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.composeResponseFrom;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.composeResponseFrom;

/**
 *
 * @author Diarsid
 */

@Path("/validation/webobjects")
public class WebObjectsValidationResource {
    
    private final WebObjectsValidationService validationService;
    
    public WebObjectsValidationResource(WebObjectsValidationService validator) {
        this.validationService = validator;
    }
    
    @GET
    @Path("/names")
    public Response validateName(JsonPayload payload) {
        ValidationResult result = this.validationService.isWebObjectNameValid(payload.get());
        if ( result.isOk() ) {
            return Response.status(OK).build();
        } else {
            return composeResponseFrom(SC_BAD_REQUEST, result);
        }
    }
    
    @GET
    @Path("/urls")
    public Response validateUrl(JsonPayload payload) {
        ValidationResult result = this.validationService.isUrlValid(payload.get());
        if ( result.isOk() ) {
            return Response.status(OK).build();
        } else {
            return composeResponseFrom(SC_BAD_REQUEST, result);
        }
    }
}
