/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.domain.services.validation;

import org.apache.commons.validator.routines.UrlValidator;

import org.springframework.stereotype.Component;

import diarsid.beam.server.domain.services.exceptions.WebObjectNameInvalidException;
import diarsid.beam.server.domain.services.exceptions.WebObjectUrlInvalidException;

import static diarsid.beam.server.domain.services.validation.ValidationResultImpl.validationFailsWith;
import static diarsid.beam.server.domain.services.validation.ValidationResultImpl.validationOk;

/**
 *
 * @author Diarsid
 */

@Component
public class WebObjectsValidationServiceWorker implements WebObjectsValidationService {
    
    private static final String ACCEPTABLE_WEB_OBJECT_NAME_REGEXP;
    private static final UrlValidator URL_VALIDATOR;
    
    private static final int NAME_MIN_LENGTH;
    private static final int NAME_MAX_LENGTH;
    
    static {
        ACCEPTABLE_WEB_OBJECT_NAME_REGEXP = "[a-zA-Z0-9-_\\(\\)\\[\\]\\.>\\s]+";
        URL_VALIDATOR = new UrlValidator();
        
        NAME_MIN_LENGTH = 1;
        NAME_MAX_LENGTH = 500;
    }
    
    public WebObjectsValidationServiceWorker() {
    }
    
    @Override
    public ValidationResult isWebObjectNameValid(String name) {
        if ( name.length() < NAME_MIN_LENGTH ) {
            return validationFailsWith(this.composeNameMinLengthInvalidMessage());
        }
        if ( name.length() > NAME_MAX_LENGTH ) {
            return validationFailsWith(this.composeNameMaxLengthInvalidMessage());
        }
        if ( ! name.matches(ACCEPTABLE_WEB_OBJECT_NAME_REGEXP) ) {
            return validationFailsWith(this.composeNameInvalidCharactersMessage());
        }
        return validationOk();
    }
    
    @Override
    public ValidationResult isUrlValid(String url) {
        if ( URL_VALIDATOR.isValid(url) ) {
            return validationOk();
        } else {
            return validationFailsWith(this.composeUrlInvalidMessage(url));
        }
    }
        
    @Override
    public void validateWebObjectNames(String... names) {
        for (String name : names) {
            if ( name.length() < NAME_MIN_LENGTH ) {
                throw new WebObjectNameInvalidException(this.composeNameMinLengthInvalidMessage());
            }
            if ( name.length() > NAME_MAX_LENGTH ) {
                throw new WebObjectNameInvalidException(this.composeNameMaxLengthInvalidMessage());
            }
            if ( ! name.matches(ACCEPTABLE_WEB_OBJECT_NAME_REGEXP) ) {
                throw new WebObjectNameInvalidException(this.composeNameInvalidCharactersMessage());
            }
        }
    }
    
    @Override
    public void validateUrl(String url) {
        if ( ! URL_VALIDATOR.isValid(url) ) {
            throw new WebObjectUrlInvalidException(this.composeUrlInvalidMessage(url));
        }
    }

    private String composeUrlInvalidMessage(String url) {
        return "WebObject URL " + url + " is invalid. It should be of an acceptable format.";
    }  
    
    private String composeNameMinLengthInvalidMessage() {
        return "WebObject name should have length no less than " + NAME_MIN_LENGTH + ".";
    }
    
    private String composeNameMaxLengthInvalidMessage() {
        return "WebObject name should have length and no more than " + NAME_MAX_LENGTH + ".";
    }
        
    private String composeNameInvalidCharactersMessage() {
        return "WebObject name can contain only characters a-z, A-Z, 0-9, spaces " +
                "and following special characters . - _ ( ) [ ] >";
    }
}
