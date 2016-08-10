/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springtests.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import diarsid.beam.server.data.daos.springdata.repositories.jpa.RepositoryUsers;
import diarsid.beam.server.data.daos.springdata.repositories.jpa.RepositoryWebDirectories;
import diarsid.beam.server.data.daos.springdata.repositories.jpa.RepositoryWebPages;
import diarsid.beam.server.data.services.webobjects.WebObjectsOrderer;
import diarsid.beam.server.data.services.webobjects.UserWebObjectsServiceValidationWrapper;
import diarsid.beam.server.data.services.webobjects.UserWebObjectsServiceWorker;
import diarsid.beam.server.data.services.webobjects.UserWebObjectsDataOperatorWorker;
import diarsid.beam.server.data.services.webobjects.WebObjectsPropertiesValidator;
import diarsid.beam.server.data.services.webobjects.WebObjectsNamesIncrementor;
import diarsid.beam.server.data.services.webobjects.UserWebObjectsService;
import diarsid.beam.server.data.services.webobjects.UserWebObjectsDataOperator;

/**
 *
 * @author Diarsid
 */

@Configuration
@Import({DataConnectionTestConfig.class})
public class DataServiceTestConfig {
    
    public DataServiceTestConfig() {
    }
    
    @Bean 
    public UserWebObjectsService userWebContentServiceWorker(
            UserWebObjectsDataOperator dataOperator,
            WebObjectsOrderer webItemsOrderer,
            WebObjectsNamesIncrementor incrementor,
            WebObjectsPropertiesValidator validator) {
        UserWebObjectsServiceWorker service = 
                new UserWebObjectsServiceWorker(dataOperator, webItemsOrderer, incrementor);
        UserWebObjectsServiceValidationWrapper validatedService = 
                new UserWebObjectsServiceValidationWrapper(service, validator);
        return validatedService;
    }
    
    @Bean
    public UserWebObjectsDataOperator userWebItemsDataOperator(
            RepositoryUsers usersRepo,
            RepositoryWebDirectories dirsRepo,
            RepositoryWebPages webPagesRepo) {
        UserWebObjectsDataOperator dataOperator = 
                new UserWebObjectsDataOperatorWorker(usersRepo, dirsRepo, webPagesRepo);
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
    public WebObjectsPropertiesValidator webItemInputValidator() {
        return new WebObjectsPropertiesValidator();
    }
}
