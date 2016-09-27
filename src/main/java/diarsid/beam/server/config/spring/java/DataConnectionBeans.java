/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config.spring.java;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import diarsid.beam.server.domain.entities.jpa.PersistableKey;
import diarsid.beam.server.domain.entities.jpa.PersistableUser;
import diarsid.beam.server.domain.entities.jpa.PersistableWebDirectory;
import diarsid.beam.server.domain.entities.jpa.PersistableWebPage;

/**
 *
 * @author Diarsid
 */

@Configuration
public class DataConnectionBeans {
    
    @Autowired
    private Environment environment;
    
    public DataConnectionBeans() {
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
                PersistableWebDirectory.class);
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.dialect", environment.getProperty("jdbc.orm.hib.dialect"));
        builder.addProperties(properties);
        return builder.buildSessionFactory();
    }
    
    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        //vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = 
                new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("diarsid.beam.server.domain.entities.jpa");
        factory.setDataSource(dataSource);
        Properties props = new Properties();
        props.put("spring.jpa.show-sql", "true");
        props.put("spring.jpa.properties.hibernate.format_sql", "true");
        props.put("spring.jpa.properties.format_sql", "true");
        factory.setJpaProperties(props);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
