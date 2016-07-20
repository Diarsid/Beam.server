/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;

import diarsid.beam.server.data.ObjectDataProvider;
import diarsid.beam.server.data.daos.DaoKeys;
import diarsid.beam.server.data.daos.DaoUsers;
import diarsid.beam.server.data.daos.HibernateDaoKeys;
import diarsid.beam.server.data.daos.HibernateDaoUsers;
import diarsid.beam.server.data.entities.jpa.PersistableKey;
import diarsid.beam.server.data.entities.jpa.PersistableUser;
import diarsid.beam.server.data.entities.jpa.PersistableUserDirs;
import diarsid.beam.server.data.entities.jpa.PersistableWebDirectory;
import diarsid.beam.server.data.entities.jpa.PersistableWebPage;
import diarsid.beam.server.util.RandomHexadecimalStringGenerator;
import diarsid.beam.server.util.RandomStringGenerator;

/**
 *
 * @author Diarsid
 */

@Configuration
public class BeamServerDataSpringAnnotationsConfig {
    
    @Autowired
    private Environment environment;
    
    public BeamServerDataSpringAnnotationsConfig() {
    }
    
    @Bean    
    public ObjectDataProvider objectDataProvider() {
        return new ObjectDataProvider();
    }
    
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(environment.getProperty("jdbc.mysql.url"));
        dataSource.setUsername(environment.getProperty("jdbc.mysql.user"));
        dataSource.setPassword(environment.getProperty("jdbc.mysql.pass"));
        dataSource.setDriverClassName(environment.getProperty("jdbc.mysql.driver"));
        return dataSource;
    }
    
    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
        builder.addAnnotatedClasses(
                PersistableKey.class,
                PersistableWebPage.class, 
                PersistableUser.class,
                PersistableWebDirectory.class,
                PersistableUserDirs.class);
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.dialect", environment.getProperty("jdbc.orm.hib.dialect"));
        builder.addProperties(properties);
        return builder.buildSessionFactory();
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {     
        HibernateTransactionManager txManager = new HibernateTransactionManager(sessionFactory);
        return txManager;
    }
    
    @Bean
    public DaoKeys keysDao(SessionFactory sessionFactory) {
        DaoKeys keysDao = new HibernateDaoKeys(sessionFactory);
        return keysDao;
    }
    
    @Bean
    public DaoUsers daoUsers(SessionFactory sessionFactory) {
        return new HibernateDaoUsers(sessionFactory);
    }
    
    @Bean RandomStringGenerator randomStringGenerator() {
        RandomStringGenerator generator = new RandomHexadecimalStringGenerator();
        return generator;
    }
}
