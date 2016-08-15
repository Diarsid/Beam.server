/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.web.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import diarsid.beam.server.services.domain.exceptions.UserRegistrationInvalidException;

import static diarsid.beam.server.services.web.providers.JaxRsResponseComposer.composeResponseFrom;

/**
 *
 * @author Diarsid
 */
public class MapperUserRegistrationInvalidException 
        implements ExceptionMapper<UserRegistrationInvalidException> {
    
    public MapperUserRegistrationInvalidException() {
    }
    
    @Override
    public Response toResponse(UserRegistrationInvalidException e) {
        return composeResponseFrom(e);
    }
}
