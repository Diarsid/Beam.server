/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.services.webitmes;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import diarsid.beam.server.data.entities.WebItemsOrderer;
import diarsid.beam.server.data.entities.WebPlacement;
import diarsid.beam.server.data.entities.jpa.PersistableUser;
import diarsid.beam.server.data.entities.jpa.PersistableWebDirectory;
import diarsid.beam.server.data.entities.jpa.PersistableWebPage;
import diarsid.beam.server.util.NameIncrementor;

import static java.util.Collections.emptyList;

import static diarsid.beam.server.data.entities.WebPlacement.BOOKMARKS;
import static diarsid.beam.server.data.entities.WebPlacement.PANEL;

/**
 *
 * @author Diarsid
 */

@Component
public class UserWebContentServiceWorker implements UserWebContentService {
     
    private final static Logger logger;
    static {
        logger = LoggerFactory.getLogger(UserWebContentServiceWorker.class);
    }
    
    private final UserWebItemsDataOperator dataOperator;
    private final WebItemsOrderer orderer;
    private final NameIncrementor nameIncrementor;
    
    public UserWebContentServiceWorker(
            UserWebItemsDataOperator dataOperator,
            WebItemsOrderer webItemsOrderer,
            NameIncrementor incrementor) {
        this.dataOperator = dataOperator;
        this.orderer = webItemsOrderer;
        this.nameIncrementor = incrementor;
    }

    @Override
    public boolean reorderUserWebDirectory(
            int userId, WebPlacement place, String dirName, int newOrder) {
        this.dataOperator.checkUser(userId);
        List<PersistableWebDirectory> dirs = 
                this.dataOperator.findWebDirectoriesNotEmpty(place, userId);
        PersistableWebDirectory reorderedDir = 
                this.dataOperator.findDirectoryFromDirectoriesNotNull(dirs, dirName);
        this.orderer.reorderWebItemsAccordingToNewOrder(dirs, reorderedDir.getOrder(), newOrder);        
        List<PersistableWebDirectory> savedDirs = this.dataOperator.saveModifiedDirectories(dirs);        
        PersistableWebDirectory savedDir = 
                this.dataOperator.findDirectoryFromDirectoriesNotNull(savedDirs, dirName);
        return ( savedDir.getOrder() == newOrder );
    }

    @Override
    public boolean reorderUserWebPageOrder(
            int userId, WebPlacement place, String dirName, String pageName, int newOrder) {
        this.dataOperator.checkUser(userId);
        PersistableWebDirectory dir = 
                this.dataOperator.findWebDirectoryNotNull(dirName, place, userId);        
        PersistableWebPage reorderedPage = 
                this.dataOperator.findWebPageInDirectoryNotNull(dir.getPages(), pageName);
        this.orderer.reorderWebItemsAccordingToNewOrder(
                dir.getPages(), reorderedPage.getOrder(), newOrder);        
        List<PersistableWebPage> savedPages = this.dataOperator.saveModifiedPages(dir.getPages());
        PersistableWebPage savedPage = 
                this.dataOperator.findWebPageInDirectoryNotNull(savedPages, pageName);
        return ( savedPage.getOrder() == newOrder );
    }

    @Override
    public boolean renameUserWebPage(
            int userId, WebPlacement place, String dirName, String oldPageName, String newPageName) {
        this.dataOperator.checkUser(userId);
        PersistableWebDirectory dir = 
                this.dataOperator.findWebDirectoryNotNullNotEmpty(dirName, place, userId);
        PersistableWebPage renamedPage = 
                this.dataOperator.findWebPageInDirectoryNotNull(dir.getPages(), oldPageName);
        renamedPage.setName(this.nameIncrementor.incrementName(dir.getPages(), newPageName));
        renamedPage = this.dataOperator.saveModifiedPage(renamedPage);
        return ( renamedPage.getName().equals(newPageName) );
    }
    
    @Override
    public boolean redirectUserWebPageUrl(
            int userId, WebPlacement place, String dirName, String pageName, String newUrl) {
        this.dataOperator.checkUser(userId);
        PersistableWebDirectory dir = 
                this.dataOperator.findWebDirectoryNotNullNotEmpty(dirName, place, userId);        
        PersistableWebPage redirectedPage = 
                this.dataOperator.findWebPageInDirectoryNotNull(dir.getPages(), pageName);
        redirectedPage.setUrl(newUrl);
        redirectedPage = this.dataOperator.saveModifiedPage(redirectedPage);
        return ( redirectedPage.getUrl().equals(newUrl) );
    }

