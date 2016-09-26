/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.services.webobjects;

import java.util.List;

import org.junit.Ignore;
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
import diarsid.beam.server.domain.entities.jpa.PersistableUser;
import diarsid.beam.server.domain.entities.jpa.PersistableWebDirectory;
import diarsid.beam.server.domain.entities.jpa.PersistableWebPage;
import diarsid.beam.server.domain.services.exceptions.BadDataRequestArgumentsException;
import diarsid.beam.server.domain.services.exceptions.WebObjectNameInvalidException;
import diarsid.beam.server.domain.services.exceptions.WebObjectUrlInvalidException;
import diarsid.beam.server.domain.services.webobjects.UserWebObjectsDataOperator;
import diarsid.beam.server.domain.services.webobjects.UserWebObjectsService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;

import static diarsid.beam.server.domain.entities.WebPlacement.BOOKMARKS;
import static diarsid.beam.server.domain.entities.WebPlacement.WEBPANEL;

import static util.FakeUserProducer.newFakeUser;
import static util.FakeWebDirsProducer.newFakeDir;
import static util.FakeWebDirsProducer.newFakeDirs;
import static util.FakeWebPagesProducer.newFakePages;

/**
 *
 * @author Diarsid
 */

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfig.class})
@Transactional
public class UserWebObjectsServiceWorkerTest {
    
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
    

    public UserWebObjectsServiceWorkerTest() {
    }

    /**
     * Test of reorderUserWebDirectory method, of class UserWebContentServiceWorker.
     */
    @Test
    public void testReorderUserWebDirectory_straightOrder_correctBoundary() {
        int dirsQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        List<PersistableWebDirectory> savedDirs = 
                dirsRepo.save(newFakeDirs(savedUser, WEBPANEL, 6, dirsQty));
        dirsRepo.flush();
        
        String movedDirName = savedDirs.get(3).getName();
        /*
         *  0 1 2 3 4
         *    <---e
         */
        int movedDirNewOrder = 1;
        
        boolean reorder = service.reorderUserWebDirectory(savedUser.getId(), WEBPANEL.name(), movedDirName, movedDirNewOrder);
        assertTrue(reorder);
        
//        int expectedDirsQty = dirsQty;
//        int actualDirsQty = countRowsInTable(jdbcTemplate, "dirs");        
//        assertEquals(expectedDirsQty, actualDirsQty);
        
        List<PersistableWebDirectory> foundDirs = 
                dirsRepo.getByPlaceAndUserIdOrderByOrder(WEBPANEL.name(), savedUser.getId());
        for (int i = 0; i < foundDirs.size(); i++) {
            assertEquals(i, foundDirs.get(i).getOrder());            
        }
        assertEquals(movedDirName, foundDirs.get(movedDirNewOrder).getName());        
    }
    
    @Test
    public void testReorderUserWebDirectory_reverseOrder_correctBoundary() {
        int dirsQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        List<PersistableWebDirectory> savedDirs = 
                dirsRepo.save(newFakeDirs(savedUser, WEBPANEL, 6, dirsQty));
        dirsRepo.flush();
        
        String movedDirName = savedDirs.get(1).getName();
        /*
         *  0 1 2 3 4
         *    e--->
         */
        int movedDirNewOrder = 3;
        
        boolean reorder = service.reorderUserWebDirectory(savedUser.getId(), WEBPANEL.name(), movedDirName, movedDirNewOrder);
        assertTrue(reorder);
        
//        int expectedDirsQty = dirsQty;
//        int actualDirsQty = countRowsInTable(jdbcTemplate, "dirs");        
//        assertEquals(expectedDirsQty, actualDirsQty);
        
        List<PersistableWebDirectory> foundDirs = 
                dirsRepo.getByPlaceAndUserIdOrderByOrder(WEBPANEL.name(), savedUser.getId());
        for (int i = 0; i < foundDirs.size(); i++) {
            assertEquals(i, foundDirs.get(i).getOrder());            
        }
        assertEquals(movedDirName, foundDirs.get(movedDirNewOrder).getName());  
    }
    
    @Test
    public void testReorderUserWebDirectory_straightOrder_wrongBoundary() {
        int dirsQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        List<PersistableWebDirectory> savedDirs = 
                dirsRepo.save(newFakeDirs(savedUser, WEBPANEL, 6, dirsQty));
        dirsRepo.flush();
        
        String movedDirName = savedDirs.get(3).getName();
        /*
         *  0 1 2 3 4
         *    <---e
         */
        int movedDirNewOrder = -3;
        
        boolean reorder = service.reorderUserWebDirectory(savedUser.getId(), WEBPANEL.name(), movedDirName, movedDirNewOrder);
        assertTrue(reorder);
        
//        int expectedDirsQty = dirsQty;
//        int actualDirsQty = countRowsInTable(jdbcTemplate, "dirs");        
//        assertEquals(expectedDirsQty, actualDirsQty);
        
        List<PersistableWebDirectory> foundDirs = 
                dirsRepo.getByPlaceAndUserIdOrderByOrder(WEBPANEL.name(), savedUser.getId());
        for (int i = 0; i < foundDirs.size(); i++) {
            assertEquals(i, foundDirs.get(i).getOrder());            
        }
        assertEquals(movedDirName, foundDirs.get(0).getName());        
    }
    
