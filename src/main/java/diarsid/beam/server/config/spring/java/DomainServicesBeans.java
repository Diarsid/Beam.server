/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config.spring.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import diarsid.beam.server.data.ObjectDataServiceWorker;
import diarsid.beam.server.data.daos.DaoKeys;
import diarsid.beam.server.data.daos.DaoUsers;
import diarsid.beam.server.data.daos.DaoWebDirectories;
import diarsid.beam.server.services.domain.keys.KeysService;
import diarsid.beam.server.services.domain.keys.KeysServiceWorker;
import diarsid.beam.server.services.domain.validation.UsersInfoValidator;
import diarsid.beam.server.services.domain.users.UsersService;
import diarsid.beam.server.services.domain.users.UsersServiceWorker;
import diarsid.beam.server.services.domain.webobjects.UserWebObjectsDataOperator;
import diarsid.beam.server.services.domain.webobjects.UserWebObjectsDataOperatorWorker;
import diarsid.beam.server.services.domain.webobjects.UserWebObjectsService;
import diarsid.beam.server.services.domain.webobjects.UserWebObjectsServiceValidationWrapper;
import diarsid.beam.server.services.domain.webobjects.UserWebObjectsServiceWorker;
import diarsid.beam.server.services.domain.webobjects.WebObjectsNamesIncrementor;
import diarsid.beam.server.services.domain.webobjects.WebObjectsOrderer;
import diarsid.beam.server.services.domain.validation.WebObjectsPropertiesValidator;
import diarsid.beam.server.util.RandomStringGenerator;

/**
 *
 * @author Diarsid
 */

@Configuration
@Import({
    DataConnectionBeans.class, 
    DomainServicesValidationBeans.class, 
    DaosBeans.class,
    UtilBeans.class})
public class DomainServicesBeans {
    
    @Autowired
    private Environment environment;
    
    public DomainServicesBeans() {
    }
            
    @Bean    
    public ObjectDataServiceWorker objectDataService() {
        return new ObjectDataServiceWorker();
    }
    
    @Bean
    public KeysService keysService(DaoKeys keysDao, RandomStringGenerator stringGenerator) {
        int keysQty = Integer.valueOf(environment.getProperty("beam.server.keysqty"));
        KeysService keysManager = new KeysServiceWorker(keysDao, stringGenerator, keysQty);
        return keysManager;
    }
    
    @Bean
    public UsersService usersService(DaoUsers daoUsers, UsersInfoValidator usersInfoValidator) {
        return new UsersServiceWorker(daoUsers, usersInfoValidator);
    }
    
    @Bean
    public UserWebObjectsService webContentService(
            WebObjectsPropertiesValidator webItemInputValidator,
            WebObjectsOrderer webItemsOrderer,
            WebObjectsNamesIncrementor nameIncrementor,
            UserWebObjectsDataOperator userWebItemsDataOperator) {
        UserWebObjectsService unvalidatedServiceWorker = new UserWebObjectsServiceWorker(
                userWebItemsDataOperator, webItemsOrderer, nameIncrementor);
        UserWebObjectsService validatedService = new UserWebObjectsServiceValidationWrapper(
                unvalidatedServiceWorker, webItemInputValidator);
        return validatedService;
    }
    
    @Bean
    public UserWebObjectsDataOperator userWebItemsDataOperator(
            DaoWebDirectories daoDirs, 
            DaoUsers daoUsers) {
        return new UserWebObjectsDataOperatorWorker(daoUsers, daoDirs);
    }
    
    @Bean
    public WebObjectsOrderer webItemsOrderer() {
        return new WebObjectsOrderer();
    }
    
    @Bean
    public WebObjectsNamesIncrementor nameIncrementor() {
        return new WebObjectsNamesIncrementor();
    }    
}
