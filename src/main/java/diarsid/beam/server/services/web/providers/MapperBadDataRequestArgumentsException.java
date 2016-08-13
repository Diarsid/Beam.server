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

import diarsid.beam.server.services.domain.exceptions.BadDataRequestArgumentsException;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

/**
 *
 * @author Diarsid
 */

@Provider
@Component
public class MapperBadDataRequestArgumentsException 
        implements ExceptionMapper<BadDataRequestArgumentsException> {
    
    public MapperBadDataRequestArgumentsException() {
    }
    
    @Override
    public Response toResponse(BadDataRequestArgumentsException e) {
        return Response.status(BAD_REQUEST)
                .type(APPLICATION_JSON)
                .entity(e.convertToJsonErrorObject())
                .build();
    }
}
