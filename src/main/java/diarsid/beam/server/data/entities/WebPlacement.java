/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.data.entities;

/**
 *
 * @author Diarsid
 */
public enum WebPlacement {
    PANEL,
    BOOKMARKS;
    
    public static WebPlacement valueOfIgnoreCase(String name) {
        return valueOf(name.toUpperCase());
    }
}
