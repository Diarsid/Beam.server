/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.json.dto;

import diarsid.beam.server.domain.services.validation.ValidationResult;
import diarsid.beam.server.domain.util.DomainErrorObject;

/**
 *
 * @author Diarsid
 */
public class JsonErrorObject {
    
    private final int statusCode;
    private final String message;
    
    public JsonErrorObject(int status, String message) {
        this.statusCode = status;
        this.message = message;
    }
    
    public JsonErrorObject(int status, ValidationResult result) {
        this.statusCode = status;
        this.message = result.getFailureMessage();
    }
    
    public JsonErrorObject(int status, DomainErrorObject error) {
        this.statusCode = status;
        this.message = error.getMessage();
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getMessage() {
        return this.message;
    }
}
