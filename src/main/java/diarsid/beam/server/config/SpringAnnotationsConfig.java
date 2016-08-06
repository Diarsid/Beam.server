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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Diarsid
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"diarsid.beam.server.data.daos.springdata.repositories.jpa"})
@Import({
    DataConnectionSpringAnnotationsConfig.class,
    DataServicesSpringAnnotationsConfig.class,
    WebServicesSpringAnnotationsConfig.class,
    AuthSpringAnnotationsConfig.class,
    UtilSpringAnnotationsConfig.class})
@PropertySources({
    @PropertySource("classpath:beam.server.properties")
})
public class SpringAnnotationsConfig {
    
    public SpringAnnotationsConfig() {
    }    
}
