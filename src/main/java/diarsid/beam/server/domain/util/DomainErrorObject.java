/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.domain.util;

import java.io.Serializable;

/**
 *
 * @author Diarsid
 */
public class DomainErrorObject implements Serializable {
    
    private final String message;
    
    public DomainErrorObject(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return this.message;
    }
}