    @Test
    public void testReorderUserWebDirectory_reverseOrder_wrongBoundary() {
        int dirsQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        List<PersistableWebDirectory> savedDirs = 
                dirsRepo.save(newFakeDirs(savedUser, WEBPANEL, 6, dirsQty));
        dirsRepo.flush();
        
        String movedDirName = savedDirs.get(1).getName();
        /*
         *  0 1 2 3 4
         *    e--->
         */
        int movedDirNewOrder = 100;
        
        boolean reorder = service.reorderUserWebDirectory(savedUser.getId(), WEBPANEL.name(), movedDirName, movedDirNewOrder);
        assertTrue(reorder);
        
//        int expectedDirsQty = dirsQty;
//        int actualDirsQty = countRowsInTable(jdbcTemplate, "dirs");        
//        assertEquals(expectedDirsQty, actualDirsQty);
        
        List<PersistableWebDirectory> foundDirs = 
                dirsRepo.getByPlaceAndUserIdOrderByOrder(WEBPANEL.name(), savedUser.getId());
        for (int i = 0; i < foundDirs.size(); i++) {
            assertEquals(i, foundDirs.get(i).getOrder());            
        }
        assertEquals(movedDirName, foundDirs.get(4).getName());  
    }

    /**
     * Test of reorderUserWebPageOrder method, of class UserWebContentServiceWorker.
     */
    @Test
    public void testReorderUserWebPageOrder_straightOrder() {
        int pagesQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedDir = newFakeDir(savedUser, WEBPANEL, 3);
        List<PersistableWebPage> savedPages = newFakePages(savedDir, 5, pagesQty);
        savedDir.setPages(savedPages);
        savedDir = dirsRepo.save(savedDir);
        dirsRepo.flush();
        
        int movedPageOldOrder = 1;
        int movedPageNewOrder = 3;
        String movedPageName = savedDir.getPages().get(movedPageOldOrder).getName();
        
        boolean reorder = service.reorderUserWebPageOrder(savedUser.getId(), WEBPANEL.name(), savedDir.getName(), movedPageName, movedPageNewOrder);
        
        assertTrue(reorder);
        
        PersistableWebDirectory foundDir = service.getUserWebDirectory(savedUser.getId(), WEBPANEL.name(), savedDir.getName());
        for (int i = 0; i < foundDir.getPages().size(); i++) {
            assertEquals(i, foundDir.getPages().get(i).getOrder());            
        }
        assertEquals(foundDir.getPages().get(movedPageNewOrder).getName(), movedPageName);
    }
    
    @Test
    public void testReorderUserWebPageOrder_reverseOrder() {
        int pagesQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedDir = newFakeDir(savedUser, WEBPANEL, 3);
        List<PersistableWebPage> savedPages = newFakePages(savedDir, 5, pagesQty);
        savedDir.setPages(savedPages);
        savedDir = dirsRepo.save(savedDir);
        dirsRepo.flush();
        
        int movedPageOldOrder = 3;
        int movedPageNewOrder = 1;
        String movedPageName = savedDir.getPages().get(movedPageOldOrder).getName();
        
        boolean reorder = service.reorderUserWebPageOrder(savedUser.getId(), WEBPANEL.name(), savedDir.getName(), movedPageName, movedPageNewOrder);
        
        assertTrue(reorder);
        
        PersistableWebDirectory foundDir = service.getUserWebDirectory(savedUser.getId(), WEBPANEL.name(), savedDir.getName());
        for (int i = 0; i < foundDir.getPages().size(); i++) {
            assertEquals(i, foundDir.getPages().get(i).getOrder());            
        }
        assertEquals(foundDir.getPages().get(movedPageNewOrder).getName(), movedPageName);
    }
        
    @Test
    public void testReorderUserWebPageOrder_straightOrder_wrongBoundary() {
        int pagesQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedDir = newFakeDir(savedUser, WEBPANEL, 3);
        List<PersistableWebPage> savedPages = newFakePages(savedDir, 5, pagesQty);
        savedDir.setPages(savedPages);
        savedDir = dirsRepo.save(savedDir);
        dirsRepo.flush();
        
        int movedPageOldOrder = 1;
        int movedPageNewOrder_wrong = 1000;
        int movedPageNewOrder_exepcted = pagesQty - 1;
        String movedPageName = savedDir.getPages().get(movedPageOldOrder).getName();
        
        boolean reorder = service.reorderUserWebPageOrder(savedUser.getId(), WEBPANEL.name(), savedDir.getName(), movedPageName, movedPageNewOrder_wrong);
        
        assertTrue(reorder);
        
        PersistableWebDirectory foundDir = service.getUserWebDirectory(savedUser.getId(), WEBPANEL.name(), savedDir.getName());
        for (int i = 0; i < foundDir.getPages().size(); i++) {
            assertEquals(i, foundDir.getPages().get(i).getOrder());            
        }
        assertEquals(foundDir.getPages().get(movedPageNewOrder_exepcted).getName(), movedPageName);
    }
    
