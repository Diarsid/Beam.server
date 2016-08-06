/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import diarsid.beam.server.data.ObjectDataServiceWorker;
import diarsid.beam.server.data.daos.DaoUsers;
import diarsid.beam.server.web.services.auth.JwtProducer;
import diarsid.beam.server.web.services.auth.JwtValidator;
import diarsid.beam.server.web.services.filters.AuthenticationFilter;
import diarsid.beam.server.web.services.resources.AuthenticationResource;
import diarsid.beam.server.web.services.resources.ObjectResource;
import diarsid.beam.server.web.services.resources.ValidationResource;

/**
 *
 * @author Diarsid
 */

@Configuration
@Import({
    AuthSpringAnnotationsConfig.class,
    DataServicesSpringAnnotationsConfig.class})
public class WebServicesSpringAnnotationsConfig {
    
    public WebServicesSpringAnnotationsConfig() {
    }    
    
    @Bean
    public AuthenticationFilter authenticationFilter(JwtValidator jwtValidator) {
        return new AuthenticationFilter(jwtValidator);
    }
    
    @Bean
    public AuthenticationResource authenticationResource(
            DaoUsers daoUsers, 
            JwtValidator jwtValidator,
            JwtProducer jwtProducer) {
        return new AuthenticationResource(
                daoUsers, jwtValidator, jwtProducer);
    }
    
    @Bean
    public ValidationResource validationResource() {
        return new ValidationResource();
    }
    
    @Bean  
    public ObjectResource objectResource(ObjectDataServiceWorker objectDataService) {
        return new ObjectResource(objectDataService);
    }
}
