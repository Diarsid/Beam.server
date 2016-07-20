/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.resources;

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

import diarsid.beam.server.data.daos.DaoUsers;
import diarsid.beam.server.data.entities.UserLoginData;
import diarsid.beam.server.data.entities.UserRegistrationData;
import diarsid.beam.server.data.entities.jpa.PersistableUser;
import diarsid.beam.server.services.auth.JwtProducer;
import diarsid.beam.server.services.auth.JwtValidationResult;
import diarsid.beam.server.services.auth.JwtValidator;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.FOUND;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import static diarsid.beam.server.services.auth.JwtProducer.JWT_RESPONSE_HEADER;

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
    
    private final DaoUsers daoUsers;
    private final JwtValidator jwtValidator;
    private final JwtProducer jwtProducer;
    
    public AuthenticationResource(
            DaoUsers daoUsers, 
            JwtValidator jwtValidator,
            JwtProducer jwtProducer) {
        this.daoUsers = daoUsers;
        this.jwtValidator = jwtValidator;
        this.jwtProducer = jwtProducer;
        logger.info("created.");
    }
        
    @POST
    @Path("/users/login")
    @Consumes(APPLICATION_JSON)
    public Response loginUserAndReturnJWT(UserLoginData login) {
        logger.info("login data: " + login.getNickName() + " "+ login.getPassword());            
        PersistableUser user = this.daoUsers.getUserByNickAndPass(login);
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
    public Response registerUserAndReturnJWT(UserRegistrationData registration) {
        String role = "user";
        PersistableUser user = new PersistableUser(registration, role);
        this.daoUsers.addUser(user);
        String jwt = this.jwtProducer.createJwt(user.getId(), user.getNickname(), role);
        logger.info("user registration succeed: <id:" + user.getId() + ", nick:" + user.getNickname() + ">");
        return Response.ok().header(JWT_RESPONSE_HEADER, jwt).build();                
    }

    private void logRegistration(UserRegistrationData registration) {
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
        if ( this.daoUsers.isNickNameFree(nick) ) {
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
