/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import diarsid.beam.server.data.entities.jpa.PersistableUser;

/**
 *
 * @author Diarsid
 */
public class FakeUserProducer {
    
    public final static String NAME_TEMPLATE;
    public final static String SURNAME_TEMPLATE;
    public final static String NICKNAME_TEMPLATE;
    public final static String PASSWORD_TEMPLATE;
    public final static String EMAIL_TEMPLATE;
    public final static String ROLE_IS_USER;
    static {
        NAME_TEMPLATE = "name_";
        SURNAME_TEMPLATE = "surname_";
        NICKNAME_TEMPLATE = "nick_";
        PASSWORD_TEMPLATE = "pass_";
        EMAIL_TEMPLATE = "email_";
        ROLE_IS_USER = "user";
    }
                
    private FakeUserProducer() {
    }    
    
    public static PersistableUser newFakeUser(int i) {
        PersistableUser newUser = new PersistableUser();
        newUser.setName(NAME_TEMPLATE + i);
        newUser.setSurname(SURNAME_TEMPLATE + i);
        newUser.setNickname(NICKNAME_TEMPLATE + i);
        newUser.setPassword(PASSWORD_TEMPLATE + i);
        newUser.setEmail(EMAIL_TEMPLATE + i);
        newUser.setRole(ROLE_IS_USER);
        return newUser;
    }
}
