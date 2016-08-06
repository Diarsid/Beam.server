/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.data.services.webitmes;

import java.util.List;

import diarsid.beam.server.data.entities.WebPlacement;
import diarsid.beam.server.data.entities.jpa.PersistableWebDirectory;

/**
 *
 * @author Diarsid
 */

public interface UserWebContentService {
    
    boolean reorderUserWebDirectory(
            int userId, WebPlacement place, String dirName, int newOrder);
    
    boolean reorderUserWebPageOrder(
            int userId, WebPlacement place, String dirName, String pageName, int newOrder);
    
    boolean renameUserWebPage(
            int userId, WebPlacement place, String dirName, String oldPageName, String newPageName);
    
    boolean redirectUserWebPageUrl(
            int userId, WebPlacement place, String dirName, String pageName, String newUrl);
    
    boolean renameUserWebDirectory(
            int userId, WebPlacement place, String oldDirName, String newDirName);
    
    boolean moveUserWebDirectoryIntoPlace(
            int userId, WebPlacement oldPlace, WebPlacement newPlace, String dirName);
    
    boolean moveUserWebPageIntoDirectory(
            int userId, WebPlacement place, String oldDirName, String newDirName, String pageName);
    
    boolean moveUserWebPageIntoDirectoryAndOrder(
            int userId, 
            WebPlacement place, 
            String oldDirName, 
            String newDirName, 
            String pageName, 
            int movedPageNewOrder);
    
    boolean moveUserWebPageIntoDirectoryAndPlace(
            int userId, 
            WebPlacement oldPlace, 
            WebPlacement newPlace, 
            String oldDirName, 
            String newDirName, 
            String pageName);
    
    boolean deleteUserWebDirectory(
            int userId, WebPlacement place, String dirName);
    
    boolean deleteUserWebPage(
            int userId, WebPlacement place, String dirName, String pageName);
    
    boolean createUserWebPage(
            int userId, WebPlacement place, String dirName, String pageName, String pageUrl);
    
    boolean createUserWebDirectory(
            int userId, WebPlacement place, String dirName);
    
    List<PersistableWebDirectory> getUserPanelWebDirectories(int userId);
    
    List<PersistableWebDirectory> getUserBookmarksWebDirectories(int userId);
    
    PersistableWebDirectory getUserWebDirectory(int userId, WebPlacement place, String dirName) ;
    
    List<PersistableWebDirectory> getAllUserWebDirectories(int userId);
}
