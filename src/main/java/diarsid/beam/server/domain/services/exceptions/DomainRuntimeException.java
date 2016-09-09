/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.domain.services.exceptions;

import diarsid.beam.server.domain.util.ConvertableToDomainErrorObject;
import diarsid.beam.server.domain.util.DomainErrorObject;

/**
 *
 * @author Diarsid
 */
public class DomainRuntimeException 
        extends RuntimeException 
        implements ConvertableToDomainErrorObject {
    
    public DomainRuntimeException(String msg) {
        super(msg);
    }
    
    @Override
    public DomainErrorObject convertToDomainErrorObject() {
        return new DomainErrorObject(this.getMessage());
    }
}
