/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.domain.services.validation;

import org.apache.commons.validator.routines.EmailValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import diarsid.beam.server.presentation.web.json.dto.JsonUserLogin;
import diarsid.beam.server.presentation.web.json.dto.JsonUserRegistration;

import static org.apache.commons.validator.routines.EmailValidator.getInstance;

import static diarsid.beam.server.domain.services.validation.ValidationResultImpl.validationFailsWith;
import static diarsid.beam.server.domain.services.validation.ValidationResultImpl.validationOk;

/**
 *
 * @author Diarsid
 */
public class UsersValidationServiceWorker implements UsersValidationService {
    
    private static final Logger logger;
    static {
        logger = LoggerFactory.getLogger(UsersValidationServiceWorker.class);
    }
    
    private static final String ACCEPTABLE_NICKNAME_REGEXP;
    private static final String ACCEPTABLE_NAME_REGEXP;
    private static final String ACCEPTABLE_PASSWORD_REGEXP;
    
    private static final EmailValidator EMAIL_VALIDATOR;
    
    private static final int NAME_MIN_LENGTH;
    private static final int NICKNAME_MIN_LENGTH;
    private static final int PASSWORD_MIN_LENGTH;
    
    private static final int NAME_MAX_LENGTH;
    private static final int NICKNAME_MAX_LENGTH;
    private static final int PASSWORD_MAX_LENGTH;
    
    static {        
        ACCEPTABLE_NAME_REGEXP = "[a-zA-Z\\s-]+";
        ACCEPTABLE_NICKNAME_REGEXP = "[a-zA-Z0-9-_\\.\\s]+";
        ACCEPTABLE_PASSWORD_REGEXP = "[a-zA-Z0-9-_\\(\\)\\[\\]\\.<>\\s]+";
        
        EMAIL_VALIDATOR = getInstance();
        
        NAME_MIN_LENGTH = 2;
        NICKNAME_MIN_LENGTH = 3;
        PASSWORD_MIN_LENGTH = 6;
        
        NAME_MAX_LENGTH = 300;
        NICKNAME_MAX_LENGTH = 30;
        PASSWORD_MAX_LENGTH = 32;
    }
    
    public UsersValidationServiceWorker() {
    }
    
    @Override
    public ValidationResult validateRegistrationInfo(JsonUserRegistration registration) {
        ValidationResult result;
        result = this.validateName(registration.getName());
        if ( ! result.isOk() ) {
            return result;
        }
        result = this.validateName(registration.getSurname());
        if ( ! result.isOk() ) {
            return result;
        }
        result = this.validateNick(registration.getNickName());
        if ( ! result.isOk() ) {
            return result;
        }
        result = this.validateEmail(registration.getEmail());
        if ( ! result.isOk() ) {
            return result;
        }
        result = this.validatePassword(registration.getPassword());
        if ( ! result.isOk() ) {
            return result;
        }
        return validationOk();
    }
    
    @Override
    public ValidationResult validateLoginInfo(JsonUserLogin login) {
        ValidationResult result;
        result = this.validateNick(login.getNickName());
        if ( ! result.isOk() ) {
            return result;
        }
        result = this.validatePassword(login.getPassword());
        if ( ! result.isOk() ) {
            return result;
        }
        return validationOk();
    }
    
    @Override
    public ValidationResult validatePassword(String pass) {        
        if ( pass.length() < PASSWORD_MIN_LENGTH ) {
            return validationFailsWith(this.composePassMinLengthInvalidMessage());
        }
        if ( pass.length() > PASSWORD_MAX_LENGTH ) {
            return validationFailsWith(this.composePassMaxLengthInvalidMessage());
        }
        if ( ! pass.matches(ACCEPTABLE_PASSWORD_REGEXP) ) {
            return validationFailsWith(this.composePassCharactersInvalidMessage());
        }
        return validationOk();
    }
    
    @Override
    public ValidationResult validateName(String name) {        
        if ( name.length() < NAME_MIN_LENGTH ) {
            return validationFailsWith(this.composeNameMinLengthInvalidMessage());
        }
        if ( name.length() > NAME_MAX_LENGTH ) {
            return validationFailsWith(this.composeNameMaxLengthInvalidMessage());
        }
        if ( ! name.matches(ACCEPTABLE_NAME_REGEXP) ) {
            return validationFailsWith(this.composeNameCharactersInvalidMessage());
        }
        return validationOk();
    }
    
    @Override
    public ValidationResult validateNick(String nick) {        
        if ( nick.length() < NICKNAME_MIN_LENGTH ) {
            return validationFailsWith(this.composeNicknameMinLengthInvalidMessage());
        }
        if ( nick.length() > NICKNAME_MAX_LENGTH ) {
            return validationFailsWith(this.composeNicknameMaxLengthInvalidMessage());
        }
        if ( ! nick.matches(ACCEPTABLE_NICKNAME_REGEXP) ) {
            return validationFailsWith(this.composeNicknameCharactersInvalidMessage());
        }
        return validationOk();
    }
    
    @Override
    public ValidationResult validateEmail(String email) {
        if ( EMAIL_VALIDATOR.isValid(email) ) {
            return validationOk();
        } else {
            return validationFailsWith(this.composeEmailInvalidMessage());
        }
    }
    
    private String composePassMaxLengthInvalidMessage() {
        return "Should be no longer than " + PASSWORD_MAX_LENGTH + ".";
    }
    
    private String composePassMinLengthInvalidMessage() {
        return "Should be longer than " + (PASSWORD_MIN_LENGTH-1) + ".";
    }
    
    private String composePassCharactersInvalidMessage() {
        return "Can contain only spaces, a-z, A-Z, 0-9, and - _ . < > ( ) [ ] characters. ";
    }

    private String composeNicknameMinLengthInvalidMessage() {
        return "Should be longer than " + (NICKNAME_MIN_LENGTH-1) + ".";
    }
    
    private String composeNicknameMaxLengthInvalidMessage() {
        return "Should be no longer than " + NICKNAME_MAX_LENGTH + ".";
    }
    
    private String composeNicknameCharactersInvalidMessage() {
        return "Can contain only spaces, . - _. a-z, A-Z, 0-9.";
    }

    private String composeNameMinLengthInvalidMessage() {
        return "Should be longer than " + (NAME_MIN_LENGTH-1) + " characters.";
    }
    
    private String composeNameMaxLengthInvalidMessage() {
        return "Should be no longer than " + NAME_MAX_LENGTH + ".";
    }
    
    private String composeNameCharactersInvalidMessage() {
        return "Can contain only spaces, a-z, A-Z, and -. ";
    }

    private String composeEmailInvalidMessage() {
        return "Invalid email format. ";
    }
}
