/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config.spring.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import diarsid.beam.server.domain.services.jwtauth.JwtAuthService;
import diarsid.beam.server.domain.services.jwtauth.JwtValidator;
import diarsid.beam.server.domain.services.users.UsersService;
import diarsid.beam.server.domain.services.validation.UsersValidationService;
import diarsid.beam.server.domain.services.validation.WebObjectsValidationService;
import diarsid.beam.server.domain.services.webobjects.UserWebObjectsService;
import diarsid.beam.server.presentation.web.json.util.JavaObjectToJsonConverter;
import diarsid.beam.server.presentation.web.services.filters.AuthenticationFilter;
import diarsid.beam.server.presentation.web.services.resources.AuthenticationResource;
import diarsid.beam.server.presentation.web.services.resources.SingleWebDirectoryResource;
import diarsid.beam.server.presentation.web.services.resources.SingleWebPageResource;
import diarsid.beam.server.presentation.web.services.resources.UsersValidationResource;
import diarsid.beam.server.presentation.web.services.resources.WebDirectoriesResource;
import diarsid.beam.server.presentation.web.services.resources.WebObjectsValidationResource;
import diarsid.beam.server.presentation.web.services.resources.WebPagesResource;

/**
 *
 * @author Diarsid
 */

@Configuration
@Import({
    DomainServicesBeans.class,
    WebServicesProvidersBeans.class, 
    UtilBeans.class})
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
            JwtAuthService jwtService) {
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
    public WebPagesResource webPagesResource(
            UserWebObjectsService webObjects,
            JavaObjectToJsonConverter toJsonConverter) {
        return new WebPagesResource(webObjects, toJsonConverter);
    }
    
    @Bean
    public SingleWebPageResource singleWebPageResource(
            UserWebObjectsService webObjects, JavaObjectToJsonConverter toJsonConverter) {
        return new SingleWebPageResource(webObjects, toJsonConverter);
    }
    
    @Bean
    public WebDirectoriesResource webDirectoriesResource(
            UserWebObjectsService webObjects, JavaObjectToJsonConverter toJsonConverter) {
        return new WebDirectoriesResource(webObjects, toJsonConverter);
    }
    
    @Bean
    public SingleWebDirectoryResource singleWebDirectoryResource(
            UserWebObjectsService webObjects, JavaObjectToJsonConverter toJsonConverter) {
        return new SingleWebDirectoryResource(webObjects, toJsonConverter);
    }
}
