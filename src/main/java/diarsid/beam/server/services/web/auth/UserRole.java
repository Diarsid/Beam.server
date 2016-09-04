/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.services.web.auth;

import diarsid.beam.server.services.domain.jwtauth.JwtUserInfo;
import diarsid.beam.server.data.entities.jpa.PersistableUser;


/**
 *
 * @author Diarsid
 */
public enum UserRole {
    
    NONE (100),
    USER (200),
    ADMIN (300),
    OWNER (400);
    
    private UserRole(int weight) {
        this.weight = weight;
    }
    
    private final int weight;
    
    
    public static UserRole valueOfIgnoreCase(String name) {
        return valueOf(name.toUpperCase());
    }
    
    public static boolean hasUserAuthority(PersistableUser user) {
        return hasAuthorityOfRole(user.getRole(), USER);
    }
    
    public static boolean hasAdminAuthority(PersistableUser user) {
        return hasAuthorityOfRole(user.getRole(), ADMIN);
    }
    
    public static boolean hasOwnerAuthority(PersistableUser user) {
        return hasAuthorityOfRole(user.getRole(), OWNER);
    }
    
    public static boolean hasUserAuthority(JwtUserInfo user) {
        return hasAuthorityOfRole(user.getRole(), USER);
    }
    
    public static boolean hasAdminAuthority(JwtUserInfo user) {
        return hasAuthorityOfRole(user.getRole(), ADMIN);
    }
    
    public static boolean hasOwnerAuthority(JwtUserInfo user) {
        return hasAuthorityOfRole(user.getRole(), OWNER);
    }

    private static boolean hasAuthorityOfRole(String userRole, UserRole askedRole) {
        return valueOf(userRole.toUpperCase()).weight >= askedRole.weight;
    }     
}
