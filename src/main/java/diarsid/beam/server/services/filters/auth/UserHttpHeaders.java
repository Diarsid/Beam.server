/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.filters.auth;

/**
 *
 * @author Diarsid
 */
public enum UserHttpHeaders {
    
    BEAM_USER_ID ("beam.server.user.id"),
    BEAM_USER_NAME ("beam.server.user.name"),
    BEAM_USER_SURNAME ("beam.server.user.surname"),
    BEAM_USER_NICKNAME ("beam.server.user.nickname"),
    BEAM_USER_EMAIL ("beam.server.user.email"),
    BEAM_USER_PASSWORD ("beam.server.user.password"),
    BEAM_USER_ROLE ("beam.server.user.role");
    
    private UserHttpHeaders(String header) {
        this.header = header;
    }
    
    private final String header;
    
    public String getHeader() {
        return this.header;
    }    
}
