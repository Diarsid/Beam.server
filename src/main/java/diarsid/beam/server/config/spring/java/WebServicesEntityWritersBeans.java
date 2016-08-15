/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config.spring.java;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import diarsid.beam.server.services.web.providers.WriterJsonErrorObject;
import diarsid.beam.server.services.web.providers.WriterObjectData;
import diarsid.beam.server.util.JavaObjectToJsonConverter;

/**
 *
 * @author Diarsid
 */

@Configuration
@Import(UtilBeans.class)
public class WebServicesEntityWritersBeans {
    
    public WebServicesEntityWritersBeans() {
    }    
    
    @Bean
    public WriterObjectData writerObjectData(JavaObjectToJsonConverter toJsonConverter) {
        return new WriterObjectData(toJsonConverter);
    }
    
    @Bean
    public WriterJsonErrorObject writerJsonErrorObject(JavaObjectToJsonConverter toJsonConverter) {
        return new WriterJsonErrorObject(toJsonConverter);
    }
}
