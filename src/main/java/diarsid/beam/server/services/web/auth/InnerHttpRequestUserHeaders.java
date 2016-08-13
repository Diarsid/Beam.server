/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.web.auth;

/**
 *
 * @author Diarsid
 */
public enum InnerHttpRequestUserHeaders {
    
    BEAM_USER_ID ("beam.server.user.id"),
    BEAM_USER_NICKNAME ("beam.server.user.nickname"),
    BEAM_USER_ROLE ("beam.server.user.role");
    
    private InnerHttpRequestUserHeaders(String header) {
        this.header = header;
    }
    
    private final String header;
    
    public String getHeader() {
        return this.header;
    }    
}
