/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.services.webobjects;

import org.apache.commons.validator.routines.UrlValidator;

import org.springframework.stereotype.Component;

import diarsid.beam.server.data.services.webobjects.exceptions.InvalidWebObjectNameException;
import diarsid.beam.server.data.services.webobjects.exceptions.InvalidWebObjectUrlException;

/**
 *
 * @author Diarsid
 */

@Component
public class WebObjectsPropertiesValidator {
    
    private final static String ACCEPTABLE_WEB_ITEM_NAME_REGEXP;
    private final static UrlValidator URL_VALIDATOR;
    static {
        ACCEPTABLE_WEB_ITEM_NAME_REGEXP = "[a-zA-Z0-9-_\\(\\)\\[\\]\\.>\\s]+";
        URL_VALIDATOR = new UrlValidator();
    }
    
    public WebObjectsPropertiesValidator() {
    }
    
    public boolean isWebItemNameValid(String webItemName) {
        return webItemName.matches(ACCEPTABLE_WEB_ITEM_NAME_REGEXP);
    }
    
    public void validateWebItemNames(String... webItemNames) {
        for (String name : webItemNames) {
            if ( ! name.matches(ACCEPTABLE_WEB_ITEM_NAME_REGEXP) ) {
                throw new InvalidWebObjectNameException("WebItem name " + name + " is invalid.");
            }
        }
    }
    
    public boolean isUrlValid(String url) {
        return URL_VALIDATOR.isValid(url);
    }
    
    public void validateUrl(String url) {
        if ( ! URL_VALIDATOR.isValid(url) ) {
            throw new InvalidWebObjectUrlException("WebItem URL " + url + " is invalid.");
        }
    }
}
