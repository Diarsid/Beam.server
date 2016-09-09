/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import diarsid.beam.server.presentation.web.json.dto.UserLoginRequestData;
import diarsid.beam.server.presentation.web.json.dto.UserRegistrationRequestData;

/**
 *
 * @author Diarsid
 */
public class FakeUserDataProducer {
    
    private FakeUserDataProducer() {
    }
    
    public static UserRegistrationRequestData fakeRegistration(String name, String surname, String nick, String email, String pass) {
        return new UserRegistrationRequestData(name, surname, nick, email, pass);
    }
    
    public static UserLoginRequestData fakeLogin(String nick, String pass) {
        return new UserLoginRequestData(nick, pass);
    }
    
//    public static UserRegistrationRequestData fakeRegistrationWithInvalid(String field) {
//        field = field.trim().toLowerCase();
//        UserRegistrationRequestData registration;
//        switch ( field ) {
//            case "name" : {
//                
//            }
//        }
//    }
}
