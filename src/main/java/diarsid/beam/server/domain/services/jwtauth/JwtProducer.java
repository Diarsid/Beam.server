/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.domain.services.jwtauth;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import diarsid.beam.server.domain.entities.KeyIdPair;
import diarsid.beam.server.domain.entities.jpa.PersistableUser;

import static diarsid.beam.server.domain.services.jwtauth.JwtBeamServerClaims.EXPIRATION;
import static diarsid.beam.server.domain.services.jwtauth.JwtBeamServerClaims.ISSUED_AT;
import static diarsid.beam.server.domain.services.jwtauth.JwtBeamServerClaims.NICK_NAME;
import static diarsid.beam.server.domain.services.jwtauth.JwtBeamServerClaims.ROLE;
import static diarsid.beam.server.domain.services.jwtauth.JwtBeamServerClaims.USER_ID;

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
    
    private final KeysService keysService;
    
    public JwtProducer(KeysService keysService) {
        this.keysService = keysService;
        LOGGER.info("created.");
    }
    
    public String createJwt(PersistableUser user) {
        LOGGER.info("JWT creation...");
        KeyIdPair pair = this.keysService.getRandomKeyIdPair();
        Claims claims = Jwts.claims();
        long now = this.getMillisOfNow();
        long expiration = this.plusOneHourFrom(now);
        claims.put(EXPIRATION.header(), String.valueOf(expiration));
        claims.put(ISSUED_AT.header(), String.valueOf(now));
        claims.put(USER_ID.header(), String.valueOf(user.getId()));
        claims.put(NICK_NAME.header(), user.getNickname());
        claims.put(ROLE.header(), user.getRole());        
        
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