    @Override
    public boolean renameUserWebDirectory(
            int userId, WebPlacement place, String oldDirName, String newDirName) {
        this.dataOperator.checkUser(userId);
        List<PersistableWebDirectory> dirs = 
                this.dataOperator.findWebDirectoriesNotEmpty(place, userId);
        PersistableWebDirectory renamedDir = 
                this.dataOperator.findDirectoryFromDirectoriesNotNull(dirs, oldDirName);
        renamedDir.setName(this.nameIncrementor.incrementName(dirs, newDirName));
        renamedDir = this.dataOperator.saveModifiedDirectory(renamedDir);
        return ( renamedDir.getName().equals(newDirName) );
    }
    
    @Override
    public boolean moveUserWebDirectoryIntoPlace(
            int userId, WebPlacement oldPlace, WebPlacement newPlace, String dirName) {
        this.dataOperator.checkUser(userId);
        if ( oldPlace.equals(newPlace) ) {
            return true;
        }
        List<PersistableWebDirectory> oldPlaceDirs = 
                this.dataOperator.findWebDirectoriesNotEmpty(oldPlace, userId);
        PersistableWebDirectory movedDir = 
                this.dataOperator.findDirectoryFromDirectoriesNotNull(oldPlaceDirs, dirName);
        List<PersistableWebDirectory> newPlaceDirs = 
                this.dataOperator.findWebDirectories(newPlace, userId);
        dirName = this.nameIncrementor.incrementName(newPlaceDirs, movedDir.getName());
        movedDir.setName(dirName);
        this.orderer.reorderToExtractWebItemLater(oldPlaceDirs, movedDir.getOrder());
        movedDir.setPlace(newPlace.name());
        movedDir.setOrder(newPlaceDirs.size());
        List<PersistableWebDirectory> savedDirs = 
                this.dataOperator.saveModifiedDirectories(oldPlaceDirs);
        PersistableWebDirectory savedDir = 
                this.dataOperator.findDirectoryFromDirectoriesNotNull(savedDirs, dirName);
        return ( savedDir.getPlace().equals(newPlace.name()) );
    }

    @Override
    public boolean moveUserWebPageIntoDirectory(
            int userId, WebPlacement place, String oldDirName, String newDirName, String pageName) {
        return this.movePageToDirAndPlace(oldDirName, place, place, userId, pageName, newDirName);
    }

    private boolean movePageToDirAndPlace(
            String oldDirName, 
            WebPlacement oldPlace, 
            WebPlacement newPlace, 
            int userId, 
            String pageName, 
            String newDirName) {
        this.dataOperator.checkUser(userId);
        PersistableWebDirectory oldDir =
                this.dataOperator.findWebDirectoryNotNullNotEmpty(oldDirName, oldPlace, userId);
        PersistableWebPage movedPage =
                this.dataOperator.findWebPageInDirectoryNotNull(oldDir.getPages(), pageName);
        PersistableWebDirectory newDir =
                this.dataOperator.findWebDirectoryNotNull(newDirName, newPlace, userId);
        pageName = this.nameIncrementor.incrementName(newDir.getPages(), pageName);
        this.orderer.reorderToExtractWebItemLater(oldDir.getPages(), movedPage.getOrder());
        movedPage.setName(pageName);
        movedPage.setOrder(newDir.getPages().size());
        movedPage.setDir(newDir);
        newDir.getPages().add(movedPage);
        List<PersistableWebPage> savedPages = new ArrayList<>();
        savedPages.addAll(oldDir.getPages());
        savedPages.addAll(newDir.getPages());
        savedPages = this.dataOperator.saveModifiedPages(savedPages);
        PersistableWebPage savedPage =
        this.dataOperator.findWebPageInDirectoryNotNull(savedPages, pageName);
        return ( savedPage.getDir().getName().equals(newDirName) );
    }
    
    @Override
    public boolean moveUserWebPageIntoDirectoryAndOrder(
            int userId, 
            WebPlacement place, 
            String oldDirName, 
            String newDirName, 
            String pageName, 
            int movedPageNewOrder) {
        this.dataOperator.checkUser(userId);
        PersistableWebDirectory oldDir = 
                this.dataOperator.findWebDirectoryNotNullNotEmpty(oldDirName, place, userId);        
        PersistableWebPage movedPage = 
                this.dataOperator.findWebPageInDirectoryNotNull(oldDir.getPages(), pageName);
        PersistableWebDirectory newDir = 
                this.dataOperator.findWebDirectoryNotNull(newDirName, place, userId);
        pageName = this.nameIncrementor.incrementName(newDir.getPages(), pageName);
        this.orderer.reorderToExtractWebItemLater(oldDir.getPages(), movedPage.getOrder());
        movedPage.setName(pageName);
        movedPage.setOrder(newDir.getPages().size());
        movedPage.setDir(newDir);
        this.orderer.reorderToInsertWebItemLater(newDir.getPages(), movedPage, movedPageNewOrder);
        newDir.getPages().add(movedPage);
        
        List<PersistableWebPage> savedPages = new ArrayList<>();
        savedPages.addAll(oldDir.getPages());
        savedPages.addAll(newDir.getPages());
        savedPages = this.dataOperator.saveModifiedPages(savedPages);
        PersistableWebPage savedPage = 
                this.dataOperator.findWebPageInDirectoryNotNull(savedPages, pageName);
        return ( 
                savedPage.getDir().getName().equals(newDirName) && 
                savedPage.getOrder() == movedPageNewOrder );
    }

