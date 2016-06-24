/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.entities;

/**
 *
 * @author Diarsid
 */
public class Users {    
    
    private final static String PASSWORD_REGEXP;
    private final static String EMAIL_REGEXP;
    private final static String NAME_REGEXP;

    static {
        PASSWORD_REGEXP = "";
        EMAIL_REGEXP = "";
        NAME_REGEXP = "";
    }
    
    private Users() {
    }
    
    
    public static User getAuthenticatedUser(long id, String name, String surname, String nickname, String email, String role) {
        return new User(id, name, surname, nickname, email, role);
    }
    
    public static User getNonAuthenticatedUser() {
        return new User("none");
    }

    public static User getFAKEAuthenticatedUser() {
        return new User(1234567890, "John", "Doe", "Jonny", "john.doe@gmail.com", "user");
    }    
}
