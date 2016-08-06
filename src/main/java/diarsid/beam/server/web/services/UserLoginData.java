/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.web.services;

/**
 *
 * @author Diarsid
 */
public class UserLoginData {
    
    private final String nickName;
    private final String password;
    
    public UserLoginData(String nick, String pass) {
        this.nickName = nick;
        this.password = pass;
    }

    public String getNickName() {
        return this.nickName;
    }

    public String getPassword() {
        return this.password;
    }
}
