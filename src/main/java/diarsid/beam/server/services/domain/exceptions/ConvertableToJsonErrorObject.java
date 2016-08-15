/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.services.domain.exceptions;

import diarsid.beam.server.util.JsonErrorObject;

/**
 *
 * @author Diarsid
 */
public interface ConvertableToJsonErrorObject {
    
    JsonErrorObject convertToJsonErrorObject();    
}
