/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.domain.services.webobjects;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import diarsid.beam.server.domain.entities.WebPlacement;
import diarsid.beam.server.domain.entities.jpa.PersistableUser;
import diarsid.beam.server.domain.entities.jpa.PersistableWebDirectory;
import diarsid.beam.server.domain.entities.jpa.PersistableWebPage;

/**
 *
 * @author Diarsid
 */
@Transactional
public interface UserWebObjectsDataOperator {

    PersistableUser findUserNotNull(int userId);
    
    void checkUser(int userId);
    
    int countWebDirectoriesInPlace(WebPlacement place, int userId);
    
    PersistableWebDirectory findDirectoryFromDirectoriesNotNull(
            List<PersistableWebDirectory> dirs, String dirName);
    
    List<PersistableWebDirectory> findAllUserWebDirectories(int userId);

    List<PersistableWebDirectory> findWebDirectories(WebPlacement place, int userId);

    List<PersistableWebDirectory> findWebDirectoriesNotEmpty(WebPlacement place, int userId);

    PersistableWebDirectory findWebDirectoryNotNull(String dirName, WebPlacement place, int userId);
    
    PersistableWebDirectory findWebDirectoryNotNullNotEmpty(
            String dirName, WebPlacement place, int userId);

    PersistableWebPage findWebPageInDirectoryNotNull(List<PersistableWebPage> pages, String pageName);
    
    List<PersistableWebDirectory> saveModifiedDirectories(List<PersistableWebDirectory> dirs);
    
    PersistableWebDirectory saveModifiedDirectory(PersistableWebDirectory dir);
        
    boolean deleteDirectory(PersistableWebDirectory dir);
}
