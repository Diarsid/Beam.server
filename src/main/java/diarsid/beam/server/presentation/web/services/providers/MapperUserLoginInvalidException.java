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

import diarsid.beam.server.domain.services.exceptions.UserLoginInvalidException;
import diarsid.beam.server.presentation.web.services.JAXRSAutoRegistrableComponent;

import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;

/**
 *
 * @author Diarsid
 */

@Provider
@Component
public class MapperUserLoginInvalidException 
        implements 
                ExceptionMapper<UserLoginInvalidException>,
                JAXRSAutoRegistrableComponent {
    
    public MapperUserLoginInvalidException() {
    }
    
    @Override
    public Response toResponse(UserLoginInvalidException e) {
        return jsonResponseWith(e);
    }
}
