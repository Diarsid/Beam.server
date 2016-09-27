/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.services.webobjects;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import springtests.config.AppTestConfig;

import diarsid.beam.server.data.daos.springdata.repositories.jpa.RepositoryUsers;
import diarsid.beam.server.data.daos.springdata.repositories.jpa.RepositoryWebDirectories;
import diarsid.beam.server.domain.entities.WebPlacement;
import diarsid.beam.server.domain.entities.jpa.PersistableUser;
import diarsid.beam.server.domain.entities.jpa.PersistableWebDirectory;
import diarsid.beam.server.domain.services.webobjects.UserWebObjectsDataOperator;
import diarsid.beam.server.domain.services.webobjects.UserWebObjectsService;

import static org.junit.Assert.assertEquals;

import static diarsid.beam.server.domain.entities.WebPlacement.BOOKMARKS;
import static diarsid.beam.server.domain.entities.WebPlacement.WEBPANEL;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Diarsid
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfig.class})
@Transactional()
public class UserWebObjectsServiceWorkerTestExp {
    
    public final static String DIR_NAME_TEMPLATE;
    static {
        DIR_NAME_TEMPLATE = "_dir_";
    }
    
    @Autowired
    private UserWebObjectsService service;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private RepositoryUsers usersRepo;
    
    @Autowired
    private RepositoryWebDirectories dirsRepo;
    
    @Autowired
    private UserWebObjectsDataOperator dataOperator;
    
    public UserWebObjectsServiceWorkerTestExp() {
    }
    
    private static String user1_name = "user1";
    private static int user1_id;
    
    private static String user2_name = "user2";
    private static int user2_id;
    
    static String dirNameOf(PersistableUser owner, WebPlacement place, int nameIncrementor) {
        return owner.getName() + DIR_NAME_TEMPLATE + place.name() + "_" + nameIncrementor;
    }
    
    public static List<PersistableWebDirectory> newFakeDirs(
            PersistableUser owner, WebPlacement place, int nameIncrementor, int qty) {
        List<PersistableWebDirectory> newDirs = new ArrayList<>();        
        PersistableWebDirectory newDir = null;
        for (int i = 0; i < qty; i++) {
            newDir = new PersistableWebDirectory();
            newDir.setUser(owner);
            newDir.setName(dirNameOf(owner, place, nameIncrementor));
            newDir.setOrder(i);
            newDir.setPlace(place);
            
            nameIncrementor++;         
            newDirs.add(newDir);
        }
        return newDirs;
    }
    
    @Before
    public void setUpTest() {
        PersistableUser user1 = new PersistableUser();
        user1.setName(user1_name);
        user1.setSurname(user1_name + "_sur");
        user1.setNickname(user1_name + "_nick");
        user1.setPassword(user1_name + "_pass");
        user1.setEmail(user1_name + "_@mail");
        user1.setRole("user");
        
        user1 = usersRepo.saveAndFlush(user1);
        user1_id = user1.getId();
        
        PersistableUser user2 = new PersistableUser();
        user2.setName(user2_name);
        user2.setSurname(user2_name + "_sur");
        user2.setNickname(user2_name + "_nick");
        user2.setPassword(user2_name + "_pass");
        user2.setEmail(user2_name + "_@mail");
        user2.setRole("user");
        
        user2 = usersRepo.saveAndFlush(user2);
        user2_id = user2.getId();
        
        List<PersistableWebDirectory> user1panelDirs = newFakeDirs(user1, WEBPANEL, 1, 6);
        List<PersistableWebDirectory> user1bookmDirs = newFakeDirs(user1, BOOKMARKS, 1, 6);
        List<PersistableWebDirectory> user2panelDirs = newFakeDirs(user2, WEBPANEL, 1, 6);
        List<PersistableWebDirectory> user2bookmDirs = newFakeDirs(user2, BOOKMARKS, 1, 6);

        List<PersistableWebDirectory> all = new ArrayList<>();
        all.addAll(user1panelDirs);
        all.addAll(user1bookmDirs);
        all.addAll(user2panelDirs);
        all.addAll(user2bookmDirs);
        
        for (PersistableWebDirectory dir : all) {
            dirsRepo.save(dir);
        }
        dirsRepo.flush();
    }
    
    @Test  
    public void testSetUp() {
        PersistableUser user1 = usersRepo.findOne(user1_id);
        assertEquals(user1_name, user1.getName());
        assertEquals(user2_name, usersRepo.findOne(user2_id).getName());
        PersistableWebDirectory dir = service.getUserWebDirectory(user1_id, WEBPANEL.name(), dirNameOf(user1, WEBPANEL, 2));
        assertEquals(dirNameOf(user1, WEBPANEL, 2), dir.getName());
        
        assertEquals(0, dir.getPages().size());
    } 
}