    @Override
    public boolean moveUserWebPageIntoDirectoryAndPlace(
            int userId, 
            WebPlacement oldPlace, 
            WebPlacement newPlace, 
            String oldDirName, 
            String newDirName, 
            String pageName) {
        return this.movePageToDirAndPlace(
                oldDirName, oldPlace, newPlace, userId, pageName, newDirName);
    }

    @Override
    public boolean deleteUserWebDirectory(int userId, WebPlacement place, String dirName) {
        this.dataOperator.checkUser(userId);
        List<PersistableWebDirectory> dirs = 
                this.dataOperator.findWebDirectoriesNotEmpty(place, userId);
        PersistableWebDirectory deletedDir = 
                this.dataOperator.findDirectoryFromDirectoriesNotNull(dirs, dirName);
        this.orderer.reorderToExtractWebItemLater(dirs, deletedDir.getOrder());
        this.dataOperator.saveModifiedDirectories(dirs);
        return this.dataOperator.deleteDirectory(deletedDir);
    }

    @Override
    public boolean deleteUserWebPage(
            int userId, WebPlacement place, String dirName, String pageName) {
        this.dataOperator.checkUser(userId);
        PersistableWebDirectory dir = 
                this.dataOperator.findWebDirectoryNotNullNotEmpty(dirName, place, userId);
        PersistableWebPage deletedPage = 
                this.dataOperator.findWebPageInDirectoryNotNull(dir.getPages(), pageName);
        this.orderer.reorderToExtractWebItemLater(dir.getPages(), deletedPage.getOrder());
        this.dataOperator.saveModifiedPages(dir.getPages());
        return this.dataOperator.deletePage(deletedPage);
    }

    @Override
    public boolean createUserWebPage(
            int userId, WebPlacement place, String dirName, String pageName, String pageUrl) {
        this.dataOperator.checkUser(userId);
        PersistableWebDirectory dir = 
                this.dataOperator.findWebDirectoryNotNull(dirName, place, userId);
        PersistableWebPage newPage = new PersistableWebPage();
        pageName = this.nameIncrementor.incrementName(dir.getPages(), pageName);
        newPage.setName(pageName);
        newPage.setUrl(pageUrl);
        newPage.setDir(dir);
        newPage.setOrder(dir.getPages().size());
        dir.getPages().add(newPage);
        PersistableWebDirectory savedDir = this.dataOperator.saveModifiedDirectory(dir);
        PersistableWebPage savedPage = 
                this.dataOperator.findWebPageInDirectoryNotNull(savedDir.getPages(), pageName);
        return ( 
                savedPage.getName().equals(pageName) && 
                savedPage.getDir().getName().equals(dirName) );
    }

    @Override
    public boolean createUserWebDirectory(int userId, WebPlacement place, String dirName) {
        PersistableUser user = this.dataOperator.findUserNotNull(userId);
        List<PersistableWebDirectory> dirs = this.dataOperator.findWebDirectories(place, userId);
        dirName = this.nameIncrementor.incrementName(dirs, dirName);
        PersistableWebDirectory newDirectory = new PersistableWebDirectory();
        newDirectory.setName(dirName);
        newDirectory.setOrder(dirs.size());
        newDirectory.setUser(user);
        newDirectory.setPlace(place.name());
        newDirectory.setPages(emptyList());
        PersistableWebDirectory savedDir = this.dataOperator.saveModifiedDirectory(newDirectory);
        return ( savedDir.getId() != 0 );
    }

    @Override
    public List<PersistableWebDirectory> getUserPanelWebDirectories(int userId) {
        this.dataOperator.checkUser(userId);
        return this.dataOperator.findWebDirectories(PANEL, userId);
    }

    @Override
    public List<PersistableWebDirectory> getUserBookmarksWebDirectories(int userId) {
        this.dataOperator.checkUser(userId);
        return this.dataOperator.findWebDirectories(BOOKMARKS, userId);
    }

    @Override
    public PersistableWebDirectory getUserWebDirectory(
            int userId, WebPlacement place, String dirName) {
        this.dataOperator.checkUser(userId);
        return this.dataOperator.findWebDirectoryNotNull(dirName, place, userId);
    }

    @Override
    public List<PersistableWebDirectory> getAllUserWebDirectories(int userId) {
        this.dataOperator.checkUser(userId);
        return this.dataOperator.findAllUserWebDirectories(userId);
    }
}
