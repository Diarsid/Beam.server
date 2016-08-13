/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.web.auth.jwt;

import static diarsid.beam.server.services.web.auth.jwt.JwtStatus.JWT_EXPIRED;
import static diarsid.beam.server.services.web.auth.jwt.JwtStatus.JWT_NOT_FOUND;
import static diarsid.beam.server.services.web.auth.jwt.JwtStatus.JWT_SIGN_INVALID;

/**
 *
 * @author Diarsid
 */
public class JwtValidationResult {
    
    private final JwtStatus status;
    private final JwtUserInfo userInfo;
    
    public JwtValidationResult(JwtStatus status, JwtUserInfo userInfo) {
        this.status = status;
        this.userInfo = userInfo;
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

    public JwtUserInfo getUserInfo() {
        return this.userInfo;
    }
}
