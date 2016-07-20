/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Diarsid
 */

@Configuration
@EnableTransactionManagement
@Import({
    BeamServerDataSpringAnnotationsConfig.class, 
    BeamServerServicesSpringAnnotationsConfig.class,
    BeamServerAuthSpringAnnotationsConfig.class})
@PropertySources({
    @PropertySource("classpath:beam.server.properties")
})
public class BeamServerSpringAnnotationsConfig {
    
    public BeamServerSpringAnnotationsConfig() {
    }
    
}
