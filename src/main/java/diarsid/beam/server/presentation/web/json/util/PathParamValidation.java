/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.json.util;

import diarsid.beam.server.domain.services.validation.ValidationResult;

import static java.util.Objects.isNull;

import static diarsid.beam.server.domain.services.validation.ValidationResultImpl.validationFailsWith;
import static diarsid.beam.server.domain.services.validation.ValidationResultImpl.validationOk;

/**
 *
 * @author Diarsid
 */
public class PathParamValidation {
    
    private PathParamValidation() {
    }
    
    public static ValidationProcess validateParam(String param) {
        return new ValidationProcess(param);
    }
    
    public static class ValidationProcess {
        
        private final String validatedParam;
        private String failureMessage;
        private boolean success;
        
        private ValidationProcess(String validatedParam) {
            this.validatedParam = validatedParam;
            this.success = true;
            this.failureMessage = "";
        }
        
        private void setFailed(String message) {
            this.success = false;
            this.failureMessage = message;
        }
        
        public ValidationProcess onNotNull() {
            // if validation failed during previous steps or fails now
            if ( this.success && isNull(this.validatedParam) ) {
                this.setFailed("is null.");
                return this;
            } else {
                // just proceed.
                return this;
            }
        }
        
        public ValidationProcess onNotEmpty() {
            if ( this.success && this.validatedParam.isEmpty() ) {
                this.setFailed("is empty.");
                return this;
            } else {
                // just proceed.
                return this;
            }
        }
        
        public ValidationProcess onOnlyDigits() {
            if (this.success && ! this.validatedParam.matches("[0-9]+")) {
                this.setFailed("contains non-digits or is empty.");
                return this;
            } else {
                // just proceed.
                return this;
            }
        }
        
        public ValidationResult end() {
            if ( this.success ) {
                return validationOk();
            } else {
                return validationFailsWith(this.failureMessage);
            }
        }
    }
}
