/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.services.webobjects;

import org.junit.Test;
import org.junit.Test.None;

import diarsid.beam.server.services.domain.exceptions.WebObjectNameInvalidException;
import diarsid.beam.server.services.domain.exceptions.WebObjectUrlInvalidException;
import diarsid.beam.server.services.domain.validation.ValidationResult;
import diarsid.beam.server.services.domain.validation.WebObjectsPropertiesValidator;

import static org.junit.Assert.*;

/**
 *
 * @author Diarsid
 */
public class WebObjectsPropertiesValidatorTest {
    
    private final WebObjectsPropertiesValidator validator;

    public WebObjectsPropertiesValidatorTest() {
        this.validator = new WebObjectsPropertiesValidator();
    }

    /**
     * Test of isWebObjectNameValid method, of class WebObjectsPropertiesValidator.
     */
    @Test
    public void testIsWebItemNameValid_valid() {
        String validName = "my name_with [1] > -name ()";
        boolean expected = true;
        
        ValidationResult result = validator.isWebObjectNameValid(validName);
        assertEquals(expected, result.isOk());
    }
    
    @Test
    public void testIsWebItemNameValid_invalid() {
        String invalidName = "my * # name_with [1] > -name () ^ @ & $ ;";
        boolean expected = false;
        
        ValidationResult result = validator.isWebObjectNameValid(invalidName);
        assertEquals(expected, result.isOk());
    }

    /**
     * Test of validateWebObjectNames method, of class WebObjectsPropertiesValidator.
     */
    @Test(expected = None.class)
    public void testValidateWebItemNames_noneThrown() {
        String validName1 = "my name_with [1]";
        String validName2 = " > -name ()";
        validator.validateWebObjectNames(validName1, validName2);
    }
    
    @Test(expected = WebObjectNameInvalidException.class)
    public void testValidateWebItemNames_exceptionThrown() {
        String validName1 = "my * $ name_with [1]";
        String validName2 = " > -name () ; &";
        validator.validateWebObjectNames(validName1, validName2);
    }

    /**
     * Test of isUrlValid method, of class WebObjectsPropertiesValidator.
     */
    @Test
    public void testIsUrlValid_valid() {
        String validUrl = "https://www.google.com.ua/";
        boolean expected = true;
        
        ValidationResult result = validator.isUrlValid(validUrl);
        assertEquals(expected, result.isOk());
    }
    
    @Test
    public void testIsUrlValid_invalid() {
        String invalidUrl = "https://www.goo gle.com.ua/";
        boolean expected = false;
        
        ValidationResult result = validator.isUrlValid(invalidUrl);
        assertEquals(expected, result.isOk());
    }

    /**
     * Test of validateUrl method, of class WebObjectsPropertiesValidator.
     */
    @Test(expected = None.class)
    public void testValidateUrl_noneThrown() {
        String validUrl = "https://www.google.com.ua/";
        validator.validateUrl(validUrl);
    }
    
    @Test(expected = WebObjectUrlInvalidException.class)
    public void testValidateUrl_exceptionThrown() {
        String invalidUrl = "https://www.goo gle.com.ua/";
        validator.validateUrl(invalidUrl);
    }

}