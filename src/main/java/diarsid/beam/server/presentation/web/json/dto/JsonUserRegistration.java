/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.json.dto;

import diarsid.beam.server.domain.entities.jpa.PersistableUser;

import static diarsid.beam.server.presentation.web.services.auth.UserRole.USER;

/**
 *
 * @author Diarsid
 */
public class JsonUserRegistration {
    
    private final String name;
    private final String surname;    
    private final String nickName;
    private final String password;
    private final String email;
    
    public JsonUserRegistration(
            String name, String surname, String nick, String email, String pass) {
        this.nickName = nick;
        this.password = pass;
        this.name = name;
        this.email = email;
        this.surname = surname;
    }
    
    public PersistableUser composeNewUnpersistedUser() {
        PersistableUser user = new PersistableUser();
        user.setName(this.name);
        user.setNickname(this.nickName);
        user.setSurname(this.surname);
        user.setPassword(this.password);
        user.setEmail(this.email);
        user.setRole(USER.name());
        return user;
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
