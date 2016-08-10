/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.util.ArrayList;
import java.util.List;

import diarsid.beam.server.data.entities.WebPlacement;
import diarsid.beam.server.data.entities.jpa.PersistableUser;
import diarsid.beam.server.data.entities.jpa.PersistableWebDirectory;

/**
 *
 * @author Diarsid
 */
public class FakeWebDirsProducer {
    
    public final static String DIR_NAME_TEMPLATE;
    static {
        DIR_NAME_TEMPLATE = "dir_name_";
    }
    
    private FakeWebDirsProducer() {
    }
    
    public static List<PersistableWebDirectory> newFakeDirs(
            PersistableUser owner, WebPlacement place, int nameIncrementor, int qty) {
        List<PersistableWebDirectory> newDirs = new ArrayList<>();        
        PersistableWebDirectory newDir = null;
        for (int i = 0; i < qty; i++) {
            newDir = new PersistableWebDirectory();
            newDir.setUser(owner);
            newDir.setName(DIR_NAME_TEMPLATE + nameIncrementor);
            newDir.setOrder(i);
            newDir.setPlace(place.name());
            nameIncrementor++;         
            newDirs.add(newDir);
        }
        return newDirs;
    }
    
    public static PersistableWebDirectory newFakeDir(
            PersistableUser owner, WebPlacement place, int nameIncrementor) {
        PersistableWebDirectory newDir = new PersistableWebDirectory();
        newDir.setUser(owner);
        newDir.setName(DIR_NAME_TEMPLATE + nameIncrementor);
        newDir.setOrder(0);
        newDir.setPlace(place.name());
        return newDir;
    }
}
