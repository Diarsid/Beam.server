/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.domain.exceptions;

import diarsid.beam.server.services.domain.validation.ValidationResult;
import diarsid.beam.server.util.JsonErrorObject;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

/**
 *
 * @author Diarsid
 */
public class UserRegistrationInvalidException 
        extends RuntimeException 
        implements ConvertableToJsonErrorObject {
        
    private static final int HTTP_ERROR_STATUS_CODE; 
    static {
        HTTP_ERROR_STATUS_CODE = SC_UNAUTHORIZED;
    }
    
    public UserRegistrationInvalidException(ValidationResult result) {
        super(result.getFailureMessage());
    }
    
    @Override
    public JsonErrorObject convertToJsonErrorObject() {
        return new JsonErrorObject(HTTP_ERROR_STATUS_CODE, this.getMessage());
    }
}