    @Test
    public void testReorderUserWebPageOrder_reverseOrder_wrongBoundary() {
        int pagesQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedDir = newFakeDir(savedUser, WEBPANEL, 3);
        List<PersistableWebPage> savedPages = newFakePages(savedDir, 5, pagesQty);
        savedDir.setPages(savedPages);
        savedDir = dirsRepo.save(savedDir);
        dirsRepo.flush();
        
        int movedPageOldOrder = 3;
        int movedPageNewOrder_wrong = -2;
        int movedPageNewOrder_exepcted = 0;
        String movedPageName = savedDir.getPages().get(movedPageOldOrder).getName();
        
        boolean reorder = service.reorderUserWebPageOrder(savedUser.getId(), WEBPANEL.name(), savedDir.getName(), movedPageName, movedPageNewOrder_wrong);
        
        assertTrue(reorder);
        
        PersistableWebDirectory foundDir = service.getUserWebDirectory(savedUser.getId(), WEBPANEL.name(), savedDir.getName());
        for (int i = 0; i < foundDir.getPages().size(); i++) {
            assertEquals(i, foundDir.getPages().get(i).getOrder());            
        }
        assertEquals(foundDir.getPages().get(movedPageNewOrder_exepcted).getName(), movedPageName);
    }

    /**
     * Test of renameUserWebPage method, of class UserWebContentServiceWorker.
     */
    @Test
    public void testRenameUserWebPage() {
        int pagesQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedDir = newFakeDir(savedUser, WEBPANEL, 3);
        List<PersistableWebPage> savedPages = newFakePages(savedDir, 5, pagesQty);
        savedDir.setPages(savedPages);
        savedDir = dirsRepo.save(savedDir);
        dirsRepo.flush();
        
        int renamedPageIndex = 2;
        String oldPageName = savedPages.get(renamedPageIndex).getName();
        String newPageName = "new_name";
        
        boolean rename = service.renameUserWebPage(savedUser.getId(), WEBPANEL.name(), savedDir.getName(), oldPageName, newPageName);
        assertTrue(rename);
        
        PersistableWebDirectory foundDir = service.getUserWebDirectory(savedUser.getId(), WEBPANEL.name(), savedDir.getName());
        assertEquals(newPageName, foundDir.getPages().get(renamedPageIndex).getName());
    }
    
    @Test(expected = WebObjectNameInvalidException.class)
    public void testRenameUserWebPage_exceptionThrown() {
        int pagesQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedDir = newFakeDir(savedUser, WEBPANEL, 3);
        List<PersistableWebPage> savedPages = newFakePages(savedDir, 5, pagesQty);
        savedDir.setPages(savedPages);
        savedDir = dirsRepo.save(savedDir);
        dirsRepo.flush();
        
        int renamedPageIndex = 2;
        String oldPageName = savedPages.get(renamedPageIndex).getName();
        String newPageName = "new_$ invalid *name";
        
        boolean rename = service.renameUserWebPage(savedUser.getId(), WEBPANEL.name(), savedDir.getName(), oldPageName, newPageName);
        fail();
    }
    
    @Test
    public void testRenameUserWebPage_nameIncremented() {
        int pagesQty = 3;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedDir = newFakeDir(savedUser, WEBPANEL, 3);
        List<PersistableWebPage> savedPages = newFakePages(savedDir, 5, pagesQty);
        savedPages.get(0).setName("first");
        savedPages.get(1).setName("second");
        savedPages.get(2).setName("third");
        savedDir.setPages(savedPages);
        savedDir = dirsRepo.save(savedDir);
        dirsRepo.flush();
        
        int renamedPageIndex = 1;
        String oldPageName = savedPages.get(renamedPageIndex).getName();
        String newPageName = "third";
        String expectedIncrementedName = "third (2)";
        
        boolean rename = service.renameUserWebPage(savedUser.getId(), WEBPANEL.name(), savedDir.getName(), oldPageName, newPageName);
        assertTrue(rename);
        
        PersistableWebDirectory foundDir = service.getUserWebDirectory(savedUser.getId(), WEBPANEL.name(), savedDir.getName());
        assertEquals(expectedIncrementedName, foundDir.getPages().get(renamedPageIndex).getName());
    }

    /**
     * Test of redirectUserWebPageUrl method, of class UserWebContentServiceWorker.
     */
    @Test
    public void testRedirectUserWebPageUrl() {
        int pagesQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedDir = newFakeDir(savedUser, WEBPANEL, 3);
        List<PersistableWebPage> savedPages = newFakePages(savedDir, 5, pagesQty);
        savedDir.setPages(savedPages);
        savedDir = dirsRepo.save(savedDir);
        dirsRepo.flush();
        
        int redirectedPageIndex = 2;
        String redirectedPageName = savedPages.get(redirectedPageIndex).getName();
        String newUrl = "http://new.valid.url.com";
        
        boolean redirected = service.redirectUserWebPageUrl(
                savedUser.getId(), WEBPANEL.name(), savedDir.getName(), redirectedPageName, newUrl);
        assertTrue(redirected);
        
        PersistableWebDirectory foundDir = service.getUserWebDirectory(
                savedUser.getId(), WEBPANEL.name(), savedDir.getName());
        assertEquals(newUrl, foundDir.getPages().get(redirectedPageIndex).getUrl());
    }
    
    @Test(expected = WebObjectUrlInvalidException.class)
    public void testRedirectUserWebPageUrl_exceptionThrown() {
        int pagesQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedDir = newFakeDir(savedUser, WEBPANEL, 3);
        List<PersistableWebPage> savedPages = newFakePages(savedDir, 5, pagesQty);
        savedDir.setPages(savedPages);
        savedDir = dirsRepo.save(savedDir);
        dirsRepo.flush();
        
        int redirectedPageIndex = 2;
        String redirectedPageName = savedPages.get(redirectedPageIndex).getName();
        String newUrl = "http://new.*in$valid.url.com";
        
        boolean redirected = service.redirectUserWebPageUrl(
                savedUser.getId(), WEBPANEL.name(), savedDir.getName(), redirectedPageName, newUrl);
        fail();
    }

