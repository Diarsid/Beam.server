/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.domain.entities;

import diarsid.beam.server.domain.services.exceptions.WebPlacementNameInvalidException;

/**
 *
 * @author Diarsid
 */
public enum WebPlacement {
    
    WEBPANEL,
    BOOKMARKS;
    
    public static void validatePlacementName(String name) {
        for (WebPlacement place : values()) {
            if ( place.name().equals(name.toUpperCase()) ) {
                return;
            }
        }
        throw new WebPlacementNameInvalidException(name + " is invalid placement name.");
    }
    
    public static WebPlacement placementOf(String name) {
        return valueOf(name.toUpperCase());            
    }
}
