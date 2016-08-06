/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.web.services.auth;

import static diarsid.beam.server.web.services.auth.JwtStatus.JWT_EXPIRED;
import static diarsid.beam.server.web.services.auth.JwtStatus.JWT_NOT_FOUND;
import static diarsid.beam.server.web.services.auth.JwtStatus.JWT_SIGN_INVALID;

/**
 *
 * @author Diarsid
 */
public class JwtValidationResult {
    
    private final static JwtValidationResult JWT_NOT_FOUND_RESULT;
    private final static JwtValidationResult JWT_SIGN_INVALID_RESULT;
    private final static JwtValidationResult JWT_EXPIRED_RESULT;
    static {
        JWT_NOT_FOUND_RESULT = new JwtValidationResult(JWT_NOT_FOUND, null);
        JWT_SIGN_INVALID_RESULT = new JwtValidationResult(JWT_SIGN_INVALID, null);
        JWT_EXPIRED_RESULT = new JwtValidationResult(JWT_EXPIRED, null);
    }
    
    private final JwtStatus status;
    private final UserJwtInfo userInfo;
    
    public JwtValidationResult(JwtStatus status, UserJwtInfo userInfo) {
        this.status = status;
        this.userInfo = userInfo;
    }
    
    static JwtValidationResult jwtNotFound() {
        return JWT_NOT_FOUND_RESULT;
    }
    
    static JwtValidationResult jwtSignFailed() {
        return JWT_SIGN_INVALID_RESULT;
    }
    
    static JwtValidationResult jwtExpired() {
        return JWT_EXPIRED_RESULT;
    }
    
    public boolean isJwtPresent() {
        return this.status.isHigherThan(JWT_NOT_FOUND);
    }
    
    public boolean isJwtNotExpired() {
        return this.status.isHigherThan(JWT_EXPIRED);
    }
    
    public boolean isJwtSignVerified() {
        return this.status.isHigherThan(JWT_SIGN_INVALID);
    }

    public UserJwtInfo getUserInfo() {
        return this.userInfo;
    }
}
