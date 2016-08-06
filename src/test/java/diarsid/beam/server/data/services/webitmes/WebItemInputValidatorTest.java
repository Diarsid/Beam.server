/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.services.webitmes;

import org.junit.Test;
import org.junit.Test.None;

import diarsid.beam.server.data.services.webitmes.exceptions.InvalidWebItemNameException;
import diarsid.beam.server.data.services.webitmes.exceptions.InvalidWebItemUrlException;

import static org.junit.Assert.*;

/**
 *
 * @author Diarsid
 */
public class WebItemInputValidatorTest {
    
    private final WebItemInputValidator validator;

    public WebItemInputValidatorTest() {
        this.validator = new WebItemInputValidator();
    }

    /**
     * Test of isWebItemNameValid method, of class WebItemInputValidator.
     */
    @Test
    public void testIsWebItemNameValid_valid() {
        String validName = "my name_with [1] > -name ()";
        boolean expected = true;
        
        boolean tested = validator.isWebItemNameValid(validName);
        assertEquals(expected, tested);
    }
    
    @Test
    public void testIsWebItemNameValid_invalid() {
        String invalidName = "my * # name_with [1] > -name () ^ @ & $ ;";
        boolean expected = false;
        
        boolean tested = validator.isWebItemNameValid(invalidName);
        assertEquals(expected, tested);
    }

    /**
     * Test of validateWebItemNames method, of class WebItemInputValidator.
     */
    @Test(expected = None.class)
    public void testValidateWebItemNames_noneThrown() {
        String validName1 = "my name_with [1]";
        String validName2 = " > -name ()";
        validator.validateWebItemNames(validName1, validName2);
    }
    
    @Test(expected = InvalidWebItemNameException.class)
    public void testValidateWebItemNames_exceptionThrown() {
        String validName1 = "my * $ name_with [1]";
        String validName2 = " > -name () ; &";
        validator.validateWebItemNames(validName1, validName2);
    }

    /**
     * Test of isUrlValid method, of class WebItemInputValidator.
     */
    @Test
    public void testIsUrlValid_valid() {
        String validUrl = "https://www.google.com.ua/";
        boolean expected = true;
        
        boolean tested = validator.isUrlValid(validUrl);
        assertEquals(expected, tested);
    }
    
    @Test
    public void testIsUrlValid_invalid() {
        String invalidUrl = "https://www.goo gle.com.ua/";
        boolean expected = false;
        
        boolean tested = validator.isUrlValid(invalidUrl);
        assertEquals(expected, tested);
    }

    /**
     * Test of validateUrl method, of class WebItemInputValidator.
     */
    @Test(expected = None.class)
    public void testValidateUrl_noneThrown() {
        String validUrl = "https://www.google.com.ua/";
        validator.validateUrl(validUrl);
    }
    
    @Test(expected = InvalidWebItemUrlException.class)
    public void testValidateUrl_exceptionThrown() {
        String invalidUrl = "https://www.goo gle.com.ua/";
        validator.validateUrl(invalidUrl);
    }

}