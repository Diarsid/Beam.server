/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services.filters.auth;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import diarsid.beam.server.entities.User;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import static diarsid.beam.server.entities.Users.getFAKEAuthenticatedUser;
import static diarsid.beam.server.services.filters.auth.UserHttpHeaders.BEAM_USER_ID;
import static diarsid.beam.server.services.filters.auth.UserHttpHeaders.BEAM_USER_NAME;
import static diarsid.beam.server.services.filters.auth.UserHttpHeaders.BEAM_USER_NICKNAME;
import static diarsid.beam.server.services.filters.auth.UserHttpHeaders.BEAM_USER_PASSWORD;
import static diarsid.beam.server.services.filters.auth.UserHttpHeaders.BEAM_USER_ROLE;
import static diarsid.beam.server.services.filters.auth.UserHttpHeaders.BEAM_USER_SURNAME;
import static diarsid.beam.server.services.registration.UserRoles.hasUserAuthority;

/**
 *
 * @author Diarsid
 */
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {
    
    private final static Logger LOGGER;
    static {
        LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);
    }
    
    public AuthenticationFilter() {
        LOGGER.info("created.");
    }
    
    @Override
    public void filter(ContainerRequestContext request) throws IOException {        
        String path = request.getUriInfo().getPath();
        if ( path.contains("registration") ) {
            LOGGER.info("unprotected area - do nothing under : " + path);
        } else {
            LOGGER.info("Authentication required aza-za!!! " + path);
            User user = this.getUserFromRequestJWT(request);
            if ( hasUserAuthority(user) ) {
                this.addUserFieldsIntoHttpRequestHeader(request, user);                
            } else {
                request.abortWith(Response.status(UNAUTHORIZED).build());
            }
        }        
    }
    
    private User getUserFromRequestJWT(ContainerRequestContext request) {
        // TODO
        // 1. parse JSON Web Token
        // 2. retrieve User from data base by info from token
        
        // User user = ....(jwt);
        // return getAuthenticatedUser(user);
        
        return getFAKEAuthenticatedUser();
    }
    
    private void addUserFieldsIntoHttpRequestHeader(ContainerRequestContext request, User user) {
        MultivaluedMap<String, String> headers = request.getHeaders();
        headers.putSingle(BEAM_USER_ID.getHeader(), user.getIdString());
        headers.putSingle(BEAM_USER_NAME.getHeader(), user.getName());
        headers.putSingle(BEAM_USER_SURNAME.getHeader(), user.getSurname());
        headers.putSingle(BEAM_USER_NICKNAME.getHeader(), user.getNickName());
        headers.putSingle(BEAM_USER_ROLE.getHeader(), user.getRole());
        headers.putSingle(BEAM_USER_PASSWORD.getHeader(), user.getPassword());
    }
}
