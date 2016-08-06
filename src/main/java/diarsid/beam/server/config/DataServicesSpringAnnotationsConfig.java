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
import diarsid.beam.server.data.entities.WebItemsOrderer;
import diarsid.beam.server.data.services.keys.KeysService;
import diarsid.beam.server.data.services.keys.KeysServiceWorker;
import diarsid.beam.server.data.services.webitmes.UserWebContentService;
import diarsid.beam.server.data.services.webitmes.UserWebContentServiceValidationWrapper;
import diarsid.beam.server.data.services.webitmes.UserWebContentServiceWorker;
import diarsid.beam.server.data.services.webitmes.UserWebItemsDataOperator;
import diarsid.beam.server.data.services.webitmes.UserWebItemsDataOperatorWorker;
import diarsid.beam.server.data.services.webitmes.WebItemInputValidator;
import diarsid.beam.server.util.NameIncrementor;
import diarsid.beam.server.util.RandomStringGenerator;

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
    public UserWebContentService webContentService(
            WebItemInputValidator webItemInputValidator,
            WebItemsOrderer webItemsOrderer,
            NameIncrementor nameIncrementor,
            UserWebItemsDataOperator userWebItemsDataOperator) {
        UserWebContentService unvalidatedServiceWorker = new UserWebContentServiceWorker(
                userWebItemsDataOperator, webItemsOrderer, nameIncrementor);
        UserWebContentService validatedService = new UserWebContentServiceValidationWrapper(
                unvalidatedServiceWorker, webItemInputValidator);
        return validatedService;
    }
    
    @Bean
    public UserWebItemsDataOperator userWebItemsDataOperator(
            RepositoryWebDirectories dirsRepo, 
            RepositoryUsers usersRepo, 
            RepositoryWebPages webPagesRepo) {
        return new UserWebItemsDataOperatorWorker(usersRepo, dirsRepo, webPagesRepo);
    }
    
    @Bean
    public WebItemsOrderer webItemsOrderer() {
        return new WebItemsOrderer();
    }
    
    @Bean
    public WebItemInputValidator webItemInputValidator() {
        return new WebItemInputValidator();
    }
    
    @Bean
    public NameIncrementor nameIncrementor() {
        return new NameIncrementor();
    }
}
