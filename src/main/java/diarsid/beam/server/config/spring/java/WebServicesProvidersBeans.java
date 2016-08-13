/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config.spring.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import diarsid.beam.server.services.web.providers.ReaderObjectData;
import diarsid.beam.server.services.web.providers.ReaderUserLoginData;
import diarsid.beam.server.services.web.providers.ReaderUserRegistrationData;
import diarsid.beam.server.services.web.providers.WriterJsonErrorObject;
import diarsid.beam.server.services.web.providers.WriterObjectData;
import diarsid.beam.server.util.JavaObjectToJsonConverter;
import diarsid.beam.server.util.JsonToJavaObjectConverter;

/**
 *
 * @author Diarsid
 */

@Configuration
@Import(UtilBeans.class)
public class WebServicesProvidersBeans {
    
    public WebServicesProvidersBeans() {
    }
    
    @Bean
    public WriterObjectData writerObjectData(JavaObjectToJsonConverter toJsonConverter) {
        return new WriterObjectData(toJsonConverter);
    }
    
    @Bean
    public WriterJsonErrorObject writerJsonErrorObject(JavaObjectToJsonConverter toJsonConverter) {
        return new WriterJsonErrorObject(toJsonConverter);
    }
    
    @Bean
    public ReaderUserLoginData readerUserLoginData(JsonToJavaObjectConverter toJavaObjectConverter) {
        return new ReaderUserLoginData(toJavaObjectConverter);
    }
    
    @Bean
    public ReaderUserRegistrationData readerUserRegistrationData(
            JsonToJavaObjectConverter toJavaObjectConverter) {
        return new ReaderUserRegistrationData(toJavaObjectConverter);
    }
    
    @Bean
    public ReaderObjectData readerObjectData(JsonToJavaObjectConverter toJavaObjectConverter) {
        return new ReaderObjectData(toJavaObjectConverter);
    }
}
