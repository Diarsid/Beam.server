/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.json.dto;

/**
 *
 * @author Diarsid
 */
public class JsonPlacementAndDirectory {
    
    private final String place;
    private final String dir;
    
    public JsonPlacementAndDirectory(String place, String dir) {
        this.place = place;
        this.dir = dir;
    }

    public String getPlace() {
        return this.place;
    }

    public String getDirectory() {
        return this.dir;
    }
}
