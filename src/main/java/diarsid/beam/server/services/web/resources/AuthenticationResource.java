/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.web.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import diarsid.beam.server.data.entities.jpa.PersistableUser;
import diarsid.beam.server.services.domain.users.UsersService;
import diarsid.beam.server.services.web.auth.UserLoginRequestData;
import diarsid.beam.server.services.web.auth.UserRegistrationRequestData;
import diarsid.beam.server.services.web.auth.jwt.JwtService;
import diarsid.beam.server.services.web.auth.jwt.JwtValidationResult;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.FOUND;
import static javax.ws.rs.core.Response.Status.OK;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import static diarsid.beam.server.services.web.auth.jwt.JwtProducer.JWT_RESPONSE_HEADER;

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
    private final JwtService jwtService;
    
    public AuthenticationResource(
            UsersService users, 
            JwtService jwtService) {
        this.jwtService = jwtService;
        this.usersService = users;
        logger.info("created.");
    }
        
    @POST
    @Path("/login")
    @Consumes(APPLICATION_JSON)
    public Response loginUserAndReturnJWT(UserLoginRequestData login) {
        logger.info("login data: " + login.getNickName() + " "+ login.getPassword());            
        PersistableUser user = this.usersService.findBy(login);
        if ( user != null ) {
            logger.info("login succeed with " + login.getNickName() + ":" + login.getPassword());               
            return Response.ok().header(
                    JWT_RESPONSE_HEADER, 
                    this.jwtService.createJwtFor(user)).build();
        } else {
            logger.info("login failed with " + login.getNickName() + ":" + login.getPassword());
            return Response.status(UNAUTHORIZED).build();
        }
    }
    
    @POST
    @Path("/registration")
    @Consumes(APPLICATION_JSON)
    public Response registerUserAndReturnJWT(UserRegistrationRequestData registration) {        
        PersistableUser user = this.usersService.createUserBy(registration);
        logger.info("user registration succeed: <id:" + user.getId() + ", nick:" + user.getNickname() + ">");
        return Response.ok().header(JWT_RESPONSE_HEADER, this.jwtService.createJwtFor(user)).build();                
    }
    
    @POST
    @Path("/verify")
    public Response validateJWT(@Context ContainerRequestContext request) {
        logger.info("token verification...");
        JwtValidationResult result = this.jwtService.validateRequest(request);
        if ( result.isJwtPresent() ) {
            if ( result.isJwtSignVerified() ) {
                if ( result.isJwtNotExpired() ) {
                    return Response.status(OK).build();
                } else {
                    return Response.status(FOUND).build();
                }
            } else {
                return Response.status(UNAUTHORIZED).build();
            }
        } else {
            return Response.status(UNAUTHORIZED).build();
        }        
    }
}