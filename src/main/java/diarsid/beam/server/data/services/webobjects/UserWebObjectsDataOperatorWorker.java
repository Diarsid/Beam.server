/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.services.webobjects;

import java.util.List;

import org.springframework.stereotype.Component;

import diarsid.beam.server.data.daos.springdata.repositories.jpa.RepositoryUsers;
import diarsid.beam.server.data.daos.springdata.repositories.jpa.RepositoryWebDirectories;
import diarsid.beam.server.data.daos.springdata.repositories.jpa.RepositoryWebPages;
import diarsid.beam.server.data.entities.WebPlacement;
import diarsid.beam.server.data.entities.jpa.PersistableUser;
import diarsid.beam.server.data.entities.jpa.PersistableWebDirectory;
import diarsid.beam.server.data.entities.jpa.PersistableWebPage;
import diarsid.beam.server.data.services.BadDataRequestArgumentsException;

/**
 *
 * @author Diarsid
 */

@Component
public class UserWebObjectsDataOperatorWorker implements UserWebObjectsDataOperator {
    
    private final RepositoryUsers users;
    private final RepositoryWebDirectories directories;
    private final RepositoryWebPages webPages;
    
    public UserWebObjectsDataOperatorWorker(
            RepositoryUsers usersRepo,
            RepositoryWebDirectories dirsRepo,
            RepositoryWebPages webPagesRepo) {
        this.users = usersRepo;
        this.directories = dirsRepo;
        this.webPages = webPagesRepo;
    }
    
    @Override
    public PersistableUser findUserNotNull(int userId) {
        PersistableUser user = this.users.findOne(userId);
        if ( user == null ) {
            throw new BadDataRequestArgumentsException(
                    "User with id " + userId + " does not exist.");
        }
        return user;
    }
    
    @Override
    public void checkUser(int userId) {
        if ( ! this.users.exists(userId) ) {
            throw new BadDataRequestArgumentsException(
                    "User with id " + userId + " does not exist.");
        }
    }
    
    @Override
    public int countWebDirectoriesInPlace(WebPlacement place, int userId) {
        PersistableUser user = this.users.findOne(userId);
        return this.directories.countByUserAndPlace(place.name(), user.getId());
    }
    
    @Override
    public List<PersistableWebDirectory> findAllUserWebDirectories(int userId) {
        return this.directories.getByUserIdOrderByOrder(userId);
    }
    
    @Override
    public PersistableWebPage findWebPageInDirectoryNotNull(
            List<PersistableWebPage> pages, String pageName) {
        PersistableWebPage movedPage = pages.stream()
                .filter(page -> page.getName().equals(pageName))
                .findFirst()
                .orElseThrow(() -> new BadDataRequestArgumentsException(
                            "Cannot find WebPage " + pageName + " in specified WebDirectory."));
        return movedPage;
    }

    @Override
    public PersistableWebDirectory findWebDirectoryNotNull(
            String dirName, WebPlacement place, int userId) {
        PersistableWebDirectory dir =
                this.directories.findByNameAndPlaceAndUserId(dirName, place.name(), userId);
        if ( dir == null ) {
            throw new BadDataRequestArgumentsException(
                    "Cannot find WebDirectory " + dirName + " in " 
                            + place.name() + " for this user.");
        } else {
            return dir;
        }
    } 
    
    @Override
    public PersistableWebDirectory findWebDirectoryNotNullNotEmpty(
            String dirName, WebPlacement place, int userId) {
        PersistableWebDirectory dir = this.findWebDirectoryNotNull(dirName, place, userId);
        if ( dir.getPages().isEmpty() ) {
            throw new BadDataRequestArgumentsException("WebDirectory " + dirName + " in " 
                            + place.name() + " is empty.");
        } else {
            return dir;
        }
    }  
    
    @Override
    public List<PersistableWebDirectory> findWebDirectoriesNotEmpty(WebPlacement place, int userId)  {
        List<PersistableWebDirectory> dirs =
                this.directories.getByPlaceAndUserIdOrderByOrder(place.name(), userId);
        if ( dirs.isEmpty() ) {
            throw new BadDataRequestArgumentsException(
                    "This user does not have any WebDirectories in " + place.name() + ".");
        }
        return dirs;
    }
    
    @Override
    public List<PersistableWebDirectory> findWebDirectories(WebPlacement place, int userId)  {
        return this.directories.getByPlaceAndUserIdOrderByOrder(place.name(), userId);
    }

    @Override
    public PersistableWebDirectory findDirectoryFromDirectoriesNotNull(
            List<PersistableWebDirectory> dirs, String dirName) {
        return dirs.stream()
                .filter(dir -> dir.getName().equals(dirName))
                .findFirst()
                .orElseThrow(() -> new BadDataRequestArgumentsException(
                        "Cannot find WebDirectory " + dirName + " in this place."));
    }
    
    @Override
    public List<PersistableWebDirectory> saveModifiedDirectories(List<PersistableWebDirectory> dirs) {
        List<PersistableWebDirectory> saved = this.directories.save(dirs);
        this.directories.flush();
        return saved;
    }
    
    @Override
    public PersistableWebDirectory saveModifiedDirectory(PersistableWebDirectory dir) {
        return this.directories.saveAndFlush(dir);
    }
           
    @Override
    public boolean deleteDirectory(PersistableWebDirectory dir) {
        this.directories.delete(dir);
        this.directories.flush();
        return ( ! this.directories.exists(dir.getId()));
    }
}
