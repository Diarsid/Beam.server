/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.web.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import diarsid.beam.server.services.domain.users.UsersService;
import diarsid.beam.server.services.domain.validation.UsersValidationService;
import diarsid.beam.server.services.domain.validation.ValidatablePayload;
import diarsid.beam.server.services.domain.validation.ValidationResult;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.FOUND;
import static javax.ws.rs.core.Response.Status.OK;

import static diarsid.beam.server.services.web.providers.JaxRsResponseComposer.composeResponseFrom;


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
    
    @GET
    @Path("/names") 
    public Response validateName(ValidatablePayload payload) {
        ValidationResult result = this.validationService.validateName(payload.get());
        if ( result.isOk() ) {
            return Response.status(OK).build();
        } else {
            return composeResponseFrom(SC_BAD_REQUEST, result);
        }
    }
    
    @GET
    @Path("/surnames") 
    public Response validateSurname(ValidatablePayload payload) {
        ValidationResult result = this.validationService.validateName(payload.get());
        if ( result.isOk() ) {
            return Response.status(OK).build();
        } else {
            return composeResponseFrom(SC_BAD_REQUEST, result);
        }
    }
    
    @GET
    @Path("/free-nicknames")
    public Response validateFreeNickName(ValidatablePayload payload) {
        logger.info("free nickname usage validation...");        
        ValidationResult result = this.validationService.validateNick(payload.get());
        if ( result.isOk() ) {
            if ( this.usersService.isNicknameFree(payload.get()) ) {
                return Response.status(OK).build();
            } else {
                return Response.status(FOUND).build();
            }            
        } else {
            return composeResponseFrom(SC_BAD_REQUEST, result);
        }
    }
    
    @GET
    @Path("/nicknames")
    public Response validateNickName(ValidatablePayload payload) {
        logger.info("nickname usage validation...");        
        ValidationResult result = this.validationService.validateNick(payload.get());
        if ( result.isOk() ) {
            return Response.status(OK).build();            
        } else {
            return composeResponseFrom(SC_BAD_REQUEST, result);
        }
    }
    
    @GET
    @Path("/passwords")
    public Response validatePassword(ValidatablePayload payload) {
        ValidationResult result = validationService.validatePassword(payload.get());
        if ( result.isOk() ) {
            return Response.status(OK).build();
        } else {
            return composeResponseFrom(SC_BAD_REQUEST, result);
        }
    }
    
    @GET
    @Path("/emails")
    public Response validateEmail(ValidatablePayload payload) {
        ValidationResult result = this.validationService.validateEmail(payload.get());
        if ( result.isOk() ) {
            return Response.status(OK).build();
        } else {
            return composeResponseFrom(SC_BAD_REQUEST, result);
        }
    }
}
