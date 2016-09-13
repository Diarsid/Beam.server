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

import diarsid.beam.server.domain.services.exceptions.NickNameIsNotFreeException;

import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.composeResponseFrom;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.composeResponseFrom;

/**
 *
 * @author Diarsid
 */

@Provider
@Component
public class MapperNickNameIsNotFreeException 
        implements ExceptionMapper<NickNameIsNotFreeException> {
    
    public MapperNickNameIsNotFreeException() {
    }

    @Override
    public Response toResponse(NickNameIsNotFreeException e) {
        return composeResponseFrom(e);
    }
}
