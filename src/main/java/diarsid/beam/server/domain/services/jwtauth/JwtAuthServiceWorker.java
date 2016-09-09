/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.domain.services.jwtauth;

import javax.ws.rs.container.ContainerRequestContext;

import org.springframework.stereotype.Component;

import diarsid.beam.server.domain.entities.jpa.PersistableUser;

@Component
public class JwtAuthServiceWorker implements JwtAuthService {
    
    private final JwtValidator jwtValidator;
    private final JwtProducer jwtProducer;
    
    public JwtAuthServiceWorker(JwtValidator jwtValidator, JwtProducer jwtProducer) {        
        this.jwtValidator = jwtValidator;
        this.jwtProducer = jwtProducer;
    }

    @Override
    public JwtValidationResult validateRequest(ContainerRequestContext request) {
        return this.jwtValidator.validateRequest(request);
    }

    @Override
    public String createJwtFor(PersistableUser user) {
        return this.jwtProducer.createJwt(user);
    }
}
