/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.filters.auth;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import diarsid.beam.server.services.auth.JwtValidationResult;
import diarsid.beam.server.services.auth.JwtValidator;
import diarsid.beam.server.services.auth.UserJwtInfo;

import static javax.ws.rs.core.Response.Status.FOUND;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import static diarsid.beam.server.services.auth.InnerHttpRequestUserHeaders.BEAM_USER_ID;
import static diarsid.beam.server.services.auth.InnerHttpRequestUserHeaders.BEAM_USER_NICKNAME;
import static diarsid.beam.server.services.auth.InnerHttpRequestUserHeaders.BEAM_USER_ROLE;

/**
 *
 * @author Diarsid
 */

@Component
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {
    
    private final static Logger LOGGER;
    static {
        LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);
    }
    
    private final JwtValidator validator;
    
    public AuthenticationFilter(JwtValidator validator) {
        this.validator = validator;
        LOGGER.info("created.");
    }
    
    @Override
    public void filter(ContainerRequestContext request) throws IOException {        
        String path = request.getUriInfo().getPath();
        if ( path.contains("auth") ) {
            LOGGER.info("unprotected area: " + path);
        } else {
            LOGGER.info("auth: required for: " + path);
            JwtValidationResult result = this.validator.validateRequest(request);
            if ( result.isJwtPresent() ) {
                if ( result.isJwtSignVerified() ) {
                    if ( result.isJwtNotExpired() ) {
                        this.addUserFieldsIntoHttpRequestHeader(request, result.getUserInfo());
                        LOGGER.info("auth: OK <"
                                + "id:"+result.getUserInfo().getId() + 
                                ", role:"+result.getUserInfo().getRole() + 
                                ", nick:"+result.getUserInfo().getNickName() + ">");
                    } else {
                        request.abortWith(Response.status(FOUND).build());
                        LOGGER.info("auth: JWT expired. Access denied.");
                    }
                } else {
                    LOGGER.info("auth: JWT signature verification failure. Access denied.");
                    request.abortWith(Response.status(UNAUTHORIZED).build());
                }
            } else {
                LOGGER.info("auth: JWT not found. Access denied.");
                request.abortWith(Response.status(UNAUTHORIZED).build());
            }
        }        
    }
    
    private void addUserFieldsIntoHttpRequestHeader(ContainerRequestContext request, UserJwtInfo user) {
        MultivaluedMap<String, String> headers = request.getHeaders();
        headers.putSingle(BEAM_USER_ID.getHeader(), user.getId());
        headers.putSingle(BEAM_USER_NICKNAME.getHeader(), user.getNickName());
        headers.putSingle(BEAM_USER_ROLE.getHeader(), user.getRole());
    }
}