    /**
     * Test of renameUserWebDirectory method, of class UserWebContentServiceWorker.
     */
    @Test
    public void testRenameUserWebDirectory() {
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedDir = newFakeDir(savedUser, WEBPANEL, 3);
        savedDir = dirsRepo.saveAndFlush(savedDir);
        
        String oldDirName = savedDir.getName();
        String newDirName = "new_name";
        
        boolean renamed = service.renameUserWebDirectory(
                savedUser.getId(), WEBPANEL.name(), oldDirName, newDirName);
        assertTrue(renamed);
        
        PersistableWebDirectory foundDir = dirsRepo.findOne(savedDir.getId());
        assertEquals(newDirName, foundDir.getName());
    }
    
    @Test(expected = WebObjectNameInvalidException.class)
    public void testRenameUserWebDirectory_exceptionThrown() {
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedDir = newFakeDir(savedUser, WEBPANEL, 3);
        savedDir = dirsRepo.saveAndFlush(savedDir);
        
        String oldDirName = savedDir.getName();
        String newDirName = "new_# invalid % name";
        
        boolean renamed = service.renameUserWebDirectory(
                savedUser.getId(), WEBPANEL.name(), oldDirName, newDirName);
        fail();
    }
    
    @Test
    public void testRenameUserWebDirectory_incrementedName() {
        int dirsQty = 3;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        List<PersistableWebDirectory> savedDirs = newFakeDirs(savedUser, WEBPANEL, 6, dirsQty);
        savedDirs.get(0).setName("one");
        savedDirs.get(1).setName("two");
        savedDirs.get(2).setName("three");
        savedDirs = dirsRepo.save(savedDirs);
        dirsRepo.flush();
        
        int renamedDirIndex = 1;
        String oldDirName = savedDirs.get(renamedDirIndex).getName();
        String newDirName = "three";
        String expectedNewDirName = "three (2)";
        
        boolean renamed = service.renameUserWebDirectory(
                savedUser.getId(), WEBPANEL.name(), oldDirName, newDirName);
        assertTrue(renamed);
        
        PersistableWebDirectory foundDir = dirsRepo.findByNameAndPlaceAndUserId(
                expectedNewDirName, WEBPANEL.name(), savedUser.getId());
        assertEquals(expectedNewDirName, foundDir.getName());
    }

    /**
     * Test of moveUserWebDirectoryIntoPlace method, of class UserWebContentServiceWorker.
     */
    @Test
    public void testMoveUserWebDirectoryIntoPlace() {
        int panelDirsQty = 4;
        int bookmDirsQty = 6;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        List<PersistableWebDirectory> panelDirs = newFakeDirs(savedUser, WEBPANEL, 6, panelDirsQty);
        List<PersistableWebDirectory> bookmDirs = newFakeDirs(savedUser, BOOKMARKS, 23, bookmDirsQty);
        panelDirs = dirsRepo.save(panelDirs);
        bookmDirs = dirsRepo.save(bookmDirs);
        dirsRepo.flush();
        
        int movedFromBookmarksDirIndex = 3;
        String movedDirName = bookmDirs.get(movedFromBookmarksDirIndex).getName();
        
        boolean moved = service.moveUserWebDirectoryIntoPlace(
                savedUser.getId(), BOOKMARKS.name(), WEBPANEL.name(), movedDirName);
        assertTrue(moved);
        
//        int expectedPanelSize = panelDirsQty + 1;
//        int expectedBookmSize = bookmDirsQty - 1;
//        int actualPanelSize = countRowsInTableWhere(jdbcTemplate, "dirs", " dir_place IS 'WEBPANEL' ");
//        int actualBookmSize = countRowsInTableWhere(jdbcTemplate, "dirs", " dir_place IS 'BOOKMARKS' ");
//        assertEquals(expectedBookmSize, actualBookmSize);
//        assertEquals(expectedPanelSize, actualPanelSize);
        
        List<PersistableWebDirectory> foundPanel = 
                service.getUserWebDirectoriesInPlace(savedUser.getId(), WEBPANEL.name());
        List<PersistableWebDirectory> foundBookm = 
                service.getUserWebDirectoriesInPlace(savedUser.getId(), BOOKMARKS.name());
        
        for (int i = 0; i < foundPanel.size(); i++ ) {
            assertEquals(i, foundPanel.get(i).getOrder());
        }
        
        for (int i = 0; i < foundBookm.size(); i++ ) {
            assertEquals(i, foundBookm.get(i).getOrder());
        }
        
        PersistableWebDirectory movedDir = service.getUserWebDirectory(
                savedUser.getId(), WEBPANEL.name(), movedDirName);
        assertNotNull(movedDir);
        assertEquals(foundPanel.size() - 1, movedDir.getOrder());
        assertTrue(foundPanel.contains(movedDir));
    }

