/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.web.auth.jwt;

import java.security.Key;
import java.util.Date;

import javax.ws.rs.container.ContainerRequestContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.SigningKeyResolver;
import io.jsonwebtoken.SigningKeyResolverAdapter;

import diarsid.beam.server.services.domain.keys.KeysService;

import static diarsid.beam.server.services.web.auth.jwt.JwtBeamServerClaims.EXPIRATION;
import static diarsid.beam.server.services.web.auth.jwt.JwtBeamServerClaims.NICK_NAME;
import static diarsid.beam.server.services.web.auth.jwt.JwtBeamServerClaims.ROLE;
import static diarsid.beam.server.services.web.auth.jwt.JwtBeamServerClaims.USER_ID;
import static diarsid.beam.server.services.web.auth.jwt.JwtStatus.JWT_EXPIRED;
import static diarsid.beam.server.services.web.auth.jwt.JwtStatus.JWT_LEGAL;
import static diarsid.beam.server.services.web.auth.jwt.JwtStatus.JWT_NOT_FOUND;
import static diarsid.beam.server.services.web.auth.jwt.JwtStatus.JWT_SIGN_INVALID;

/**
 *
 * @author Diarsid
 */

@Component
public class JwtValidator {
    
    private static final Logger logger;
    static {
        logger = LoggerFactory.getLogger(JwtValidator.class);
    }
    
    private static final JwtValidationResult JWT_NOT_FOUND_RESULT;
    private static final JwtValidationResult JWT_SIGN_INVALID_RESULT;
    private static final JwtValidationResult JWT_EXPIRED_RESULT;
    
    public static final String AUTH_HEADER;
    public static final String BEARER_MARKER;
    public static final String JWT_ISSUER;
        
    static {
        JWT_NOT_FOUND_RESULT = new JwtValidationResult(JWT_NOT_FOUND, null);
        JWT_SIGN_INVALID_RESULT = new JwtValidationResult(JWT_SIGN_INVALID, null);
        JWT_EXPIRED_RESULT = new JwtValidationResult(JWT_EXPIRED, null);
        
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
        logger.info("created");
    }
    
    public JwtValidationResult validateRequest(ContainerRequestContext request) {        
        if ( ! this.doesRequestHaveJwt(request) ) {
            return JWT_NOT_FOUND_RESULT;
        } 
        return 
                this.parseJwtClaimsIntoUserInfo(
                        this.verifyAndExtractClaims(
                                this.extractJwtFrom(request)));
    }
    
    private boolean doesRequestHaveJwt(ContainerRequestContext request) {
        String authHeader = request.getHeaderString(AUTH_HEADER);
        if ( authHeader != null ) {
            if ( ! authHeader.isEmpty() ) {
                if ( authHeader.startsWith("Bearer ")) {
                    logger.info("JWT detected.");
                    return true;
                } else {
                    logger.info("Request autherization is not a JWT");
                    return false;
                }
            } else {
                logger.info("Authentication header is empty.");
                return false;
            }
        } else {
            logger.info("Authentication is missed.");
            return false;
        }
    }    
    
    private JwtValidationResult parseJwtClaimsIntoUserInfo(Claims claims) {
        if ( claims != null ) {
            if ( this.jwtNotExpired(claims) ) {
                logger.info("JWT ok");
                return new JwtValidationResult(
                        JWT_LEGAL, this.extractUserInfoFromClaims(claims));
            } else {
                logger.info("JWT expired");
                return JWT_EXPIRED_RESULT;
            }  
        } else {  
            logger.info("JWT signature failed.");
            return JWT_SIGN_INVALID_RESULT;                
        }
    }
    
    private Claims verifyAndExtractClaims(String jwt) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKeyResolver(this.keyResolver)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (SignatureException e) {
            logger.info("JWT validation failed. Do not trust to this JWT.");
        } catch (MalformedJwtException e) {
            logger.info("JWT validation failed - JWT is malformed.");
        }
        return claims;
    }
    
    private String extractJwtFrom(ContainerRequestContext request) {
        return request.getHeaderString(AUTH_HEADER).substring(BEARER_MARKER.length());
    }
    
    private boolean jwtNotExpired(Claims claims) {
        return (new Date(
                        Long.valueOf(this.extractClaim(claims, EXPIRATION))))
                .after(
                        new Date());
    }

    private JwtUserInfo extractUserInfoFromClaims(Claims claims) {
        return new JwtUserInfo(
                this.extractClaim(claims, USER_ID),
                this.extractClaim(claims, NICK_NAME),
                this.extractClaim(claims, ROLE));
    }
    
    private String extractClaim(Claims claims, JwtBeamServerClaims claimHeader) {
        return ( (String) claims.get(claimHeader.header()) );
    }
}
