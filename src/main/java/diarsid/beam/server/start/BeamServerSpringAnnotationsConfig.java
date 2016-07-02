/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.start;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import diarsid.beam.server.data.MockKeyStorage;
import diarsid.beam.server.data.MockUserStorage;
import diarsid.beam.server.data.ObjectDataProvider;
import diarsid.beam.server.services.auth.JwtProducer;
import diarsid.beam.server.services.auth.JwtValidator;
import diarsid.beam.server.services.filters.auth.AuthenticationFilter;
import diarsid.beam.server.services.resources.auth.AuthenticationResource;
import diarsid.beam.server.services.resources.objects.ObjectResource;

/**
 *
 * @author Diarsid
 */

@Configuration
public class BeamServerSpringAnnotationsConfig {
    
    public BeamServerSpringAnnotationsConfig() {
    }
    
    @Bean    
    public ObjectDataProvider objectDataProvider() {
        return new ObjectDataProvider();
    }
    
    @Bean  
    public ObjectResource objectResource(ObjectDataProvider objectDataProvider) {
        return new ObjectResource(objectDataProvider);
    }
    
    @Bean
    public MockKeyStorage mockKeyStorage() {
        return new MockKeyStorage();
    }
    
    @Bean
    public MockUserStorage mockUserStorage() {
        return new MockUserStorage();
    }
    
    @Bean
    public AuthenticationResource authenticationResource(
            MockUserStorage mockUserStorage, 
            JwtValidator jwtValidator,
            JwtProducer jwtProducer) {
        return new AuthenticationResource(
                mockUserStorage, jwtValidator, jwtProducer);
    }
    
    @Bean
    public JwtValidator jwtValidator(MockKeyStorage mockKeyStorage) {
        return new JwtValidator(mockKeyStorage);
    }
    
    @Bean
    public JwtProducer jwtProducer(MockKeyStorage mockKeyStorage) {
        return new JwtProducer(mockKeyStorage);
    }
    
    @Bean
    public AuthenticationFilter authenticationFilter(JwtValidator jwtValidator) {
        return new AuthenticationFilter(jwtValidator);
    }
}
