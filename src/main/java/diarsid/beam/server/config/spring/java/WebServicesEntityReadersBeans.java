/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config.spring.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import diarsid.beam.server.presentation.web.json.util.JsonToJavaObjectConverter;
import diarsid.beam.server.presentation.web.services.providers.ReaderJsonPayload;
import diarsid.beam.server.presentation.web.services.providers.ReaderJsonPlacementAndDirectory;
import diarsid.beam.server.presentation.web.services.providers.ReaderJsonUserLogin;
import diarsid.beam.server.presentation.web.services.providers.ReaderJsonUserRegistration;
import diarsid.beam.server.presentation.web.services.providers.ReaderJsonWebPage;

/**
 *
 * @author Diarsid
 */

@Configuration
@Import(UtilBeans.class)
public class WebServicesEntityReadersBeans {
    
    public WebServicesEntityReadersBeans() {
    }
    
    @Bean
    public ReaderJsonUserLogin readerUserLoginData(JsonToJavaObjectConverter toJavaObjectConverter) {
        return new ReaderJsonUserLogin(toJavaObjectConverter);
    }
    
    @Bean
    public ReaderJsonUserRegistration readerUserRegistrationData(
            JsonToJavaObjectConverter toJavaObjectConverter) {
        return new ReaderJsonUserRegistration(toJavaObjectConverter);
    }
        
    @Bean
    public ReaderJsonPayload readerValidatablePayload(
            JsonToJavaObjectConverter toJavaObjectConverter) {
        return new ReaderJsonPayload(toJavaObjectConverter);
    }
    
    @Bean
    public ReaderJsonWebPage readerJsonWebPage(
            JsonToJavaObjectConverter toJavaObjectConverter) {
        return new ReaderJsonWebPage(toJavaObjectConverter);
    }
    
    @Bean
    public ReaderJsonPlacementAndDirectory readerJsonPlacementAndDirectory(
            JsonToJavaObjectConverter toJavaObjectConverter) {
        return new ReaderJsonPlacementAndDirectory(toJavaObjectConverter);
    }
}
