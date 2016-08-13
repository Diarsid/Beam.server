/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.domain.users;

import org.junit.Test;
import org.junit.Test.None;

import diarsid.beam.server.services.domain.exceptions.UserInfoInvalidException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
    @Test(expected = None.class)
    public void testValidateRegistrationInfo_ok() {
        validator.validateRegistrationInfo(fakeRegistration("John", "Doesson", "Cool_J.d.Nick", "john.doe.84@gmail.com", "joHn.DoE(pa[ss]Wo123rd"));
        assertTrue(true);
    }
    
    
    @Test(expected = UserInfoInvalidException.class)
    public void testValidateRegistrationInfo_nameInvalid_length() {
        validator.validateRegistrationInfo(fakeRegistration("J", "Doesson", "Cool_J.d.Nick", "john.doe.84@gmail.com", "joHn.DoE(pa[ss]Wo123rd"));
        fail();
    }
    
    @Test(expected = UserInfoInvalidException.class)
    public void testValidateRegistrationInfo_nameInvalid_content() {
        validator.validateRegistrationInfo(fakeRegistration("John87", "Doesson", "Cool_J.d.Nick", "john.doe.84@gmail.com", "joHn.DoE(pa[ss]Wo123rd"));
        fail();
    }
    
    @Test(expected = UserInfoInvalidException.class)
    public void testValidateRegistrationInfo_surnameInvalid_length() {
        validator.validateRegistrationInfo(fakeRegistration("John", "D", "Cool_J.d.Nick", "john.doe.84@gmail.com", "joHn.DoE(pa[ss]Wo123rd"));
        fail();
    }
        
    @Test(expected = UserInfoInvalidException.class)
    public void testValidateRegistrationInfo_surnameInvalid_content() {
        validator.validateRegistrationInfo(fakeRegistration("John", "Does3son(", "Cool_J.d.Nick", "john.doe.84@gmail.com", "joHn.DoE(pa[ss]Wo123rd"));
        fail();
    }
    
    @Test(expected = UserInfoInvalidException.class)
    public void testValidateRegistrationInfo_nickInvalid_length() {
        validator.validateRegistrationInfo(fakeRegistration("John", "Doesson", "Cool_J.d.N123445590dsdick1 _2345ds ws6789aassscx s sdsd", "john.doe.84@gmail.com", "joHn.DoE(pa[ss]Wo123rd"));
        fail();
    }
    
    @Test(expected = UserInfoInvalidException.class)
    public void testValidateRegistrationInfo_nickInvalid_content() {
        validator.validateRegistrationInfo(fakeRegistration("John", "Doesson", "Cool@_J.d.Nick_&", "john.doe.84@gmail.com", "joHn.DoE(pa[ss]Wo123rd"));
        fail();
    }
    
    @Test(expected = UserInfoInvalidException.class)
    public void testValidateRegistrationInfo_emailInvalid() {
        validator.validateRegistrationInfo(fakeRegistration("John", "Doesson", "Cool_J.d.Nick", "john.@doe.84@gmail.com", "joHn.DoE(pa[ss]Wo123rd"));
        fail();
    }
    
    @Test(expected = UserInfoInvalidException.class)
    public void testValidateRegistrationInfo_passInvalid_length() {
        validator.validateRegistrationInfo(fakeRegistration("John", "Doesson", "Cool_J.d.Nick", "john.doe.84@gmail.com", "joHn"));
        fail();
    }
    
    @Test(expected = UserInfoInvalidException.class)
    public void testValidateRegistrationInfo_passInvalid_content() {
        validator.validateRegistrationInfo(fakeRegistration("John", "Doesson", "Cool_J.d.Nick", "john.doe.84@gmail.com", "jo*Hn.&^%$!@Wo123rd"));
        fail();
    }
    

    /**
     * Test of validateLoginInfo method, of class UsersInfoValidator.
     */
    @Test(expected = None.class)
    public void testValidateLoginInfo_ok() {
        this.validator.validateLoginInfo(fakeLogin("j.doe_45", "123]j.doe><JD"));
        assertTrue(true);
    }
    
    @Test(expected = UserInfoInvalidException.class)
    public void testValidateLoginInfo_nickNameInvalid_length() {
        this.validator.validateLoginInfo(fakeLogin("jd", "123]j.doe><JD"));
        fail();
    }
    
    @Test(expected = UserInfoInvalidException.class)
    public void testValidateLoginInfo_nickNameInvalid_content() {
        this.validator.validateLoginInfo(fakeLogin("j.doe_45@", "123]j.doe><JD"));
        fail();
    }
    
    @Test(expected = UserInfoInvalidException.class)
    public void testValidateLoginInfo_passInvalid_length() {
        this.validator.validateLoginInfo(fakeLogin("j.doe_45", "123]f"));
        fail();
    }
    
    @Test(expected = UserInfoInvalidException.class)
    public void testValidateLoginInfo_passInvalid_content() {
        this.validator.validateLoginInfo(fakeLogin("j.doe_45@", "12oe>*&%$#<JD"));
        fail();
    }
}