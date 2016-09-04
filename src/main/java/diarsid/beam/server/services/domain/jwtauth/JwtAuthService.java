/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.services.domain.jwtauth;

import javax.ws.rs.container.ContainerRequestContext;

import diarsid.beam.server.data.entities.jpa.PersistableUser;

/**
 *
 * @author Diarsid
 */
public interface JwtAuthService {
    
    JwtValidationResult validateRequest(ContainerRequestContext request);
    
    String createJwtFor(PersistableUser user);
}
