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

import diarsid.beam.server.data.KeysManager;
import diarsid.beam.server.services.auth.JwtProducer;
import diarsid.beam.server.services.auth.JwtValidator;
import diarsid.beam.server.util.RandomStringGenerator;
import diarsid.beam.server.data.daos.DaoKeys;

/**
 *
 * @author Diarsid
 */

@Configuration
@Import(BeamServerDataSpringAnnotationsConfig.class)
public class BeamServerAuthSpringAnnotationsConfig {
    
    @Autowired
    Environment environment;
    
    public BeamServerAuthSpringAnnotationsConfig() {
    }
    
    @Bean
    public JwtValidator jwtValidator(KeysManager keysManager) {
        return new JwtValidator(keysManager);
    }
    
    @Bean
    public JwtProducer jwtProducer(KeysManager keysManager) {
        return new JwtProducer(keysManager);
    }
    
    @Bean
    public KeysManager keysManager(DaoKeys keysDao, RandomStringGenerator stringGenerator) {
        int keysQty = Integer.valueOf(environment.getProperty("beam.server.keysqty"));
        KeysManager keysManager = new KeysManager(keysDao, stringGenerator, keysQty);
        return keysManager;
    }
}
