/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springtests.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import diarsid.beam.server.data.daos.DaoUsers;
import diarsid.beam.server.data.daos.DaoWebDirectories;
import diarsid.beam.server.domain.services.webobjects.UserWebObjectsDataOperator;
import diarsid.beam.server.domain.services.webobjects.UserWebObjectsDataOperatorWorker;
import diarsid.beam.server.domain.services.webobjects.UserWebObjectsService;
import diarsid.beam.server.domain.services.webobjects.UserWebObjectsServiceValidationWrapper;
import diarsid.beam.server.domain.services.webobjects.UserWebObjectsServiceWorker;
import diarsid.beam.server.domain.services.webobjects.WebObjectsNamesIncrementor;
import diarsid.beam.server.domain.services.webobjects.WebObjectsOrderer;
import diarsid.beam.server.domain.services.validation.WebObjectsValidationServiceWorker;

/**
 *
 * @author Diarsid
 */

@Configuration
@Import({
    DataConnectionTestConfig.class, 
    DaosTestConfig.class})
public class DomainServicesTestConfig {
    
    public DomainServicesTestConfig() {
    }
    
    @Bean 
    public UserWebObjectsService userWebContentServiceWorker(
            UserWebObjectsDataOperator dataOperator,
            WebObjectsOrderer webItemsOrderer,
            WebObjectsNamesIncrementor incrementor,
            WebObjectsValidationServiceWorker validator) {
        UserWebObjectsServiceWorker service = 
                new UserWebObjectsServiceWorker(dataOperator, webItemsOrderer, incrementor);
        UserWebObjectsServiceValidationWrapper validatedService = 
                new UserWebObjectsServiceValidationWrapper(service, validator);
        return validatedService;
    }
    
    @Bean
    public UserWebObjectsDataOperator userWebItemsDataOperator(
            DaoUsers usersDao,
            DaoWebDirectories dirsDao) {
        UserWebObjectsDataOperator dataOperator = 
                new UserWebObjectsDataOperatorWorker(usersDao, dirsDao);
        return dataOperator;
    }
    
    @Bean
    public WebObjectsOrderer webItemsOrderer() {
        return new WebObjectsOrderer();
    }
    
    @Bean
    public WebObjectsNamesIncrementor nameIncrementor() {
        return new WebObjectsNamesIncrementor();
    }
    
    @Bean
    public WebObjectsValidationServiceWorker webItemInputValidator() {
        return new WebObjectsValidationServiceWorker();
    }
}
