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
public class User {    
    
    private long id;
    
    private String name;
    private String surname;
    private String password;
    private String nickName;
    private String email;
    private String role;
    
    // default null constructor.
    public User() {
        this.id = 0;
        this.name = "";
        this.surname = "";
        this.nickName = "";
        this.email = "";
        this.role = "";
    }
    
    // authenticated User creation
    User(long id, String name, String surname, String nickname, String email, String role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.nickName = nickname;
        this.email = email;
        this.role = role;
    }
    
    // NON-authenticated User creation
    User(String role) {
        this.id = 0;
        this.name = "";
        this.surname = "";
        this.nickName = "";
        this.email = "";
        this.role = role;
    }
        
    public String getRole() {
        return this.role;
    }

    public long getId() {
        return id;
    }
    
    public String getIdString() {
        return Long.toString(this.id);
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getPassword() {
        return this.password;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getEmail() {
        return this.email;
    }
}
