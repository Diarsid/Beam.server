/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config.spring.java;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author Diarsid
 */

@Configuration
@Import({
    WebServicesExceptionMappersBeans.class, 
    WebServicesEntityReadersBeans.class, 
    WebServicesEntityWritersBeans.class})
public class WebServicesProvidersBeans {
    
    public WebServicesProvidersBeans() {
    }
}
