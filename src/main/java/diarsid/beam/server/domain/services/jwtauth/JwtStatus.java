/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.domain.services.jwtauth;

/**
 *
 * @author Diarsid
 */
public enum JwtStatus {
    
    JWT_NOT_FOUND (100),
    JWT_SIGN_INVALID (200),
    JWT_SIGN_VALID (300),
    JWT_EXPIRED (400),
    JWT_LEGAL (500);
    
    private final int weight;
    
    private JwtStatus(int weight) {
        this.weight = weight;
    }
    
    public boolean isHigherThan(JwtStatus otherStatus) {
        return ( this.weight > otherStatus.weight );
    }    
}
