/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.domain.validation;

/**
 *
 * @author Diarsid
 */
public class ValidatablePayload {
    
    private final String payload;
    
    public ValidatablePayload(String payload) {
        this.payload = payload;
    }
    
    public String get() {
        return this.payload;
    }
}
