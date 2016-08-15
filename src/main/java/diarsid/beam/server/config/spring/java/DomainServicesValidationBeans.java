/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config.spring.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import diarsid.beam.server.services.domain.validation.UsersInfoValidator;
import diarsid.beam.server.services.domain.validation.UsersValidationService;
import diarsid.beam.server.services.domain.validation.WebObjectsPropertiesValidator;
import diarsid.beam.server.services.domain.validation.WebObjectsValidationService;

/**
 *
 * @author Diarsid
 */

@Configuration
public class DomainServicesValidationBeans {
    
    public DomainServicesValidationBeans() {
    }
        
    @Bean
    public WebObjectsValidationService webObjectsValidationService() {
        return new WebObjectsPropertiesValidator();
    }
    
    @Bean
    public UsersValidationService usersValidationService() {
        return new UsersInfoValidator();
    }
}
