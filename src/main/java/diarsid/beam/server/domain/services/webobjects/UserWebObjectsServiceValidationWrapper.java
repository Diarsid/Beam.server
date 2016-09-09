/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.domain.services.webobjects;

import java.util.List;

import org.springframework.stereotype.Component;

import diarsid.beam.server.domain.entities.WebPlacement;
import diarsid.beam.server.domain.entities.jpa.PersistableWebDirectory;
import diarsid.beam.server.domain.services.validation.WebObjectsValidationService;

@Component
public class UserWebObjectsServiceValidationWrapper implements UserWebObjectsService {
    
    private final UserWebObjectsService unvalidatedService;
    private final WebObjectsValidationService validation;
    
    public UserWebObjectsServiceValidationWrapper(
            UserWebObjectsService service, WebObjectsValidationService validator) {
        this.unvalidatedService = service;
        this.validation = validator;
    }

    @Override
    public boolean reorderUserWebDirectory(
            int userId, WebPlacement place, String dirName, int newOrder) {
        this.validation.validateWebObjectNames(dirName);
        return this.unvalidatedService.reorderUserWebDirectory(userId, place, dirName, newOrder);
    }

    @Override
    public boolean reorderUserWebPageOrder(
            int userId, WebPlacement place, String dirName, String pageName, int newOrder) {
        this.validation.validateWebObjectNames(dirName, pageName);
        return this.unvalidatedService.reorderUserWebPageOrder(
                userId, place, dirName, pageName, newOrder);
    }

    @Override
    public boolean renameUserWebPage(
            int userId, WebPlacement place, String dirName, String oldPageName, String newPageName) {
        this.validation.validateWebObjectNames(dirName, oldPageName, newPageName);
        return this.unvalidatedService.renameUserWebPage(
                userId, place, dirName, oldPageName, newPageName);
    }

    @Override
    public boolean redirectUserWebPageUrl(
            int userId, WebPlacement place, String dirName, String pageName, String newUrl) {
        this.validation.validateWebObjectNames(dirName, pageName);
        this.validation.validateUrl(newUrl);
        return this.unvalidatedService.redirectUserWebPageUrl(
                userId, place, dirName, pageName, newUrl);
    }

    @Override
    public boolean renameUserWebDirectory(
            int userId, WebPlacement place, String oldDirName, String newDirName) {
        this.validation.validateWebObjectNames(newDirName, oldDirName);
        return this.unvalidatedService.renameUserWebDirectory(
                userId, place, oldDirName, newDirName);
    }

    @Override
    public boolean moveUserWebDirectoryIntoPlace(
            int userId, WebPlacement oldPlace, WebPlacement newPlace, String dirName) {
        this.validation.validateWebObjectNames(dirName);
        if ( oldPlace.equals(newPlace) ) {
            return true;
        } else {
            return this.unvalidatedService.moveUserWebDirectoryIntoPlace(
                    userId, oldPlace, newPlace, dirName);
        }
    }

    @Override
    public boolean moveUserWebPageIntoDirectory(
            int userId, WebPlacement place, String oldDirName, String newDirName, String pageName) {
        this.validation.validateWebObjectNames(oldDirName, newDirName, pageName);
        return this.unvalidatedService.moveUserWebPageIntoDirectory(
                userId, place, oldDirName, newDirName, pageName);
    }

    @Override
    public boolean moveUserWebPageIntoDirectoryAndPlace(
            int userId, 
            WebPlacement oldPlace, 
            WebPlacement newPlace, 
            String oldDirName, 
            String newDirName, 
            String pageName) {
        this.validation.validateWebObjectNames(oldDirName, newDirName, pageName);
        return this.unvalidatedService.moveUserWebPageIntoDirectoryAndPlace(
                userId, oldPlace, newPlace, oldDirName, newDirName, pageName);
    }
    
    @Override
    public boolean moveUserWebPageIntoDirectoryAndOrder(
            int userId, 
            WebPlacement place, 
            String oldDirName, 
            String newDirName, 
            String pageName, 
            int movedPageNewOrder) {
        this.validation.validateWebObjectNames(oldDirName, newDirName, pageName);
        return this.unvalidatedService.moveUserWebPageIntoDirectoryAndOrder(
                userId, place, oldDirName, newDirName, pageName, movedPageNewOrder);
    }

    @Override
    public boolean deleteUserWebDirectory(int userId, WebPlacement place, String dirName) {
        this.validation.validateWebObjectNames(dirName);
        return this.unvalidatedService.deleteUserWebDirectory(userId, place, dirName);
    }

    @Override
    public boolean deleteUserWebPage(
            int userId, WebPlacement place, String dirName, String pageName) {
        this.validation.validateWebObjectNames(dirName, pageName);
        return this.unvalidatedService.deleteUserWebPage(userId, place, dirName, pageName);
    }

    @Override
    public boolean createUserWebPage(
            int userId, WebPlacement place, String dirName, String pageName, String pageUrl) {
        this.validation.validateWebObjectNames(dirName, pageName);
        this.validation.validateUrl(pageUrl);
        return this.unvalidatedService.createUserWebPage(
                userId, place, dirName, pageName, pageUrl);
    }

    @Override
    public boolean createUserWebDirectory(int userId, WebPlacement place, String dirName) {
        this.validation.validateWebObjectNames(dirName);
        return this.unvalidatedService.createUserWebDirectory(userId, place, dirName);
    }

    @Override
    public List<PersistableWebDirectory> getUserWebDirectoriesInPlace(int userId, WebPlacement place) {
        return this.unvalidatedService.getUserWebDirectoriesInPlace(userId, place);
    }

    @Override
    public PersistableWebDirectory getUserWebDirectory(
            int userId, WebPlacement place, String dirName) {
        this.validation.validateWebObjectNames(dirName);
        return this.unvalidatedService.getUserWebDirectory(userId, place, dirName);
    }

    @Override
    public List<PersistableWebDirectory> getUserAllWebDirectories(int userId) {
        return this.unvalidatedService.getUserAllWebDirectories(userId);
    }
}
