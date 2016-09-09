/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.domain.entities;

import java.security.Key;
import java.util.Objects;

/**
 *
 * @author Diarsid
 */
public class KeyIdPair {
    
    private final Key key;
    private final String id;
    
    public KeyIdPair(String id, Key key) {
        this.id = id;
        this.key = key;
    }

    public Key getKey() {
        return key;
    }

    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.key);
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final KeyIdPair other = ( KeyIdPair ) obj;
        if ( !Objects.equals(this.id, other.id) ) {
            return false;
        }
        if ( !Objects.equals(this.key, other.key) ) {
            return false;
        }
        return true;
    }
}
