/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.web.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import diarsid.beam.server.services.domain.exceptions.UserLoginInvalidException;

import static diarsid.beam.server.services.web.providers.JaxRsResponseComposer.composeResponseFrom;

/**
 *
 * @author Diarsid
 */

@Provider
@Component
public class MapperUserLoginInvalidException 
        implements ExceptionMapper<UserLoginInvalidException> {
    
    public MapperUserLoginInvalidException() {
    }
    
    @Override
    public Response toResponse(UserLoginInvalidException e) {
        return composeResponseFrom(e);
    }
}
