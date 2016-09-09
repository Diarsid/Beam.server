/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config.spring.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import diarsid.beam.server.presentation.web.json.util.JavaObjectToJsonConverter;
import diarsid.beam.server.presentation.web.json.util.JavaObjectToJsonConverterWorkerGson;
import diarsid.beam.server.presentation.web.json.util.JsonToJavaObjectConverter;
import diarsid.beam.server.presentation.web.json.util.JsonToJavaObjectConverterWorkerGson;
import diarsid.beam.server.domain.util.RandomHexadecimalStringGenerator;
import diarsid.beam.server.domain.util.RandomStringGenerator;

/**
 *
 * @author Diarsid
 */

@Configuration
public class UtilBeans {
    
    public UtilBeans() {
    }
    
    @Bean 
    public RandomStringGenerator randomStringGenerator() {
        RandomStringGenerator generator = new RandomHexadecimalStringGenerator();
        return generator;
    }
    
    @Bean
    public JavaObjectToJsonConverter toJsonConverter() {
        return new JavaObjectToJsonConverterWorkerGson();
    }
    
    @Bean
    public JsonToJavaObjectConverter toJavaObjectConverter() {
        return new JsonToJavaObjectConverterWorkerGson();
    }
}
