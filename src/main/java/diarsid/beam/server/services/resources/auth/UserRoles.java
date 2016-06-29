/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.services.resources.auth;

import diarsid.beam.server.entities.User;


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
    
    public static boolean hasUserAuthority(User user) {
        return hasAuthorityOfRole(user, USER);
    }
    
    public static boolean hasAdminAuthority(User user) {
        return hasAuthorityOfRole(user, ADMIN);
    }
    
    public static boolean hasOwnerAuthority(User user) {
        return hasAuthorityOfRole(user, OWNER);
    }

    private static boolean hasAuthorityOfRole(User user, UserRoles role) {
        return valueOf(user.getRole()).weight >= role.weight;
    }    
}
