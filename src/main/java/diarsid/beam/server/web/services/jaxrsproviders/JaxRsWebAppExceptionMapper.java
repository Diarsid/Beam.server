/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.web.services.jaxrsproviders;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Diarsid
 */

@Provider
public class JaxRsWebAppExceptionMapper implements ExceptionMapper<WebApplicationException> {
    
    private final static Logger logger;
    static {
        logger = LoggerFactory.getLogger(JaxRsWebAppExceptionMapper.class);
    }
    
    public JaxRsWebAppExceptionMapper() {
        logger.info("created.");
    }
    
    @Override
    public Response toResponse(WebApplicationException exception) {
        logger.info("Intercepted web exception: ", exception);
        return exception.getResponse();
    }
}