    /**
     * Test of moveUserWebPageIntoDirectory method, of class UserWebContentServiceWorker.
     */
    @Test
    public void testMoveUserWebPageIntoDirectory() {
        int panelDirsQty = 4;
        int pagesQtyInDirFrom = 6;
        int pagesQtyInDirTo = 4;
        int dirFromIndex = 1;
        int dirToIndex = 3;
        int movedPageInitialIndex = 3;
        
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        List<PersistableWebDirectory> dirs = newFakeDirs(savedUser, WEBPANEL, 6, panelDirsQty);
        
        PersistableWebDirectory dirFrom = dirs.get(dirFromIndex);
        PersistableWebDirectory dirTo = dirs.get(dirToIndex);
        
        List<PersistableWebPage> pagesInDirFrom = newFakePages(dirFrom, 1, pagesQtyInDirFrom);
        List<PersistableWebPage> pagesInDirTo = newFakePages(dirTo, 20, pagesQtyInDirTo);
        
        String movedPageName = pagesInDirFrom.get(movedPageInitialIndex).getName();
        
        dirFrom.setPages(pagesInDirFrom);
        dirTo.setPages(pagesInDirTo);
        
        dirs = dirsRepo.save(dirs);
        dirFrom = dirs.get(dirFromIndex);
        dirTo = dirs.get(dirToIndex);
        dirsRepo.flush();
        
        boolean moved = service.moveUserWebPageIntoDirectory(
                savedUser.getId(), WEBPANEL.name(), dirFrom.getName(), dirTo.getName(), movedPageName);
        
        assertTrue(moved);
        
//        int expectedDirToSize = pagesQtyInDirTo + 1;
//        int expectedDirFromSize = pagesQtyInDirFrom - 1;
//        int actualDirToSize = countRowsInTableWhere(jdbcTemplate, "pages", " dir_id IS " + dirTo.getId());
//        int actualDirFromSize = countRowsInTableWhere(jdbcTemplate, "pages", " dir_id IS " + dirFrom.getId());
//        assertEquals(expectedDirFromSize, actualDirFromSize);
//        assertEquals(expectedDirToSize, actualDirToSize);
        
        dirFrom = service.getUserWebDirectory(savedUser.getId(), WEBPANEL.name(), dirFrom.getName());
        dirTo = service.getUserWebDirectory(savedUser.getId(), WEBPANEL.name(), dirTo.getName());
        
        for (int i = 0; i < dirFrom.getPages().size(); i++ ) {
            assertEquals(i, dirFrom.getPages().get(i).getOrder());
        }
        
        for (int i = 0; i < dirTo.getPages().size(); i++ ) {
            assertEquals(i, dirTo.getPages().get(i).getOrder());
        }
        
        PersistableWebPage movedPage = dataOperator.findWebPageInDirectoryNotNull(dirTo.getPages(), movedPageName);
        assertEquals(movedPageName, movedPage.getName());
        assertEquals(dirTo.getPages().size() - 1, movedPage.getOrder());
    }

    /**
     * Test of moveUserWebPageIntoDirectoryAndOrder method, of class UserWebContentServiceWorker.
     */
    @Test
    public void testMoveUserWebPageIntoDirectoryAndOrder() {
        int panelDirsQty = 4;
        int pagesQtyInDirFrom = 6;
        int pagesQtyInDirTo = 4;
        int dirFromIndex = 1;
        int dirToIndex = 3;
        int movedPageInitialIndex = 3;
        int movedPageNewOrder = 1;
        
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        List<PersistableWebDirectory> dirs = newFakeDirs(savedUser, WEBPANEL, 6, panelDirsQty);
        
        PersistableWebDirectory dirFrom = dirs.get(dirFromIndex);
        PersistableWebDirectory dirTo = dirs.get(dirToIndex);
        
        List<PersistableWebPage> pagesInDirFrom = newFakePages(dirFrom, 11, pagesQtyInDirFrom);
        List<PersistableWebPage> pagesInDirTo = newFakePages(dirTo, 88, pagesQtyInDirTo);
        
        String movedPageName = pagesInDirFrom.get(movedPageInitialIndex).getName();
                        
        dirFrom.setPages(pagesInDirFrom);
        dirTo.setPages(pagesInDirTo);
        
        dirs = dirsRepo.save(dirs);
        dirsRepo.flush();
        dirFrom = dirs.get(dirFromIndex);
        dirTo = dirs.get(dirToIndex);
                
        boolean moved = service.moveUserWebPageIntoDirectoryAndOrder(
                savedUser.getId(), WEBPANEL.name(), dirFrom.getName(), dirTo.getName(), movedPageName, movedPageNewOrder);
        
        assertTrue(moved);
        
//        int expectedDirToSize = pagesQtyInDirTo + 1;
//        int expectedDirFromSize = pagesQtyInDirFrom - 1;
//        int actualDirToSize = countRowsInTableWhere(jdbcTemplate, "pages", " dir_id IS " + dirTo.getId());
//        int actualDirFromSize = countRowsInTableWhere(jdbcTemplate, "pages", " dir_id IS " + dirFrom.getId());
//        assertEquals(expectedDirFromSize, actualDirFromSize);
//        assertEquals(expectedDirToSize, actualDirToSize);
        
        dirFrom = service.getUserWebDirectory(savedUser.getId(), WEBPANEL.name(), dirFrom.getName());
        dirTo = service.getUserWebDirectory(savedUser.getId(), WEBPANEL.name(), dirTo.getName());
        
        for (int i = 0; i < dirFrom.getPages().size(); i++ ) {            
            assertEquals(i, dirFrom.getPages().get(i).getOrder());
        }
        
        for (int i = 0; i < dirTo.getPages().size(); i++ ) {
            assertEquals(i, dirTo.getPages().get(i).getOrder());
        }
        
        PersistableWebPage movedPage = dataOperator.findWebPageInDirectoryNotNull(dirTo.getPages(), movedPageName);
        assertEquals(movedPageName, movedPage.getName());
        assertEquals(movedPageNewOrder, movedPage.getOrder());
    }

