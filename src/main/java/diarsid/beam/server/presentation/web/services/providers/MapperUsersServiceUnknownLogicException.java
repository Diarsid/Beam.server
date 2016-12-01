/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.services.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import diarsid.beam.server.domain.services.exceptions.UsersServiceUnknownLogicException;

import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;

/**
 *
 * @author Diarsid
 */

@Provider
@Component
public class MapperUsersServiceUnknownLogicException 
        implements ExceptionMapper<UsersServiceUnknownLogicException> {
    
    public MapperUsersServiceUnknownLogicException() {
    }

    @Override
    public Response toResponse(UsersServiceUnknownLogicException e) {
        return jsonResponseWith(e);
    }
}
