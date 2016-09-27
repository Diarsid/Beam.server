/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.services.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import diarsid.beam.server.domain.entities.jpa.PersistableUser;
import diarsid.beam.server.domain.services.jwtauth.JwtAuthService;
import diarsid.beam.server.domain.services.jwtauth.JwtUserInfo;
import diarsid.beam.server.domain.services.jwtauth.JwtValidationResult;
import diarsid.beam.server.domain.services.users.UsersService;
import diarsid.beam.server.presentation.web.json.dto.JsonUserLogin;
import diarsid.beam.server.presentation.web.json.dto.JsonUserRegistration;
import diarsid.beam.server.presentation.web.services.filters.bindings.AuthenticationRequired;

import static java.lang.Integer.valueOf;
import static java.util.Objects.nonNull;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import static diarsid.beam.server.presentation.web.services.filters.RequestAdditionalProperties.USER;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.anauthenticatedResponse;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.okResponse;
import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.okResponseWithJwt;

/**
 *
 * @author Diarsid
 */

@Component
@Path("/auth")
public class AuthenticationResource {
    
    private final static Logger logger;
    static {
        logger = LoggerFactory.getLogger(AuthenticationResource.class);
    }
    
    private final UsersService usersService;
    private final JwtAuthService jwtAuthService;
    
    public AuthenticationResource(
            UsersService users, 
            JwtAuthService jwtService) {
        this.jwtAuthService = jwtService;
        this.usersService = users;
        logger.info("created.");
    }
        
    @POST
    @Path("/login")
    @Consumes(APPLICATION_JSON)
    public Response loginUserAndReturnJWT(JsonUserLogin login) {
        logger.info("login data: " + login.getNickName() + " "+ login.getPassword());            
        PersistableUser user = this.usersService.findBy(login);
        if ( user != null ) {
            logger.info("login succeed with " + login.getNickName() + ":" + login.getPassword());               
            return okResponseWithJwt(this.jwtAuthService.createJwtFor(user));
        } else {
            logger.info("login failed with " + login.getNickName() + ":" + login.getPassword());
            return anauthenticatedResponse();
        }
    }
    
    @POST
    @Path("/registration")
    @Consumes(APPLICATION_JSON)
    public Response registerUserAndReturnJWT(JsonUserRegistration registration) {        
        PersistableUser user = this.usersService.createUserBy(registration);
        logger.info(
                "user registration succeed: <id:" + user.getId() + 
                        ", nick:" + user.getNickname() + ">");
        return okResponseWithJwt(this.jwtAuthService.createJwtFor(user));                
    }
    
    @POST
    @Path("/verify")
    public Response validateJWT(@Context ContainerRequestContext request) {
        logger.info("token verification...");
        JwtValidationResult result = this.jwtAuthService.validateRequest(request);
        if ( result.isJwtPresent() ) {
            if ( result.isJwtSignVerified() ) {
                if ( result.isJwtNotExpired() ) {
                    return okResponse();
                } else {
                    return anauthenticatedResponse();
                }
            } else {
                return anauthenticatedResponse();
            }
        } else {
            return anauthenticatedResponse();
        }        
    }
    
    @POST
    @Path("/refresh")
    @AuthenticationRequired
    public Response refreshJWT(@Context ContainerRequestContext request) {
        logger.info("jwt refreshing...");
        PersistableUser user = getUserFromRequestProperty(request);
        if ( nonNull(user) ) {
            logger.info("...new jwt for: " + user.getId() + ":" + user.getNickname());               
            return okResponseWithJwt(this.jwtAuthService.createJwtFor(user));
        } else {
            logger.info("...unauthenticated.");
            return anauthenticatedResponse();
        }
    }

    private PersistableUser getUserFromRequestProperty(ContainerRequestContext request) 
            throws NumberFormatException {
        JwtUserInfo info = (JwtUserInfo) request.getProperty(USER.getPropertyName());
        return this.usersService.findBy(valueOf(info.getId()));
    }
}
