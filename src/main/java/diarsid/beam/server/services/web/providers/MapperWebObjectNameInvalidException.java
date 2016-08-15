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

import diarsid.beam.server.services.domain.exceptions.WebObjectNameInvalidException;

import static diarsid.beam.server.services.web.providers.JaxRsResponseComposer.composeResponseFrom;

/**
 *
 * @author Diarsid
 */

@Provider
@Component
public class MapperWebObjectNameInvalidException 
        implements ExceptionMapper<WebObjectNameInvalidException> {
    
    public MapperWebObjectNameInvalidException() {
    }

    @Override
    public Response toResponse(WebObjectNameInvalidException e) {
        return composeResponseFrom(e);
    }
}
