/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.filters.auth;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Diarsid
 */
@Provider
@AuthenticationNotRequired
public class NonAuthenticateFilter implements ContainerRequestFilter {
    
    private final static Logger LOGGER;
    static {
        LOGGER = LoggerFactory.getLogger(NonAuthenticateFilter.class);
    }
    
    public NonAuthenticateFilter() {
        LOGGER.info("created.");
    }
    
    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        LOGGER.info("intercepted: " + request.getUriInfo().getPath());
    }
}
