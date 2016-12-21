/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.services.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import diarsid.beam.server.presentation.web.services.JAXRSAutoRegistrableComponent;
import diarsid.beam.server.presentation.web.services.filters.bindings.AdministratorRightsRequired;

/**
 *
 * @author Diarsid
 */

@Component
@Provider
@AdministratorRightsRequired
public class AdministratorAccessFilter 
        implements 
                ContainerRequestFilter,
                JAXRSAutoRegistrableComponent {
    
    public AdministratorAccessFilter() {
    }
    
    @Override
    public void filter(ContainerRequestContext request) throws IOException { 
        //TODO
    }
}
