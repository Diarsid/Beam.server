/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.services.providers;

import javax.ws.rs.core.Response;

import diarsid.beam.server.domain.services.exceptions.DomainRuntimeException;
import diarsid.beam.server.domain.services.validation.ValidationResult;
import diarsid.beam.server.presentation.web.json.dto.JsonErrorObject;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import static diarsid.beam.server.presentation.web.services.providers.DomainExceptionOnHttpStatusMapper.defineHttpStatusOf;

/**
 *
 * @author Diarsid
 */
public class JaxRsResponseComposer {
    
    private JaxRsResponseComposer() {
    }
    
    public static Response composeResponseFrom(int status, String message) {
        return buildWithJsonErrorObject(new JsonErrorObject(status, message));
    }
    
    public static Response composeResponseFrom(int status, ValidationResult result) {
        return buildWithJsonErrorObject(new JsonErrorObject(status, result));
    }
    
    public static Response composeResponseFrom(DomainRuntimeException domainException) {
        return buildWithJsonErrorObject(new JsonErrorObject(
                defineHttpStatusOf(domainException), 
                domainException.convertToDomainErrorObject()));        
    }
    
    private static Response buildWithJsonErrorObject(JsonErrorObject errorObject) {
        return Response.status(errorObject.getStatusCode())
                .type(APPLICATION_JSON)
                .entity(errorObject)
                .build();
    }
}
