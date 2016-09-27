/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.presentation.web.services.filters.bindings;

import java.lang.annotation.Retention;

import javax.ws.rs.NameBinding;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author Diarsid
 */

@NameBinding
@Retention(RUNTIME)
public @interface AdministratorRightsRequired {
    
}