    /**
     * Test of moveUserWebPageIntoDirectoryAndPlace method, of class UserWebContentServiceWorker.
     */
    @Test
    public void testMoveUserWebPageIntoDirectoryAndPlace() {
        int panelDirsQty = 4;
        int bookmDirsQty = 5;
        int pagesQtyInDirFrom = 6;
        int pagesQtyInDirTo = 4;
        int dirFromIndex = 1;
        int dirToIndex = 3;
        int movedPageInitialIndex = 3;
        
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        List<PersistableWebDirectory> panelDirs = newFakeDirs(savedUser, WEBPANEL, 10, panelDirsQty);
        List<PersistableWebDirectory> bookmDirs = newFakeDirs(savedUser, BOOKMARKS, 20, bookmDirsQty);
        
        PersistableWebDirectory dirFrom = panelDirs.get(dirFromIndex);
        PersistableWebDirectory dirTo = bookmDirs.get(dirToIndex);
        
        List<PersistableWebPage> pagesInDirFrom = newFakePages(dirFrom, 1, pagesQtyInDirFrom);
        List<PersistableWebPage> pagesInDirTo = newFakePages(dirTo, 20, pagesQtyInDirTo);
        
        String movedPageName = pagesInDirFrom.get(movedPageInitialIndex).getName();
        
        dirFrom.setPages(pagesInDirFrom);
        dirTo.setPages(pagesInDirTo);
        
        panelDirs = dirsRepo.save(panelDirs);
        bookmDirs = dirsRepo.save(bookmDirs);
        dirsRepo.flush();
        dirFrom = panelDirs.get(dirFromIndex);
        dirTo = bookmDirs.get(dirToIndex);
        
        boolean moved = service.moveUserWebPageIntoDirectoryAndPlace(
                savedUser.getId(), WEBPANEL.name(), BOOKMARKS.name(), dirFrom.getName(), dirTo.getName(), movedPageName);
        
        assertTrue(moved);
        
//        int expectedDirToSize = pagesQtyInDirTo + 1;
//        int expectedDirFromSize = pagesQtyInDirFrom - 1;
//        int actualDirToSize = countRowsInTableWhere(jdbcTemplate, "pages", " dir_id IS " + dirTo.getId());
//        int actualDirFromSize = countRowsInTableWhere(jdbcTemplate, "pages", " dir_id IS " + dirFrom.getId());
//        assertEquals(expectedDirFromSize, actualDirFromSize);
//        assertEquals(expectedDirToSize, actualDirToSize);
        
        dirFrom = service.getUserWebDirectory(savedUser.getId(), WEBPANEL.name(), dirFrom.getName());
        dirTo = service.getUserWebDirectory(savedUser.getId(), BOOKMARKS.name(), dirTo.getName());
        
        for (int i = 0; i < dirFrom.getPages().size(); i++ ) {
            assertEquals(i, dirFrom.getPages().get(i).getOrder());
        }
        
        for (int i = 0; i < dirTo.getPages().size(); i++ ) {
            assertEquals(i, dirTo.getPages().get(i).getOrder());
        }
        
        PersistableWebPage movedPage = dataOperator.findWebPageInDirectoryNotNull(dirTo.getPages(), movedPageName);
        assertEquals(movedPageName, movedPage.getName());
        assertEquals(dirTo.getPages().size() - 1, movedPage.getOrder());
    }

    /**
     * Test of deleteUserWebDirectory method, of class UserWebContentServiceWorker.
     */
    @Test
    public void testDeleteUserWebDirectory() {
        int dirsQty = 5;
        int deletedDirIndex = 1;     
        int anotherDir1Index = 3;
        int anotherDir2Index = 0;
        int pagesQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        List<PersistableWebDirectory> dirs = newFakeDirs(savedUser, WEBPANEL, 10, dirsQty);
        PersistableWebDirectory deletedDir = dirs.get(deletedDirIndex);
        PersistableWebDirectory anotherDir1 = dirs.get(anotherDir1Index);
        PersistableWebDirectory anotherDir2 = dirs.get(anotherDir2Index);
        List<PersistableWebPage> pagesInDeletedDir = newFakePages(deletedDir, 10, pagesQty);
        List<PersistableWebPage> pagesInAnotherDir1 = newFakePages(anotherDir1, 20, pagesQty);
        List<PersistableWebPage> pagesInAnotherDir2 = newFakePages(anotherDir2, 30, pagesQty);
        deletedDir.setPages(pagesInDeletedDir);
        anotherDir1.setPages(pagesInAnotherDir1);
        anotherDir2.setPages(pagesInAnotherDir2);
        dirs = dirsRepo.save(dirs);
        dirsRepo.flush();
        
        int expectedDirsQty = dirsQty;
        int actualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
        int expectedPagesTotalQty = pagesQty * 3;
        int actualPagesTotalQty = countRowsInTable(jdbcTemplate, "pages");
        assertEquals(expectedPagesTotalQty, actualPagesTotalQty); 
        assertEquals(expectedDirsQty, actualDirsQty); 
              
        String deletedDirName = dirs.get(deletedDirIndex).getName();
        
        boolean deleted = service.deleteUserWebDirectory(savedUser.getId(), WEBPANEL.name(), deletedDirName);
        assertTrue(deleted);
        
//        expectedDirsQty = dirsQty - 1;
//        actualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
//        expectedPagesTotalQty = pagesQty * 2;
//        actualPagesTotalQty = countRowsInTable(jdbcTemplate, "pages");
//        assertEquals(expectedPagesTotalQty, actualPagesTotalQty); 
//        assertEquals(expectedDirsQty, actualDirsQty); 
        
        List<PersistableWebDirectory> foundDirs = service.getUserAllWebDirectories(savedUser.getId());
        assertEquals(dirsQty - 1, foundDirs.size());
        try {
            PersistableWebDirectory nonExistedDir = service.getUserWebDirectory(
                    savedUser.getId(), WEBPANEL.name(), deletedDirName);
            fail();
        } catch (BadDataRequestArgumentsException e) {
            assertTrue(true);
        }
    }

