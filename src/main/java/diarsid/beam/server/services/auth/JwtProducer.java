/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.auth;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import diarsid.beam.server.data.KeyIdPair;
import diarsid.beam.server.data.MockKeyStorage;

import static diarsid.beam.server.services.auth.JwtBeamServerClaims.EXPIRATION;
import static diarsid.beam.server.services.auth.JwtBeamServerClaims.ISSUED_AT;
import static diarsid.beam.server.services.auth.JwtBeamServerClaims.NICK_NAME;
import static diarsid.beam.server.services.auth.JwtBeamServerClaims.ROLE;
import static diarsid.beam.server.services.auth.JwtBeamServerClaims.USER_ID;

/**
 *
 * @author Diarsid
 */

@Component
public class JwtProducer {
    
    private final static Logger LOGGER;
    public final static String JWT_RESPONSE_HEADER;
    static {
        LOGGER = LoggerFactory.getLogger(JwtProducer.class);
        JWT_RESPONSE_HEADER = "jwt";
    }
    
    private final MockKeyStorage keyStorage;
    
    public JwtProducer(MockKeyStorage keyStorage) {
        this.keyStorage = keyStorage;
        LOGGER.info("created.");
    }
    
    public String createJwt(String id, String nickName, String role) {
        LOGGER.info("JWT creation...");
        KeyIdPair pair = this.keyStorage.getRandomKeyIdPair();
        Claims claims = Jwts.claims();
        long now = this.getMillisOfNow();
        long expiration = this.plusOneHourFrom(now);
        claims.put(EXPIRATION.header(), String.valueOf(expiration));
        claims.put(ISSUED_AT.header(), String.valueOf(now));
        claims.put(USER_ID.header(), id);
        claims.put(NICK_NAME.header(), nickName);
        claims.put(ROLE.header(), role.toLowerCase());        
        
        return Jwts.builder()
                .setHeaderParam("kid", pair.getId())
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, pair.getKey())
                .compact();
    }
    
    private long getMillisOfNow() {
        return (new Date()).getTime();
    }
    
    private long plusOneHourFrom(long now) {
        return (now + (1000 * 60 * 60));
    }
}
