/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.entities.jpa;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 * @author Diarsid
 */

@Entity
@Table(name = "users")
public class PersistableUser implements Serializable {    
    
    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "user_id")
    private int id;
    
    @Column(name = "user_name")
    private String name;
    
    @Column(name = "user_nickname")
    private String nickname;
    
    @Column(name = "user_surname")
    private String surname;
    
    @Column(name = "user_password")
    private String password;
    
    @Column(name = "user_role")
    private String role;
    
    @Column(name = "user_email")
    private String email;
    
    public PersistableUser() {
    }
    
//    
//    public PersistableUser(UserRegistrationRequestData registrationData, String role) {
//        this.password = registrationData.getPassword();
//        this.name = registrationData.getName();
//        this.surname = registrationData.getSurname();
//        this.nickname = registrationData.getNickName();
//        this.email = registrationData.getEmail();
//        this.role = role.toUpperCase();
//    }
    
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return this.role;
    }

    public String getEmail() {
        return this.email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.nickname);
        hash = 59 * hash + Objects.hashCode(this.surname);
        hash = 59 * hash + Objects.hashCode(this.password);
        hash = 59 * hash + Objects.hashCode(this.role);
        hash = 59 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final PersistableUser other = ( PersistableUser ) obj;
        if ( !Objects.equals(this.name, other.name) ) {
            return false;
        }
        if ( !Objects.equals(this.nickname, other.nickname) ) {
            return false;
        }
        if ( !Objects.equals(this.surname, other.surname) ) {
            return false;
        }
        if ( !Objects.equals(this.password, other.password) ) {
            return false;
        }
        if ( !Objects.equals(this.role, other.role) ) {
            return false;
        }
        if ( !Objects.equals(this.email, other.email) ) {
            return false;
        }
        return true;
    }
}
