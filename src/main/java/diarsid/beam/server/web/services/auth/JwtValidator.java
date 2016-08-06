/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.web.services.auth;

import java.security.Key;
import java.util.Date;

import javax.ws.rs.container.ContainerRequestContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.SigningKeyResolverAdapter;

import diarsid.beam.server.data.services.keys.KeysService;

import static diarsid.beam.server.web.services.auth.JwtBeamServerClaims.EXPIRATION;
import static diarsid.beam.server.web.services.auth.JwtBeamServerClaims.NICK_NAME;
import static diarsid.beam.server.web.services.auth.JwtBeamServerClaims.ROLE;
import static diarsid.beam.server.web.services.auth.JwtBeamServerClaims.USER_ID;
import static diarsid.beam.server.web.services.auth.JwtStatus.JWT_LEGAL;
import static diarsid.beam.server.web.services.auth.JwtValidationResult.jwtExpired;
import static diarsid.beam.server.web.services.auth.JwtValidationResult.jwtNotFound;
import static diarsid.beam.server.web.services.auth.JwtValidationResult.jwtSignFailed;

/**
 *
 * @author Diarsid
 */

@Component
public class JwtValidator {
    
    private final static Logger LOGGER;
    public final static String AUTH_HEADER;
    public final static String BEARER_MARKER;
    public final static String JWT_ISSUER;
    static {
        LOGGER = LoggerFactory.getLogger(JwtValidator.class);
        AUTH_HEADER = "Authentication";
        BEARER_MARKER = "Bearer ";
        JWT_ISSUER = "Beam.server";
    }
        
    private final SigningKeyResolver keyResolver;
    
    public JwtValidator(KeysService keysService) {
        this.keyResolver = new SigningKeyResolverAdapter() {
            private final Logger anonymousKeyResolverLogger;
            {
                this.anonymousKeyResolverLogger = LoggerFactory.getLogger(this.getClass());
            }
            
            @Override
            public Key resolveSigningKey(JwsHeader header, Claims claims) {
                this.anonymousKeyResolverLogger.info("resolved with keyId: " + header.getKeyId());
                return keysService.getKeyById(header.getKeyId());
            }
        };                
        LOGGER.info("created");
    }
    
    public JwtValidationResult validateRequest(ContainerRequestContext request) {        
        if ( this.doesRequestHaveJwt(request) ) {
            return this.parseJwtClaimsIntoUserInfo(
                    this.verifyAndExtratctClaims(
                            this.extractJwtFrom(request)));
        } else {
            return jwtNotFound();
        }        
    }
    
    private boolean doesRequestHaveJwt(ContainerRequestContext request) {
        String authHeader = request.getHeaderString(AUTH_HEADER);
        if ( authHeader != null ) {
            if ( ! authHeader.isEmpty() ) {
                if ( authHeader.startsWith("Bearer ")) {
                    LOGGER.info("JWT detected.");
                    return true;
                } else {
                    LOGGER.info("Request autherization is not a JWT");
                    return false;
                }
            } else {
                LOGGER.info("Authentication header is empty.");
                return false;
            }
        } else {
            LOGGER.info("Authentication is missed.");
            return false;
        }
    }
    
    private String extractJwtFrom(ContainerRequestContext request) {
        return request.getHeaderString(AUTH_HEADER).substring(BEARER_MARKER.length());
    }
    
    private Claims verifyAndExtratctClaims(String jwt) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKeyResolver(this.keyResolver)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (SignatureException e) {
            LOGGER.info("JWT validation failed. Do not trust to this JWT.");
        }
        return claims;
    }
    
    private JwtValidationResult parseJwtClaimsIntoUserInfo(Claims claims) {
        if ( claims != null ) {
            if ( this.jwtNotExpired(claims) ) {
                LOGGER.info("JWT ok");
                return new JwtValidationResult(
                        JWT_LEGAL, this.extractUserInfoFromClaims(claims));
            } else {
                LOGGER.info("JWT expired");
                return jwtExpired();
            }  
        } else {  
            LOGGER.info("JWT signature failed.");
            return jwtSignFailed();                
        }
    }
    
    private boolean jwtNotExpired(Claims claims) {
        return (new Date(
                        Long.valueOf(this.extractClaim(claims, EXPIRATION))))
                .after(
                        new Date());
    }

    private UserJwtInfo extractUserInfoFromClaims(Claims claims) {
        return new UserJwtInfo(
                this.extractClaim(claims, USER_ID),
                this.extractClaim(claims, NICK_NAME),
                this.extractClaim(claims, ROLE));
    }
    
    private String extractClaim(Claims claims, JwtBeamServerClaims claimHeader) {
        return ( (String) claims.get(claimHeader.header()) );
    }
}
