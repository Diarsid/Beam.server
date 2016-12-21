/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.services.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import diarsid.beam.server.domain.services.jwtauth.JwtUserInfo;
import diarsid.beam.server.domain.services.jwtauth.JwtValidationResult;
import diarsid.beam.server.domain.services.jwtauth.JwtValidator;
import diarsid.beam.server.presentation.web.services.JAXRSAutoRegistrableComponent;
import diarsid.beam.server.presentation.web.services.filters.bindings.AuthenticationRequired;

import static diarsid.beam.server.presentation.web.services.auth.InnerHttpRequestUserHeaders.BEAM_USER_ID;
import static diarsid.beam.server.presentation.web.services.auth.InnerHttpRequestUserHeaders.BEAM_USER_NICKNAME;
import static diarsid.beam.server.presentation.web.services.auth.InnerHttpRequestUserHeaders.BEAM_USER_ROLE;
import static diarsid.beam.server.presentation.web.services.filters.RequestAdditionalProperties.USER;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.unauthenticatedResponse;

/**
 *
 * @author Diarsid
 */

@Component
@Provider
@AuthenticationRequired
public class JwtAuthenticationFilter 
        implements 
                ContainerRequestFilter,
                JAXRSAutoRegistrableComponent {
    
    private final static Logger logger;
    static {
        logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    }
    
    private final JwtValidator validator;
    
    public JwtAuthenticationFilter(JwtValidator validator) {
        this.validator = validator;
        logger.info("created.");
    }
    
    @Override
    public void filter(ContainerRequestContext request) throws IOException {        
        logger.info("Auth required for: " + request.getUriInfo().getPath());
        JwtValidationResult result = this.validator.validateRequest(request);
        if ( result.isJwtPresent() ) {
            if ( result.isJwtSignVerified() ) {
                if ( result.isJwtNotExpired() ) {
                    request.setProperty(USER.getPropertyName(), result.getUserInfo());
                    logger.info("auth: OK <"
                            + "id:"+result.getUserInfo().getId() + 
                            ", role:"+result.getUserInfo().getRole() + 
                            ", nick:"+result.getUserInfo().getNickName() + ">");
                } else {
                    request.abortWith(unauthenticatedResponse());
                    logger.info("auth: JWT expired. Access denied.");
                }
            } else {
                logger.info("auth: JWT signature verification failure. Access denied.");
                request.abortWith(unauthenticatedResponse());
            }
        } else {
            logger.info("auth: JWT not found. Access denied.");
            request.abortWith(unauthenticatedResponse());
        }
    }
    
    private void addUserFieldsIntoHttpRequestHeader(ContainerRequestContext request, JwtUserInfo user) {
        MultivaluedMap<String, String> headers = request.getHeaders();
        headers.putSingle(BEAM_USER_ID.getHeader(), user.getId());
        headers.putSingle(BEAM_USER_NICKNAME.getHeader(), user.getNickName());
        headers.putSingle(BEAM_USER_ROLE.getHeader(), user.getRole());
    }
}
