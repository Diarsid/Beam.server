/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.services;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Diarsid
 */

@ApplicationPath("/services")
public class JerseyApplicationRoot extends ResourceConfig {
    
    public JerseyApplicationRoot() {
        super();
        packages("diarsid.beam.server.services");
    }
}
