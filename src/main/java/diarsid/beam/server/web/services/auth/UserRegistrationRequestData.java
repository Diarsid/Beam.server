/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.web.services.auth;

/**
 *
 * @author Diarsid
 */
public class UserRegistrationRequestData {
    
    private final String name;
    private final String surname;    
    private final String nickName;
    private final String password;
    private final String email;
    
    public UserRegistrationRequestData(
            String name, String surname, String nick, String email, String pass) {
        this.nickName = nick;
        this.password = pass;
        this.name = name;
        this.email = email;
        this.surname = surname;
    }
    
    public String getNickName() {
        return this.nickName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getEmail() {
        return this.email;
    }
}
