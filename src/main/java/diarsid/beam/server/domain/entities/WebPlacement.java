/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.domain.entities;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

/**
 *
 * @author Diarsid
 */
public enum WebPlacement {
    
    WEBPANEL,
    BOOKMARKS;
    
    public static Optional<WebPlacement> valueOfIgnoreCase(String name) {
        if ( name.isEmpty() ) {
            return empty();
        } else {
            try {
                return of(valueOf(name.toUpperCase()));
            } catch (IllegalArgumentException e) {
                return empty();
            } 
        }               
    }
}
