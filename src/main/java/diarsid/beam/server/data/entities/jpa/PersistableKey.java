/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.entities.jpa;

import diarsid.beam.server.data.entities.KeyIdPair;

import java.io.Serializable;
import java.security.Key;
import java.util.Arrays;
import java.util.Objects;

import javax.crypto.spec.SecretKeySpec;
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
@Table(name = "jwt_keys")
public class PersistableKey implements Serializable {
    
    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "key_synthetic_id")
    private int syntheticId;
    
    @Column(name = "key_natural_id")
    private String naturalId;
    
    @Column(name = "key_algorithm")
    private String secretKeyAlgorithm;
    
    @Column(name = "key_binary")
    private byte[] secretBinaryKey;
    
    public PersistableKey() {
    }
    
    public PersistableKey(KeyIdPair keyIdPair) {
        this.naturalId = keyIdPair.getId();
        this.secretKeyAlgorithm = keyIdPair.getKey().getAlgorithm();
        this.secretBinaryKey = keyIdPair.getKey().getEncoded();
    }
    
    public KeyIdPair constructCryptoKey() {
        Key key = new SecretKeySpec(this.secretBinaryKey, this.secretKeyAlgorithm);
        return new KeyIdPair(this.naturalId, key);
    }

    public int getSyntheticId() {
        return syntheticId;
    }

    public void setSyntheticId(int syntheticId) {
        this.syntheticId = syntheticId;
    }

    public String getNaturalId() {
        return naturalId;
    }

    public void setNaturalId(String naturalId) {
        this.naturalId = naturalId;
    }

    public String getSecretKeyAlgorithm() {
        return secretKeyAlgorithm;
    }

    public void setSecretKeyAlgorithm(String secretKeyAlgorithm) {
        this.secretKeyAlgorithm = secretKeyAlgorithm;
    }

    public byte[] getSecretBinaryKey() {
        return secretBinaryKey;
    }

    public void setSecretBinaryKey(byte[] secretBinaryKey) {
        this.secretBinaryKey = secretBinaryKey;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.naturalId);
        hash = 59 * hash + Objects.hashCode(this.secretKeyAlgorithm);
        hash = 59 * hash + Arrays.hashCode(this.secretBinaryKey);
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
        final PersistableKey other = ( PersistableKey ) obj;
        if ( !Objects.equals(this.naturalId, other.naturalId) ) {
            return false;
        }
        if ( !Objects.equals(this.secretKeyAlgorithm, other.secretKeyAlgorithm) ) {
            return false;
        }
        if ( !Arrays.equals(this.secretBinaryKey, other.secretBinaryKey) ) {
            return false;
        }
        return true;
    }
}
