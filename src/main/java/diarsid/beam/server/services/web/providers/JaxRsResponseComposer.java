/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.web.providers;

import javax.ws.rs.core.Response;

import diarsid.beam.server.services.domain.exceptions.ConvertableToJsonErrorObject;
import diarsid.beam.server.services.domain.validation.ValidationResult;
import diarsid.beam.server.util.JsonErrorObject;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 *
 * @author Diarsid
 */
public class JaxRsResponseComposer {
    
    private JaxRsResponseComposer() {
    }
    
    public static Response composeResponseFrom(int status, ValidationResult result) {
        return buildWithJsonErrorObject(new JsonErrorObject(status, result.getFailureMessage()));
    }
    
    public static Response composeResponseFrom(ConvertableToJsonErrorObject convertable) {
        return buildWithJsonErrorObject(convertable.convertToJsonErrorObject());        
    }
    
    private static Response buildWithJsonErrorObject(JsonErrorObject errorObject) {
        return Response.status(errorObject.getStatusCode())
                .type(APPLICATION_JSON)
                .entity(errorObject)
                .build();
    }
}
