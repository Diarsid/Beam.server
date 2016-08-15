/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.domain.users;

import org.junit.Test;

import diarsid.beam.server.services.domain.validation.UsersInfoValidator;
import diarsid.beam.server.services.domain.validation.ValidationResult;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static util.FakeUserDataProducer.fakeLogin;
import static util.FakeUserDataProducer.fakeRegistration;

/**
 *
 * @author Diarsid
 */
public class UsersInfoValidatorTest {
    
    private final UsersInfoValidator validator = new UsersInfoValidator();
    
    public UsersInfoValidatorTest() {
    }
    
    // fakeRegistration("John", "Doesson", "Cool_J.d.Nick", "john.doe.84@gmail.com", "joHn.DoE(pa[ss]Wo123rd")

    /**
     * Test of validateRegistrationInfo method, of class UsersInfoValidator.
     */
    @Test
    public void testValidateRegistrationInfo_ok() {
        ValidationResult result = validator.validateRegistrationInfo(fakeRegistration("John", "Doesson", "Cool_J.d.Nick", "john.doe.84@gmail.com", "joHn.DoE(pa[ss]Wo123rd"));
        assertTrue(result.isOk());
    }
    
    
    @Test
    public void testValidateRegistrationInfo_nameInvalid_length() {
        ValidationResult result = validator.validateRegistrationInfo(fakeRegistration("J", "Doesson", "Cool_J.d.Nick", "john.doe.84@gmail.com", "joHn.DoE(pa[ss]Wo123rd"));
        assertFalse(result.isOk());
    }
    
    @Test
    public void testValidateRegistrationInfo_nameInvalid_content() {
        ValidationResult result = validator.validateRegistrationInfo(fakeRegistration("John87", "Doesson", "Cool_J.d.Nick", "john.doe.84@gmail.com", "joHn.DoE(pa[ss]Wo123rd"));
        assertFalse(result.isOk());
    }
    
    @Test
    public void testValidateRegistrationInfo_surnameInvalid_length() {
        ValidationResult result = validator.validateRegistrationInfo(fakeRegistration("John", "D", "Cool_J.d.Nick", "john.doe.84@gmail.com", "joHn.DoE(pa[ss]Wo123rd"));
        assertFalse(result.isOk());
    }
        
    @Test
    public void testValidateRegistrationInfo_surnameInvalid_content() {
        ValidationResult result = validator.validateRegistrationInfo(fakeRegistration("John", "Does3son(", "Cool_J.d.Nick", "john.doe.84@gmail.com", "joHn.DoE(pa[ss]Wo123rd"));
        assertFalse(result.isOk());
    }
    
    @Test
    public void testValidateRegistrationInfo_nickInvalid_length() {
        ValidationResult result = validator.validateRegistrationInfo(fakeRegistration("John", "Doesson", "Cool_J.d.N123445590dsdick1 _2345ds ws6789aassscx s sdsd", "john.doe.84@gmail.com", "joHn.DoE(pa[ss]Wo123rd"));
        assertFalse(result.isOk());
    }
    
    @Test
    public void testValidateRegistrationInfo_nickInvalid_content() {
        ValidationResult result = validator.validateRegistrationInfo(fakeRegistration("John", "Doesson", "Cool@_J.d.Nick_&", "john.doe.84@gmail.com", "joHn.DoE(pa[ss]Wo123rd"));
        assertFalse(result.isOk());
    }
    
    @Test
    public void testValidateRegistrationInfo_emailInvalid() {
        ValidationResult result = validator.validateRegistrationInfo(fakeRegistration("John", "Doesson", "Cool_J.d.Nick", "john.@doe.84@gmail.com", "joHn.DoE(pa[ss]Wo123rd"));
        assertFalse(result.isOk());
    }
    
    @Test
    public void testValidateRegistrationInfo_passInvalid_length() {
        ValidationResult result = validator.validateRegistrationInfo(fakeRegistration("John", "Doesson", "Cool_J.d.Nick", "john.doe.84@gmail.com", "joHn"));
        assertFalse(result.isOk());
    }
    
    @Test
    public void testValidateRegistrationInfo_passInvalid_content() {
        ValidationResult result = validator.validateRegistrationInfo(fakeRegistration("John", "Doesson", "Cool_J.d.Nick", "john.doe.84@gmail.com", "jo*Hn.&^%$!@Wo123rd"));
        assertFalse(result.isOk());
    }
    

    /**
     * Test of validateLoginInfo method, of class UsersInfoValidator.
     */
    @Test
    public void testValidateLoginInfo_ok() {
        ValidationResult result = this.validator.validateLoginInfo(fakeLogin("j.doe_45", "123]j.doe><JD"));
        assertTrue(result.isOk());
    }
    
    @Test
    public void testValidateLoginInfo_nickNameInvalid_length() {
        ValidationResult result = this.validator.validateLoginInfo(fakeLogin("jd", "123]j.doe><JD"));
        assertFalse(result.isOk());
    }
    
    @Test
    public void testValidateLoginInfo_nickNameInvalid_content() {
        ValidationResult result = this.validator.validateLoginInfo(fakeLogin("j.doe_45@", "123]j.doe><JD"));
        assertFalse(result.isOk());
    }
    
    @Test
    public void testValidateLoginInfo_passInvalid_length() {
        ValidationResult result = this.validator.validateLoginInfo(fakeLogin("j.doe_45", "123]f"));
        assertFalse(result.isOk());
    }
    
    @Test
    public void testValidateLoginInfo_passInvalid_content() {
        ValidationResult result = this.validator.validateLoginInfo(fakeLogin("j.doe_45@", "12oe>*&%$#<JD"));
        assertFalse(result.isOk());
    }
}