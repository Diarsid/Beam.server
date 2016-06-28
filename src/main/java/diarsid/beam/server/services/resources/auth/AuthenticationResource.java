/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.resources.auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 *
 * @author Diarsid
 */

@Path("/auth")
public class AuthenticationResource {
    
    public AuthenticationResource() {
    }
    
    @GET
    @Path("/users/login")
    @Produces(APPLICATION_JSON)
    public void loginUserAndReturnJWT() {
        // TODO
        // login using user login and password
        // return JWT in header
    }
    
    @GET
    @Path("/users/registration")
    @Produces(APPLICATION_JSON)
    public void registerUserAndReturnJWT() {
        // TODO
        // save new user using full user info
        // return JWT in header
    }
    
    @GET
    @Path("/tokens/validation")
    public void validateJWT() {
        // TODO
        // validate given JWT
        // return 200 OK if JWT is valid and has not been expired
        // return 302 FOUND if JWT is valid but has been expired
        // return 401 UNAUTHORIZED if JWT is not valid at all
    }
}
