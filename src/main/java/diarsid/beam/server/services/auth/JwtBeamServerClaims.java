/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.auth;

/**
 *
 * @author Diarsid
 */
public enum JwtBeamServerClaims {
    
    EXPIRATION ("exp"),
    ISSUED_AT ("iat"),
    USER_ID ("id"),
    NICK_NAME ("nickName"),
    ROLE ("role");
    
    private final String claimHeader;
    
    private JwtBeamServerClaims(String claimHeader) {
        this.claimHeader = claimHeader;
    }
    
    public String header() {
        return this.claimHeader;
    }
}
