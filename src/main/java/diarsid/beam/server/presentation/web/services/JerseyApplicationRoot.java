/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.services;

import java.util.Set;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 *
 * @author Diarsid
 */

@ApplicationPath("/services")
@Component
public class JerseyApplicationRoot extends ResourceConfig {
    
    public JerseyApplicationRoot(Set<Class<?>> jaxRsClasses) {
        super(jaxRsClasses);
    }
}
