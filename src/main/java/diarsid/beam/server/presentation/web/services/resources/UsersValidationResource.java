/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.services.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import diarsid.beam.server.domain.services.users.UsersService;
import diarsid.beam.server.domain.services.validation.UsersValidationService;
import diarsid.beam.server.domain.services.validation.ValidationResult;
import diarsid.beam.server.presentation.web.json.dto.JsonPayload;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.FOUND;
import static javax.ws.rs.core.Response.Status.OK;

import static diarsid.beam.server.presentation.web.services.providers.JaxRsResponseComposer.composeResponseFrom;


/**
 *
 * @author Diarsid
 */

@Component
@Path("/validation/users")
public class UsersValidationResource {
    
    private static final Logger logger;
    static {
        logger = LoggerFactory.getLogger(UsersValidationResource.class);
    }
    
    private final UsersValidationService validationService;
    private final UsersService usersService;
    
    public UsersValidationResource(UsersValidationService validation, UsersService usersService) {
        this.validationService = validation;
        this.usersService = usersService;
    }
    
    @POST
    @Path("/names") 
    public Response validateName(JsonPayload payload) {
        logger.info("name validation..." + payload.get());
        ValidationResult result = this.validationService.validateName(payload.get());
        if ( result.isOk() ) {
            logger.info(" -ok.");
            return Response.status(OK).build();
        } else {
            logger.info(" -failed: " + result.getFailureMessage());
            return composeResponseFrom(SC_BAD_REQUEST, result);
        }
    }
    
    @POST
    @Path("/surnames") 
    public Response validateSurname(JsonPayload payload) {
        logger.info("surname validation..." + payload.get());
        ValidationResult result = this.validationService.validateName(payload.get());
        if ( result.isOk() ) {
            logger.info(" -ok.");
            return Response.status(OK).build();
        } else {
            logger.info(" -failed: " + result.getFailureMessage());
            return composeResponseFrom(SC_BAD_REQUEST, result);
        }
    }
    
    @POST
    @Path("/free-nicknames")
    public Response validateFreeNickName(JsonPayload payload) {
        logger.info("free nickname validation..." + payload.get());        
        ValidationResult result = this.validationService.validateNick(payload.get());
        if ( result.isOk() ) {
            if ( this.usersService.isNicknameFree(payload.get()) ) {
                logger.info(" -ok.");
                return Response.status(OK).build();                
            } else {
                logger.info("nickname is not free.");
                return Response.status(FOUND).build();
            }            
        } else {
            logger.info(" -failed: " + result.getFailureMessage());
            return composeResponseFrom(SC_BAD_REQUEST, result);
        }
    }
    
    @POST
    @Path("/nicknames")
    public Response validateNickName(JsonPayload payload) {        
        logger.info("nickname validation..." + payload.get());
        ValidationResult result = this.validationService.validateNick(payload.get());
        if ( result.isOk() ) {
            logger.info(" -ok.");
            return Response.status(OK).build();      
        } else {
            logger.info(" -failed: " + result.getFailureMessage());
            return composeResponseFrom(SC_BAD_REQUEST, result);
        }
    }
    
    @POST
    @Path("/passwords")
    public Response validatePassword(JsonPayload payload) {
        logger.info("password validation..." + payload.get());
        ValidationResult result = validationService.validatePassword(payload.get());
        if ( result.isOk() ) {
            logger.info(" -ok.");
            return Response.status(OK).build();      
        } else {
            logger.info(" -failed: " + result.getFailureMessage());
            return composeResponseFrom(SC_BAD_REQUEST, result);
        }
    }
    
    @POST
    @Path("/emails")
    public Response validateEmail(JsonPayload payload) {
        logger.info("email validation..." + payload.get());
        ValidationResult result = this.validationService.validateEmail(payload.get());
        if ( result.isOk() ) {
            logger.info(" -ok.");
            return Response.status(OK).build();
        } else {
            logger.info(" -failed: " + result.getFailureMessage());
            return composeResponseFrom(SC_BAD_REQUEST, result);
        }
    }
}
