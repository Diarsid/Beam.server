/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.services.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import diarsid.beam.server.domain.services.exceptions.UserRegistrationInvalidException;
import diarsid.beam.server.presentation.web.services.JAXRSAutoRegistrableComponent;

import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.jsonResponseWith;

/**
 *
 * @author Diarsid
 */
public class MapperUserRegistrationInvalidException 
        implements 
                ExceptionMapper<UserRegistrationInvalidException>,
                JAXRSAutoRegistrableComponent {
    
    public MapperUserRegistrationInvalidException() {
    }
    
    @Override
    public Response toResponse(UserRegistrationInvalidException e) {
        return jsonResponseWith(e);
    }
}
