/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config.spring.java;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import diarsid.beam.server.data.daos.DaoKeys;
import diarsid.beam.server.data.daos.DaoUsers;
import diarsid.beam.server.data.daos.DaoWebDirectories;
import diarsid.beam.server.data.daos.SpringDataJpaDaoKeys;
import diarsid.beam.server.data.daos.SpringDataJpaDaoUsers;
import diarsid.beam.server.data.daos.SpringDataJpaDaoWebDirectories;
import diarsid.beam.server.data.daos.springdata.repositories.jpa.RepositoryKeys;
import diarsid.beam.server.data.daos.springdata.repositories.jpa.RepositoryUsers;
import diarsid.beam.server.data.daos.springdata.repositories.jpa.RepositoryWebDirectories;

/**
 *
 * @author Diarsid
 */

@Configuration
public class DaosBeans {
    
    public DaosBeans() {
    }
    
    @Bean
    public DaoKeys daoKeys(RepositoryKeys repositoryKeys) {
        return new SpringDataJpaDaoKeys(repositoryKeys);
    }
    
    @Bean
    public DaoUsers daoUsers(RepositoryUsers repositoryUsers) {
        return new SpringDataJpaDaoUsers(repositoryUsers);
    }
    
    @Bean DaoWebDirectories daoWebDirectories(RepositoryWebDirectories repositoryWebDirectories) {
        return new SpringDataJpaDaoWebDirectories(repositoryWebDirectories);
    }
}
