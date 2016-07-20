/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.jaxrsproviders;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

/**
 *
 * @author Diarsid
 */

@Provider
public class RuntimeExceptionJaxRsMapper implements ExceptionMapper<RuntimeException> {
    
    private static final Logger logger;
    static {
        logger = LoggerFactory.getLogger(RuntimeExceptionJaxRsMapper.class);
    }
    
    public RuntimeExceptionJaxRsMapper() {
        logger.info("created.");
    }
    
    @Override
    public Response toResponse(RuntimeException exception) {
        logger.error("Intercepted application exception: ", exception);
        return Response
                .status(INTERNAL_SERVER_ERROR)
                .type(APPLICATION_JSON)
                .entity(exception)
                .build();
    }
}
