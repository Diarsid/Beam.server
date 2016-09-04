/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.domain.jwtauth;

import java.util.Objects;

/**
 *
 * @author Diarsid
 */
public class JwtUserInfo {
    
    private final String id;
    private final String nickName;
    private final String role;
    
    public JwtUserInfo(String id, String nick, String role) {
        this.id = id;
        this.nickName = nick;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getNickName() {
        return nickName;
    }

    public String getRole() {
        return role;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        hash = 31 * hash + Objects.hashCode(this.nickName);
        hash = 31 * hash + Objects.hashCode(this.role);
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
        final JwtUserInfo other = ( JwtUserInfo ) obj;
        if ( !Objects.equals(this.id, other.id) ) {
            return false;
        }
        if ( !Objects.equals(this.nickName, other.nickName) ) {
            return false;
        }
        if ( !Objects.equals(this.role, other.role) ) {
            return false;
        }
        return true;
    }
}
