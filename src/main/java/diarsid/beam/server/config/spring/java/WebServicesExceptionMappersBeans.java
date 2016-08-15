/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config.spring.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import diarsid.beam.server.services.web.providers.MapperBadDataRequestArgumentsException;
import diarsid.beam.server.services.web.providers.MapperNickNameIsNotFreeException;
import diarsid.beam.server.services.web.providers.MapperUserLoginInvalidException;
import diarsid.beam.server.services.web.providers.MapperUserRegistrationInvalidException;
import diarsid.beam.server.services.web.providers.MapperUsersServiceUnknownLogicException;
import diarsid.beam.server.services.web.providers.MapperWebObjectNameInvalidException;
import diarsid.beam.server.services.web.providers.MapperWebObjectUrlInvalidException;

/**
 *
 * @author Diarsid
 */

@Configuration
public class WebServicesExceptionMappersBeans {
    
    public WebServicesExceptionMappersBeans() {
    }
    
    @Bean
    public MapperBadDataRequestArgumentsException mapperBadDataRequestArgumentsException() {
        return new MapperBadDataRequestArgumentsException();
    }
    
    @Bean 
    public MapperNickNameIsNotFreeException mapperNickNameIsNotFreeException() {
        return new MapperNickNameIsNotFreeException();
    }
    
    @Bean
    public MapperUserLoginInvalidException mapperUserLoginInvalidException() {
        return new MapperUserLoginInvalidException();
    }
    
    @Bean
    public MapperUserRegistrationInvalidException mapperUserRegistrationInvalidException() {
        return new MapperUserRegistrationInvalidException();
    }
    
    @Bean
    public MapperUsersServiceUnknownLogicException mapperUsersServiceUnknownLogicException() {
        return new MapperUsersServiceUnknownLogicException();
    }
    
    @Bean
    public MapperWebObjectNameInvalidException mapperWebObjectNameInvalidException() {
        return new MapperWebObjectNameInvalidException();
    }
    
    @Bean
    public MapperWebObjectUrlInvalidException mapperWebObjectUrlInvalidException() {
        return new MapperWebObjectUrlInvalidException();
    }
}
