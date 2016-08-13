/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.domain.users;

import org.apache.commons.validator.routines.EmailValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import diarsid.beam.server.services.domain.exceptions.UserInfoInvalidException;
import diarsid.beam.server.services.web.auth.UserLoginRequestData;
import diarsid.beam.server.services.web.auth.UserRegistrationRequestData;

import static org.apache.commons.validator.routines.EmailValidator.getInstance;

/**
 *
 * @author Diarsid
 */
public class UsersInfoValidator {
    
    private static final Logger logger;
    static {
        logger = LoggerFactory.getLogger(UsersInfoValidator.class);
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
    
    public UsersInfoValidator() {
    }
    
    public void validateRegistrationInfo(UserRegistrationRequestData registration) {
        if ( ! this.isEmailValid(registration.getEmail()) ) {
            logger.debug("Registration: email " + registration.getEmail() + " is invalid. ");
            throw new UserInfoInvalidException(
                    "Email " + registration.getEmail() + " is invalid. " + 
                    "Email format should satisfy common requirements. " +
                    "Given email has been validated by Apache Commons Validator 1.4.0.");
        }
        if ( ! this.isNameValid(registration.getName()) ) {
            logger.debug("Registration: name " + registration.getName() + " is invalid. ");
            throw new UserInfoInvalidException(
                    "Registration failed - name " + registration.getName() + " is invalid. " + 
                    "Name can contain only latin alphabet characters, spaces and - character. " + 
                    "Name should be no less than 2 characters and no more than 300 characters length.");
        }
        if ( ! this.isNameValid(registration.getSurname()) ) {
            logger.debug("Registration: surname " + registration.getSurname()+ " is invalid. ");
            throw new UserInfoInvalidException(
                    "Registration failed - surname " + registration.getSurname() + " is invalid. " + 
                    "Name can contain only latin alphabet characters, spaces and - character. " +
                    "Surname should be no less than 2 characters and no more than 300 characters length.");
        }
        if ( ! this.isNickValid(registration.getNickName()) ) {
            logger.debug("Registration: nickname " + registration.getNickName()+ " is invalid. ");
            throw new UserInfoInvalidException(
                    "Registration failed - nickname " + registration.getNickName() + " is invalid. " + 
                    "Nickname can contain only latin alphabet characters, spaces, numeric characters " +
                    "and - _ . characters. " +
                    "Nickname should be no less than 3 characters and no more than 30 characters length. ");
        }
        if ( ! this.isPassValid(registration.getPassword()) ) {
            logger.debug("Registration: password " + registration.getPassword() + " is invalid. ");
            throw new UserInfoInvalidException(
                    "Registration failed - password " + registration.getPassword() + " is invalid. " + 
                    "Password can contain only latin alphabet characters, spaces, numeric characters " +
                    "and - _ . < > ( ) [ ] characters. " +
                    "Password should be no less than 6 characters and no more than 32 characters length.");
        }
    }
    
    public void validateLoginInfo(UserLoginRequestData login) {
        logger.debug("Login: nickname " + login.getNickName()+ " is invalid. ");
        if ( ! this.isNickValid(login.getNickName()) ) {
            throw new UserInfoInvalidException(
                    "Registration failed - nickname " + login.getNickName() + " is invalid. " + 
                    "Nickname can contain only latin alphabet characters, spaces, numeric characters " +
                    "and - _ . characters. " +
                    "Nickname should be no less than 3 characters and no more than 30 characters length. ");
        }
        if ( ! this.isPassValid(login.getPassword()) ) {
            logger.debug("Login: password " + login.getPassword() + " is invalid. ");
            throw new UserInfoInvalidException(
                    "Registration failed - password " + login.getPassword() + " is invalid. " + 
                    "Password can contain only latin alphabet characters, spaces, numeric characters " +
                    "and - _ . < > ( ) [ ] characters. " +
                    "Password should be no less than 6 characters and no more than 32 characters length.");
        }
    }
    
    public boolean isPassValid(String pass) {
        boolean valid = 
                pass.matches(ACCEPTABLE_PASSWORD_REGEXP) &&
                pass.length() >= PASSWORD_MIN_LENGTH &&
                pass.length() <= PASSWORD_MAX_LENGTH;
        return valid;
    }

    public boolean isNameValid(String name) {
        boolean valid = 
                name.matches(ACCEPTABLE_NAME_REGEXP) && 
                name.length() >= NAME_MIN_LENGTH && 
                name.length() <= NAME_MAX_LENGTH;
        return valid;
    }
    
    public boolean isNickValid(String nick) {
        boolean valid = 
                nick.matches(ACCEPTABLE_NICKNAME_REGEXP) &&
                nick.length() >= NICKNAME_MIN_LENGTH &&
                nick.length() <= NICKNAME_MAX_LENGTH;
        return valid;
    }

    public boolean isEmailValid(String email) {
        return EMAIL_VALIDATOR.isValid(email);
    }
}