    /**
     * Test of deleteUserWebPage method, of class UserWebContentServiceWorker.
     */
    @Test
    public void testDeleteUserWebPage() {
        int pagesQty = 5;
        int deletedPageIndex = 1;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedDir = newFakeDir(savedUser, WEBPANEL, 3);
        List<PersistableWebPage> savedPages = newFakePages(savedDir, 5, pagesQty);
        savedDir.setPages(savedPages);
        savedDir = dirsRepo.save(savedDir);
        dirsRepo.flush();
        
        int expectedPagesQty = pagesQty;
        int actualPagesQty = countRowsInTable(jdbcTemplate, "pages");
        assertEquals(expectedPagesQty, actualPagesQty); 
        
        String deletedPageName = savedDir.getPages().get(deletedPageIndex).getName();
        
        boolean deleted = service.deleteUserWebPage(
                savedUser.getId(), WEBPANEL.name(), savedDir.getName(), deletedPageName);
        assertTrue(deleted);
        
//        expectedPagesQty = pagesQty - 1;
//        actualPagesQty = countRowsInTable(jdbcTemplate, "pages");
//        assertEquals(expectedPagesQty, actualPagesQty); 
        
        savedDir = service.getUserWebDirectory(savedUser.getId(), WEBPANEL.name(), savedDir.getName());
        for (int i = 0; i < savedDir.getPages().size(); i++) {
            assertEquals(i, savedDir.getPages().get(i).getOrder());
            assertNotEquals(savedDir.getPages().get(i).getName(), deletedPageName);
        }
        
        try {
            PersistableWebPage nonExistedPage = dataOperator.findWebPageInDirectoryNotNull(savedDir.getPages(), deletedPageName);
            fail();
        } catch (BadDataRequestArgumentsException e) {
            assertTrue(true);
        }
    }

    /**
     * Test of createUserWebPage method, of class UserWebContentServiceWorker.
     */
    @Test
    public void testCreateUserWebPage() {        
        int pagesQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedDir = newFakeDir(savedUser, WEBPANEL, 3);
        List<PersistableWebPage> savedPages = newFakePages(savedDir, 5, pagesQty);
        savedDir.setPages(savedPages);
        savedDir = dirsRepo.save(savedDir);
        dirsRepo.flush();
        
        int expectedPagesQty = pagesQty;
        int actualPagesQty = countRowsInTable(jdbcTemplate, "pages");
        assertEquals(expectedPagesQty, actualPagesQty); 
        
        String newPageName = "valid-> page_name (created) [new56]";
        String newPageUrl = "http://valid.page.im?peers=3342544_c65_c1_c167_c18&sel=c91";        
        
        boolean saved = service.createUserWebPage(savedUser.getId(), WEBPANEL.name(), savedDir.getName(), newPageName, newPageUrl);
        assertTrue(saved);
        
//        expectedPagesQty = pagesQty + 1;
//        actualPagesQty = countRowsInTable(jdbcTemplate, "pages");
//        assertEquals(expectedPagesQty, actualPagesQty); 
        
        savedDir = service.getUserWebDirectory(savedUser.getId(), WEBPANEL.name(), savedDir.getName());
        for (int i = 0; i < savedDir.getPages().size(); i++) {
            assertEquals(i, savedDir.getPages().get(i).getOrder());
        }
        assertEquals(newPageName, savedDir.getPages().get(savedDir.getPages().size() - 1).getName());
        assertEquals(savedDir.getPages().get(savedDir.getPages().size() - 1).getOrder(), savedDir.getPages().size() - 1);
    }

    /**
     * Test of createUserWebDirectory method, of class UserWebContentServiceWorker.
     */
    @Test
    public void testCreateUserWebDirectory() {
        int dirsQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        List<PersistableWebDirectory> dirs = newFakeDirs(savedUser, WEBPANEL, 10, dirsQty);
        List<PersistableWebDirectory> dirsInAnotherPlace = newFakeDirs(savedUser, BOOKMARKS, 20, dirsQty);
        dirs = dirsRepo.save(dirs);
        dirsInAnotherPlace = dirsRepo.save(dirsInAnotherPlace);
        dirsRepo.flush();
        
        int expectedDirsQty = dirsQty * 2;
        int actualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
        assertEquals(expectedDirsQty, actualDirsQty); 
        
        String newDirName = "valid [dir]_name (new 1)";
        
        boolean created = service.createUserWebDirectory(savedUser.getId(), WEBPANEL.name(), newDirName);
        assertTrue(created);
        
        expectedDirsQty = (dirsQty * 2) + 1;
        actualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
        assertEquals(expectedDirsQty, actualDirsQty); 
        int expectedPanelDirsQty = dirsQty + 1;
        int actualPanelDirsQty = countRowsInTableWhere(jdbcTemplate, "dirs", " dir_place IS 'WEBPANEL' ");
        assertEquals(expectedPanelDirsQty, actualPanelDirsQty);
                
        PersistableWebDirectory newDir = service.getUserWebDirectory(savedUser.getId(), WEBPANEL.name(), newDirName);
        List<PersistableWebDirectory> dirsInPlace = service.getUserWebDirectoriesInPlace(savedUser.getId(), WEBPANEL.name());
        assertEquals(newDirName, newDir.getName());
        assertEquals(WEBPANEL, newDir.getPlace());
        assertEquals(dirsInPlace.size() - 1, newDir.getOrder());
    }

