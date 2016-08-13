/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.web.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import diarsid.beam.server.data.entities.jpa.PersistableUser;
import diarsid.beam.server.services.domain.users.UsersService;
import diarsid.beam.server.services.web.auth.jwt.JwtProducer;
import diarsid.beam.server.services.web.auth.jwt.JwtValidationResult;
import diarsid.beam.server.services.web.auth.jwt.JwtValidator;
import diarsid.beam.server.services.web.auth.UserLoginRequestData;
import diarsid.beam.server.services.web.auth.UserRegistrationRequestData;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.FOUND;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
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
    private final JwtValidator jwtValidator;
    private final JwtProducer jwtProducer;
    
    public AuthenticationResource(
            UsersService users, 
            JwtValidator jwtValidator,
            JwtProducer jwtProducer) {
        this.usersService = users;
        this.jwtValidator = jwtValidator;
        this.jwtProducer = jwtProducer;
        logger.info("created.");
    }
        
    @POST
    @Path("/users/login")
    @Consumes(APPLICATION_JSON)
    public Response loginUserAndReturnJWT(UserLoginRequestData login) {
        logger.info("login data: " + login.getNickName() + " "+ login.getPassword());            
        PersistableUser user = this.usersService.findBy(login);
        if ( user != null ) {
            logger.info("login succeed with " + login.getNickName() + ":" + login.getPassword());               
            return Response.ok().header(
                    JWT_RESPONSE_HEADER, this.produceJwtFor(user)).build();
        } else {
            logger.info("login failed with " + login.getNickName() + ":" + login.getPassword());
            return Response.status(UNAUTHORIZED).build();
        }
    }

    private String produceJwtFor(PersistableUser user) {
        return this.jwtProducer.createJwt(user.getId(), user.getNickname(), user.getRole());
    }    
    
    @POST
    @Path("/users/registration")
    @Consumes(APPLICATION_JSON)
    public Response registerUserAndReturnJWT(UserRegistrationRequestData registration) {        
        PersistableUser user = this.usersService.createUserBy(registration);
        String jwt = this.jwtProducer.createJwt(user.getId(), user.getNickname(), user.getRole());
        logger.info("user registration succeed: <id:" + user.getId() + ", nick:" + user.getNickname() + ">");
        return Response.ok().header(JWT_RESPONSE_HEADER, jwt).build();                
    }

    private void logRegistration(UserRegistrationRequestData registration) {
        String password = registration.getPassword();
        String name = registration.getName();
        String surname = registration.getSurname();
        String nickName = registration.getNickName();
        String email = registration.getEmail();
        logger.info("parsed reg data: " + name + " " + surname + " " + nickName + " " + email);
    }
    
    @GET
    @Path("/users/nicknames/{nick}")
    public Response checkIfNickNameIsFree(@PathParam("nick") String nick) {
        logger.info("nickname usage validation...");
        if ( this.usersService.isNickValidAndFree(nick) ) {
            return Response.status(NOT_FOUND).build();
        } else {
            return Response.status(FOUND).build();
        }
    }
    
    @POST
    @Path("/tokens/validation")
    public Response validateJWT(@Context ContainerRequestContext request) {
        logger.info("token validation...");
        JwtValidationResult result = this.jwtValidator.validateRequest(request);
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
