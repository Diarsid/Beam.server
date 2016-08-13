/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config.spring.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import diarsid.beam.server.services.domain.keys.KeysService;
import diarsid.beam.server.services.web.auth.jwt.JwtProducer;
import diarsid.beam.server.services.web.auth.jwt.JwtValidator;

/**
 *
 * @author Diarsid
 */

@Configuration
@Import(DomainServicesBeans.class)
public class AuthenticationBeans {
    
    @Autowired
    Environment environment;
    
    public AuthenticationBeans() {
    }
    
    @Bean
    public JwtValidator jwtValidator(KeysService keysService) {
        return new JwtValidator(keysService);
    }
    
    @Bean
    public JwtProducer jwtProducer(KeysService keysService) {
        return new JwtProducer(keysService);
    }
}
