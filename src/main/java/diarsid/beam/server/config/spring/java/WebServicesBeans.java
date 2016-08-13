/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config.spring.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import diarsid.beam.server.data.ObjectDataServiceWorker;
import diarsid.beam.server.services.domain.users.UsersService;
import diarsid.beam.server.services.web.filters.AuthenticationFilter;
import diarsid.beam.server.services.web.auth.jwt.JwtProducer;
import diarsid.beam.server.services.web.auth.jwt.JwtValidator;
import diarsid.beam.server.services.web.resources.AuthenticationResource;
import diarsid.beam.server.services.web.resources.ObjectResource;
import diarsid.beam.server.services.web.resources.ValidationResource;

/**
 *
 * @author Diarsid
 */

@Configuration
@Import({
    AuthenticationBeans.class,
    DomainServicesBeans.class,
    WebServicesProvidersBeans.class})
public class WebServicesBeans {
    
    public WebServicesBeans() {
    }    
    
    @Bean
    public AuthenticationFilter authenticationFilter(JwtValidator jwtValidator) {
        return new AuthenticationFilter(jwtValidator);
    }
    
    @Bean
    public AuthenticationResource authenticationResource(
            UsersService usersService, 
            JwtValidator jwtValidator,
            JwtProducer jwtProducer) {
        return new AuthenticationResource(
                usersService, jwtValidator, jwtProducer);
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
