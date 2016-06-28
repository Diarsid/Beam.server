/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.resources.auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author Diarsid
 */

@Path("/registration")
public class NickNamePresenceResource {
    
    public NickNamePresenceResource() {
    }
    
    @GET
    @Path("/users/nickname")
    public void ifNickNameExists() {
        // TODO
        // Send back appropriate response status - found or not
    }
}
