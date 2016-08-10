/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import diarsid.beam.server.data.ObjectDataServiceWorker;
import diarsid.beam.server.data.daos.DaoKeys;
import diarsid.beam.server.data.daos.DaoUsers;
import diarsid.beam.server.data.daos.HibernateDaoKeys;
import diarsid.beam.server.data.daos.HibernateDaoUsers;
import diarsid.beam.server.data.daos.springdata.repositories.jpa.RepositoryUsers;
import diarsid.beam.server.data.daos.springdata.repositories.jpa.RepositoryWebDirectories;
import diarsid.beam.server.data.daos.springdata.repositories.jpa.RepositoryWebPages;
import diarsid.beam.server.data.services.webobjects.WebObjectsOrderer;
import diarsid.beam.server.data.services.keys.KeysService;
import diarsid.beam.server.data.services.keys.KeysServiceWorker;
import diarsid.beam.server.data.services.webobjects.UserWebObjectsServiceValidationWrapper;
import diarsid.beam.server.data.services.webobjects.UserWebObjectsServiceWorker;
import diarsid.beam.server.data.services.webobjects.UserWebObjectsDataOperatorWorker;
import diarsid.beam.server.data.services.webobjects.WebObjectsPropertiesValidator;
import diarsid.beam.server.data.services.webobjects.WebObjectsNamesIncrementor;
import diarsid.beam.server.util.RandomStringGenerator;
import diarsid.beam.server.data.services.webobjects.UserWebObjectsService;
import diarsid.beam.server.data.services.webobjects.UserWebObjectsDataOperator;

/**
 *
 * @author Diarsid
 */

@Configuration
@Import({
    DataConnectionSpringAnnotationsConfig.class, 
    UtilSpringAnnotationsConfig.class})
public class DataServicesSpringAnnotationsConfig {
    
    @Autowired
    private Environment environment;
    
    public DataServicesSpringAnnotationsConfig() {
    }
    
    @Bean
    public DaoKeys daoKeys(SessionFactory sessionFactory) {
        DaoKeys keysDao = new HibernateDaoKeys(sessionFactory);
        return keysDao;
    }
    
    @Bean
    public DaoUsers daoUsers(SessionFactory sessionFactory) {
        return new HibernateDaoUsers(sessionFactory);
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
            RepositoryWebDirectories dirsRepo, 
            RepositoryUsers usersRepo, 
            RepositoryWebPages webPagesRepo) {
        return new UserWebObjectsDataOperatorWorker(usersRepo, dirsRepo, webPagesRepo);
    }
    
    @Bean
    public WebObjectsOrderer webItemsOrderer() {
        return new WebObjectsOrderer();
    }
    
    @Bean
    public WebObjectsPropertiesValidator webItemInputValidator() {
        return new WebObjectsPropertiesValidator();
    }
    
    @Bean
    public WebObjectsNamesIncrementor nameIncrementor() {
        return new WebObjectsNamesIncrementor();
    }
}
