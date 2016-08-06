/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.web.services.auth;

import diarsid.beam.server.data.entities.jpa.PersistableUser;
import diarsid.beam.server.web.services.auth.UserJwtInfo;


/**
 *
 * @author Diarsid
 */
public enum UserRoles {
    
    NONE (100, "NONE"),
    USER (200, "USER"),
    ADMIN (300, "ADMIN"),
    OWNER (400, "OWNER");
    
    private UserRoles(int weight, String role) {
        this.weight = weight;
        this.role = role;
    }
    
    private final int weight;
    private final String role;
    
    public String roleName() {
        return this.role;
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
    
    public static boolean hasUserAuthority(UserJwtInfo user) {
        return hasAuthorityOfRole(user.getRole(), USER);
    }
    
    public static boolean hasAdminAuthority(UserJwtInfo user) {
        return hasAuthorityOfRole(user.getRole(), ADMIN);
    }
    
    public static boolean hasOwnerAuthority(UserJwtInfo user) {
        return hasAuthorityOfRole(user.getRole(), OWNER);
    }

    private static boolean hasAuthorityOfRole(String userRole, UserRoles askedRole) {
        return valueOf(userRole.toUpperCase()).weight >= askedRole.weight;
    }     
}
