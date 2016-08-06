/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import diarsid.beam.server.data.services.keys.KeysService;
import diarsid.beam.server.web.services.auth.JwtProducer;
import diarsid.beam.server.web.services.auth.JwtValidator;

/**
 *
 * @author Diarsid
 */

@Configuration
@Import(DataServicesSpringAnnotationsConfig.class)
public class AuthSpringAnnotationsConfig {
    
    @Autowired
    Environment environment;
    
    public AuthSpringAnnotationsConfig() {
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
