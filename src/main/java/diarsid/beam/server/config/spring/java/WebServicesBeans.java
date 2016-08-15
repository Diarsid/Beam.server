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
import diarsid.beam.server.services.domain.validation.UsersValidationService;
import diarsid.beam.server.services.domain.validation.WebObjectsValidationService;
import diarsid.beam.server.services.web.auth.jwt.JwtService;
import diarsid.beam.server.services.web.auth.jwt.JwtValidator;
import diarsid.beam.server.services.web.filters.AuthenticationFilter;
import diarsid.beam.server.services.web.resources.AuthenticationResource;
import diarsid.beam.server.services.web.resources.ObjectResource;
import diarsid.beam.server.services.web.resources.UsersValidationResource;
import diarsid.beam.server.services.web.resources.WebObjectsValidationResource;

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
            JwtService jwtService) {
        return new AuthenticationResource(
                usersService, jwtService);
    }
    
    @Bean
    public UsersValidationResource usersValidationResource(
            UsersService usersService, UsersValidationService validation) {
        return new UsersValidationResource(validation, usersService);
    }
    
    @Bean
    public WebObjectsValidationResource webObjectsValidationResource(WebObjectsValidationService validation) {
        return new WebObjectsValidationResource(validation);
    }
    
    @Bean  
    public ObjectResource objectResource(ObjectDataServiceWorker objectDataService) {
        return new ObjectResource(objectDataService);
    }
}
