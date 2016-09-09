/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.domain.services.validation;

/**
 *
 * @author Diarsid
 */
public class ValidationResultImpl implements ValidationResult {
    
    private static final ValidationResultImpl OK_RESULT;
    
    static {
        OK_RESULT = new ValidationResultImpl(true, "Validation is successful, info is OK.");
    }
        
    private final String description;
    private final boolean isOk;
    
    private ValidationResultImpl(boolean isOk, String description) {
        this.isOk = isOk;
        this.description = description;
    }
    
    public static ValidationResult validationOk() {
        return OK_RESULT;
    }
    
    public static ValidationResult validationFailsWith(String description) {
        return new ValidationResultImpl(false, description);
    }
    
    @Override
    public boolean isOk() {
        return this.isOk;
    }
    
    @Override
    public String getFailureMessage() {
        return this.description;
    }    
}
