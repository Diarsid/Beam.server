/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.domain.services.webobjects;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import diarsid.beam.server.domain.entities.jpa.PersistableWebDirectory;

/**
 *
 * @author Diarsid
 */

@Transactional
public interface UserWebObjectsService {
    
    boolean reorderUserWebDirectory(
            int userId, String place, String dirName, int newOrder);
    
    boolean reorderUserWebPageOrder(
            int userId, String place, String dirName, String pageName, int newOrder);
    
    boolean renameUserWebPage(
            int userId, String place, String dirName, String oldPageName, String newPageName);
    
    boolean redirectUserWebPageUrl(
            int userId, String place, String dirName, String pageName, String newUrl);
    
    boolean renameUserWebDirectory(
            int userId, String place, String oldDirName, String newDirName);
    
    boolean moveUserWebDirectoryIntoPlace(
            int userId, String oldPlace, String newPlace, String dirName);
    
    boolean moveUserWebPageIntoDirectory(
            int userId, String place, String oldDirName, String newDirName, String pageName);
    
    boolean moveUserWebPageIntoDirectoryAndOrder(
            int userId, 
            String place, 
            String oldDirName, 
            String newDirName, 
            String pageName, 
            int movedPageNewOrder);
    
    boolean moveUserWebPageIntoDirectoryAndPlace(
            int userId, 
            String oldPlace, 
            String newPlace, 
            String oldDirName, 
            String newDirName, 
            String pageName);
    
    boolean deleteUserWebDirectory(
            int userId, String place, String dirName);
    
    boolean deleteUserWebPage(
            int userId, String place, String dirName, String pageName);
    
    boolean createUserWebPage(
            int userId, String place, String dirName, String pageName, String pageUrl);
    
    boolean createUserWebDirectory(
            int userId, String place, String dirName);
    
    List<PersistableWebDirectory> getUserWebDirectoriesInPlace(int userId, String place);
    
    PersistableWebDirectory getUserWebDirectory(int userId, String place, String dirName) ;
    
    List<PersistableWebDirectory> getUserAllWebDirectories(int userId);
}
