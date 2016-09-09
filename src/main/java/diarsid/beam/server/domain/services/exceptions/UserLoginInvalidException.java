/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.domain.services.exceptions;

import diarsid.beam.server.domain.services.validation.ValidationResult;

/**
 *
 * @author Diarsid
 */
public class UserLoginInvalidException extends DomainRuntimeException {
        
    /**
     * Constructs an instance of <code>UserLoginInvalidException</code> with the specified 
     * ValidationResult message.
     *
     * @param ValidationResult result.
     */
    public UserLoginInvalidException(ValidationResult result) {
        super(result.getFailureMessage());
    }    
}
