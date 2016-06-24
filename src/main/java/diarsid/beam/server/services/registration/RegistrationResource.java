/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.registration;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import diarsid.beam.server.services.filters.auth.AuthenticationNotRequired;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 *
 * @author Diarsid
 */

@Path("/registration")
public class RegistrationResource {
    
    public RegistrationResource() {
    }
    
    @GET
    @Path("/token")
    @Produces(APPLICATION_JSON)
    @AuthenticationNotRequired
    public String getToken() {
        return "token-hujoken";
    }
    
    @POST
    @Path("/users")
    @Produces(APPLICATION_JSON)
    @AuthenticationNotRequired
    public void acceptUserInfo() {
        // TODO
        // return JWT in header
    }
}
