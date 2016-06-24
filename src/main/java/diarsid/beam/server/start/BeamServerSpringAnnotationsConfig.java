/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.start;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import diarsid.beam.server.data.ObjectDataProvider;
import diarsid.beam.server.services.resources.objects.ObjectResource;

/**
 *
 * @author Diarsid
 */

@Configuration
public class BeamServerSpringAnnotationsConfig {
    
    public BeamServerSpringAnnotationsConfig() {
    }
    
    @Bean    
    public ObjectDataProvider objectDataProvider() {
        return new ObjectDataProvider();
    }
    
    @Bean  
    public ObjectResource objectResource(ObjectDataProvider objectDataProvider) {
        return new ObjectResource(objectDataProvider);
    }
}
