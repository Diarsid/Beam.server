/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.data.daos;

import java.util.List;

import diarsid.beam.server.data.entities.jpa.PersistableWebDirectory;

/**
 *
 * @author Diarsid
 */
public interface DaoWebDirectories {
    
    List<PersistableWebDirectory> getByUserId(int userId);
    
    List<PersistableWebDirectory> getByPlaceAndUserId(String place, int userId);
    
    PersistableWebDirectory findByNameAndPlaceAndUserId(String name, String place, int userId);
    
    int countByUserAndPlace(String place, int userId);
    
    List<PersistableWebDirectory> saveAndFlush(List<PersistableWebDirectory> dirs);
    
    PersistableWebDirectory saveAndFlush(PersistableWebDirectory dir);
    
    void deleteAndFlush(PersistableWebDirectory dir);
    
    boolean exists(int id);
}
