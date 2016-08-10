/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springtests.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Diarsid
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"diarsid.beam.server.data.daos.springdata.repositories.jpa"})
@Import({
    DataConnectionTestConfig.class, 
    DataServiceTestConfig.class})
public class AppTestConfig {
    
    public AppTestConfig() {
    }
}