    /**
     * Test of getUserPanelWebDirectories method, of class UserWebContentServiceWorker.
     */
    @Test
    public void testGetUserWebDirectoriesInPlace() {
        int dirsQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableUser anotherUser = usersRepo.saveAndFlush(newFakeUser(5));
        List<PersistableWebDirectory> anotherUserDirs = newFakeDirs(anotherUser, WEBPANEL, 10, dirsQty);
        List<PersistableWebDirectory> dirs = newFakeDirs(savedUser, WEBPANEL, 10, dirsQty);
        List<PersistableWebDirectory> dirsInAnotherPlace = newFakeDirs(savedUser, BOOKMARKS, 20, dirsQty);
        anotherUserDirs = dirsRepo.save(anotherUserDirs);
        dirs = dirsRepo.save(dirs);
        dirsInAnotherPlace = dirsRepo.save(dirsInAnotherPlace);
        dirsRepo.flush();
        
        int expectedDirsQty = dirsQty * 3;
        int actualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
        assertEquals(expectedDirsQty, actualDirsQty); 
        
        int expectedPanelDirsQty = dirsQty;
        int actualPanelDirsQty = countRowsInTableWhere(jdbcTemplate, "dirs", " dir_place IS 'WEBPANEL' AND user_id IS " + savedUser.getId());
        assertEquals(expectedPanelDirsQty, actualPanelDirsQty);
        
        List<PersistableWebDirectory> foundUserDirsInPlace = 
                service.getUserWebDirectoriesInPlace(savedUser.getId(), WEBPANEL.name());
        assertEquals(dirs.size(), foundUserDirsInPlace.size());
        for (int i = 0; i < foundUserDirsInPlace.size(); i++) {
            assertEquals(dirs.get(i).getName(), foundUserDirsInPlace.get(i).getName());
        }
    }

    /**
     * Test of getUserWebDirectory method, of class UserWebContentServiceWorker.
     */
    @Test
    public void testGetUserWebDirectory() {
        int dirsQty = 5;
        int searchedDirIndex = 3;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableUser anotherUser = usersRepo.saveAndFlush(newFakeUser(5));
        List<PersistableWebDirectory> anotherUserDirs = newFakeDirs(anotherUser, WEBPANEL, 10, dirsQty);
        List<PersistableWebDirectory> dirs = newFakeDirs(savedUser, WEBPANEL, 10, dirsQty);
        List<PersistableWebDirectory> dirsInAnotherPlace = newFakeDirs(savedUser, BOOKMARKS, 20, dirsQty);
        anotherUserDirs = dirsRepo.save(anotherUserDirs);
        dirs = dirsRepo.save(dirs);
        dirsInAnotherPlace = dirsRepo.save(dirsInAnotherPlace);
        dirsRepo.flush();
        
        String searchedDirName = dirs.get(searchedDirIndex).getName();
        
        PersistableWebDirectory foundDir = service.getUserWebDirectory(
                savedUser.getId(), WEBPANEL.name(), searchedDirName);
        assertEquals(searchedDirName, foundDir.getName());        
    }

    /**
     * Test of getUserAllWebDirectories method, of class UserWebContentServiceWorker.
     */
    @Test
    public void testGetAllUserWebDirectories() {
        int dirsQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableUser anotherUser = usersRepo.saveAndFlush(newFakeUser(5));
        List<PersistableWebDirectory> anotherUserDirs = newFakeDirs(anotherUser, WEBPANEL, 10, dirsQty);
        List<PersistableWebDirectory> dirs = newFakeDirs(savedUser, WEBPANEL, 10, dirsQty);
        List<PersistableWebDirectory> dirsInAnotherPlace = newFakeDirs(savedUser, BOOKMARKS, 20, dirsQty);
        anotherUserDirs = dirsRepo.save(anotherUserDirs);
        dirs = dirsRepo.save(dirs);
        dirsInAnotherPlace = dirsRepo.save(dirsInAnotherPlace);
        dirsRepo.flush();
        
        int expectedDirsQty = dirsQty * 3;
        int actualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
        assertEquals(expectedDirsQty, actualDirsQty); 
        
        int expectedPanelDirsQty = dirsQty * 2;
        int actualPanelDirsQty = countRowsInTableWhere(jdbcTemplate, "dirs", " user_id IS " + savedUser.getId());
        assertEquals(expectedPanelDirsQty, actualPanelDirsQty);
        
        List<PersistableWebDirectory> foundUserDirs = service.getUserAllWebDirectories(savedUser.getId());
        assertEquals(dirs.size() + dirsInAnotherPlace.size(), foundUserDirs.size());
    }

}