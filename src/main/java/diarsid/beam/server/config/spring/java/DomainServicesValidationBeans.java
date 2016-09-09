/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config.spring.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import diarsid.beam.server.domain.services.validation.UsersValidationServiceWorker;
import diarsid.beam.server.domain.services.validation.UsersValidationService;
import diarsid.beam.server.domain.services.validation.WebObjectsValidationServiceWorker;
import diarsid.beam.server.domain.services.validation.WebObjectsValidationService;

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
        return new WebObjectsValidationServiceWorker();
    }
    
    @Bean
    public UsersValidationService usersValidationService() {
        return new UsersValidationServiceWorker();
    }
}
