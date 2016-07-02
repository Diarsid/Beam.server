/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.resources.auth;

import java.util.Random;

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

import com.google.gson.JsonObject;

import diarsid.beam.server.data.MockUserStorage;
import diarsid.beam.server.entities.User;
import diarsid.beam.server.services.auth.JwtProducer;
import diarsid.beam.server.services.auth.JwtValidationResult;
import diarsid.beam.server.services.auth.JwtValidator;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.FOUND;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import static diarsid.beam.server.services.auth.JwtProducer.JWT_RESPONSE_HEADER;
import static diarsid.beam.server.util.GsonJsonConverter.objectivize;

/**
 *
 * @author Diarsid
 */

@Component
@Path("/auth")
public class AuthenticationResource {
    
    private final static Logger LOGGER;
    static {
        LOGGER = LoggerFactory.getLogger(AuthenticationResource.class);
    }
    
    private final MockUserStorage userStorage;
    private final JwtValidator jwtValidator;
    private final JwtProducer jwtProducer;
    private final Random random;
    
    public AuthenticationResource(
            MockUserStorage userStorage, 
            JwtValidator jwtValidator,
            JwtProducer jwtProducer) {
        this.userStorage = userStorage;
        this.jwtValidator = jwtValidator;
        this.jwtProducer = jwtProducer;
        this.random = new Random();
        LOGGER.info("created.");
    }
    
    @POST
    @Path("/users/login")
    @Consumes(APPLICATION_JSON)
    public Response loginUserAndReturnJWT(String userLoginDataInJson) {
        LOGGER.info("user login with: " + userLoginDataInJson); 
        JsonObject userLoginData = objectivize(userLoginDataInJson);
        LOGGER.info("log json: " + userLoginData);
        try {
            
            String nickName = userLoginData.get("nickName").getAsString();
            String password = userLoginData.get("password").getAsString();
            LOGGER.info("parsed login data: " + nickName + " "+ password);            
            User user = this.userStorage.getUserByNickAndPass(nickName, password);
            if ( user != null ) {
                LOGGER.info("login succeed with " + nickName + ":" + password);               
                return Response.ok().header(
                        JWT_RESPONSE_HEADER, this.produceJwtFor(user)).build();
            } else {
                LOGGER.info("login failed with " + nickName + ":" + password);
                return Response.status(UNAUTHORIZED).build();
            }
        } catch (NullPointerException e) {
            LOGGER.info("user login failed: bad request.", e);
            return Response.status(BAD_REQUEST).build();
        }
    }

    private String produceJwtFor(User user) {
        return this.jwtProducer.createJwt(user.getIdString(), user.getNickName(), user.getRole());
    }
    
    @POST
    @Path("/users/registration")
    @Consumes(APPLICATION_JSON)
    public Response registerUserAndReturnJWT(String userRegDataInJson) {
        LOGGER.info("user registration with: " + userRegDataInJson);   
        JsonObject userRegData = objectivize(userRegDataInJson);
        LOGGER.info("reg json: " + userRegData);
        try {
            String password = userRegData.get("password").getAsString();
            String name = userRegData.get("name").getAsString();
            String surname = userRegData.get("surname").getAsString();
            String nickName = userRegData.get("nickName").getAsString();
            String email = userRegData.get("email").getAsString();
            LOGGER.info("parsed reg data: " + name + " " + surname + " " + nickName + " " + email);
            String role = "user";
            Long newId = this.random.nextLong();
            User user = new User(newId, password, name, surname, nickName, email, role);
            this.userStorage.addUser(user);
            String jwt = this.jwtProducer.createJwt(String.valueOf(newId), nickName, role);
            LOGGER.info("user registration succeed: <id:" + newId + ", nick:" + nickName + ">");
            return Response.ok().header(JWT_RESPONSE_HEADER, jwt).build();
        } catch (NullPointerException e) {
            LOGGER.info("user registration failed: bad request.", e);
            return Response.status(BAD_REQUEST).build();
        }        
    }
    
    @GET
    @Path("/users/nicknames/{nick}")
    public Response checkIfNickNameIsFree(@PathParam("nick") String nick) {
        LOGGER.info("nickname usage validation...");
        if ( this.userStorage.isNickNameFree(nick) ) {
            return Response.status(NOT_FOUND).build();
        } else {
            return Response.status(FOUND).build();
        }
    }
    
    @POST
    @Path("/tokens/validation")
    public Response validateJWT(@Context ContainerRequestContext request) {
        LOGGER.info("token validation...");
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
