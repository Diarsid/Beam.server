/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.entities.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

import diarsid.beam.server.data.entities.OrderableWebObject;
import diarsid.beam.server.data.entities.OrderableWebObjectsCollection;


/**
 *
 * @author Diarsid
 */

@Entity
@Table(name = "users")
public class PersistableUserDirs 
        implements Serializable, 
                   OrderableWebObjectsCollection {    
        
    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "user_id")
    private long id;
    
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
    
    @OneToMany(
            mappedBy = "user",
            fetch = FetchType.EAGER, 
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<PersistableWebDirectory> dirs;
    
    public PersistableUserDirs() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public List<PersistableWebDirectory> getDirs() {
        return dirs;
    }
    
    @Override
    public List<? extends OrderableWebObject> getObjects() {
        return (List<? extends OrderableWebObject>) this.dirs;
    }

    public void setId(long id) {
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

    public void setDirs(List<PersistableWebDirectory> dirs) {
        this.dirs = dirs;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.nickname);
        hash = 79 * hash + Objects.hashCode(this.surname);
        hash = 79 * hash + Objects.hashCode(this.password);
        hash = 79 * hash + Objects.hashCode(this.role);
        hash = 79 * hash + Objects.hashCode(this.email);
        hash = 79 * hash + Objects.hashCode(this.dirs);
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
        final PersistableUserDirs other = ( PersistableUserDirs ) obj;
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
        if ( !Objects.equals(this.dirs, other.dirs) ) {
            return false;
        }
        return true;
    }
}
