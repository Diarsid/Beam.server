/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import diarsid.beam.server.util.RandomHexadecimalStringGenerator;
import diarsid.beam.server.util.RandomStringGenerator;

/**
 *
 * @author Diarsid
 */

@Configuration
public class UtilSpringAnnotationsConfig {
    
    public UtilSpringAnnotationsConfig() {
    }
    
    @Bean 
    public RandomStringGenerator randomStringGenerator() {
        RandomStringGenerator generator = new RandomHexadecimalStringGenerator();
        return generator;
    }
}
