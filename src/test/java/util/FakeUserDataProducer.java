/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import diarsid.beam.server.presentation.web.json.dto.JsonUserLogin;
import diarsid.beam.server.presentation.web.json.dto.JsonUserRegistration;

/**
 *
 * @author Diarsid
 */
public class FakeUserDataProducer {
    
    private FakeUserDataProducer() {
    }
    
    public static JsonUserRegistration fakeRegistration(String name, String surname, String nick, String email, String pass) {
        return new JsonUserRegistration(name, surname, nick, email, pass);
    }
    
    public static JsonUserLogin fakeLogin(String nick, String pass) {
        return new JsonUserLogin(nick, pass);
    }
    
//    public static JsonUserRegistration fakeRegistrationWithInvalid(String field) {
//        field = field.trim().toLowerCase();
//        JsonUserRegistration registration;
//        switch ( field ) {
//            case "name" : {
//                
//            }
//        }
//    }
}
