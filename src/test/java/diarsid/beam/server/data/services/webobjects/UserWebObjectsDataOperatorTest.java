/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.services.webobjects;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.Test.None;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import diarsid.beam.server.domain.services.webobjects.UserWebObjectsDataOperator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;

import static diarsid.beam.server.domain.entities.WebPlacement.BOOKMARKS;
import static diarsid.beam.server.domain.entities.WebPlacement.WEBPANEL;

import static util.FakeUserProducer.newFakeUser;
import static util.FakeWebDirsProducer.DIR_NAME_TEMPLATE;
import static util.FakeWebDirsProducer.newFakeDir;
import static util.FakeWebDirsProducer.newFakeDirs;
import static util.FakeWebPagesProducer.PAGE_NAME_TEMPLATE;
import static util.FakeWebPagesProducer.newFakePages;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfig.class})
@Transactional
public class UserWebObjectsDataOperatorTest {
    
    private static final Logger logger = LoggerFactory.getLogger(UserWebObjectsDataOperatorTest.class);
    
    @Autowired
    private UserWebObjectsDataOperator dataOperator;
        
    @Autowired
    private RepositoryUsers usersRepo;
    
    @Autowired
    private RepositoryWebDirectories dirsRepo;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public UserWebObjectsDataOperatorTest() {       
    }
    
//    @Before
//    public void setUp() {
//        dataOperator.
//    }

    /**
     * Test of findUserNotNull method, of class UserWebObjectsDataOperatorWorker.
     */
    @Test(expected = BadDataRequestArgumentsException.class)
    public void testFindUserNotNull() {
        PersistableUser user = dataOperator.findUserNotNull(1);
    }
    
    @Test(expected = None.class)
    public void testFindUserNotNull_pass() {
        PersistableUser user = newFakeUser(1);
        user = usersRepo.saveAndFlush(user);
        
        PersistableUser foundUser = dataOperator.findUserNotNull(user.getId());
        assertEquals(user.getId(), foundUser.getId());
    }

    /**
     * Test of checkUser method, of class UserWebObjectsDataOperatorWorker.
     */
    @Test(expected = BadDataRequestArgumentsException.class)
    public void testCheckUser_fail() {
        dataOperator.checkUser(-1);     
    }
    
    @Test(expected = None.class)
    public void testCheckUser_pass() {
        PersistableUser user = newFakeUser(1);
        user = usersRepo.saveAndFlush(user);
        
        dataOperator.checkUser(user.getId());
        
        int expectedUserQty = 1;
        int actualUserQty = countRowsInTable(jdbcTemplate, "users");
        assertEquals(expectedUserQty, actualUserQty);
    }

    /**
     * Test of countWebDirectoriesInPlace method, of class UserWebObjectsDataOperatorWorker.
     */
    @Test
    public void testCountWebDirectoriesInPlace() {
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        List<PersistableWebDirectory> savedDirs = dirsRepo.save(newFakeDirs(savedUser, WEBPANEL, 3, 5));
        dirsRepo.flush();
        
        int expectedDirsQty = savedDirs.size();
        logger.info(savedDirs.get(0).getPages().getClass().getName());
        int assuredActualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
        
        int actualDirsQty = dataOperator.countWebDirectoriesInPlace(WEBPANEL, savedUser.getId());
        
        assertEquals(expectedDirsQty, assuredActualDirsQty);
        assertEquals(expectedDirsQty, actualDirsQty);        
    }

    /**
     * Test of findAllUserWebDirectories method, of class UserWebObjectsDataOperatorWorker.
     */
    @Test
    public void testFindAllUserWebDirectories() {
        int panelDirs = 5;
        int bookmDirs = 3;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        List<PersistableWebDirectory> savedPanelDirs = dirsRepo.save(newFakeDirs(savedUser, WEBPANEL, 3, panelDirs));
        List<PersistableWebDirectory> savedBookmDirs = dirsRepo.save(newFakeDirs(savedUser, BOOKMARKS, 10, bookmDirs));
        dirsRepo.flush();
        
        int expectedDirsQty = savedPanelDirs.size() + savedBookmDirs.size();
        int assuredActualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
        
        int actualDirsQty = dataOperator.findAllUserWebDirectories(savedUser.getId()).size();
        assertEquals(expectedDirsQty, assuredActualDirsQty);
        assertEquals(expectedDirsQty, actualDirsQty);
    }

    /**
     * Test of findWebPageInDirectoryNotNull method, of class UserWebObjectsDataOperatorWorker.
     */
    @Test(expected = None.class)
    public void testFindWebPageInDirectoryNotNull_noException() {
        int pagesQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedPanelDir = dirsRepo.saveAndFlush(newFakeDir(savedUser, WEBPANEL, 3));
        //List<PersistableWebPage> savedPages = pagesRepo.save(newFakePages(savedPanelDir, 15, pagesQty));
        List<PersistableWebPage> savedPages = newFakePages(savedPanelDir, 15, pagesQty);
        savedPanelDir.getPages().addAll(savedPages);
        dirsRepo.saveAndFlush(savedPanelDir);
        
        String expectedPageName = PAGE_NAME_TEMPLATE + 17;
        
        int expectedDirsQty = 1;
        int assuredActualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
        int expectedPagesQty = pagesQty;
        int assuredActualPagesQty = countRowsInTable(jdbcTemplate, "pages");
        
        PersistableWebDirectory foundDir = 
                dataOperator.findWebDirectoryNotNull(savedPanelDir.getName(), WEBPANEL, savedUser.getId());
        PersistableWebPage foundPage = 
                dataOperator.findWebPageInDirectoryNotNull(foundDir.getPages(), expectedPageName);
        assertEquals(expectedPageName, foundPage.getName());
        assertEquals(expectedDirsQty, assuredActualDirsQty);
        assertEquals(expectedPagesQty, assuredActualPagesQty);
    }
    
    @Test(expected = BadDataRequestArgumentsException.class)
    public void testFindWebPageInDirectoryNotNull_exceptionThrown() {
        int pagesQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedPanelDir = dirsRepo.saveAndFlush(newFakeDir(savedUser, WEBPANEL, 3));
//        List<PersistableWebPage> savedPages = pagesRepo.save(newFakePages(savedPanelDir, 15, pagesQty));
        List<PersistableWebPage> savedPages = newFakePages(savedPanelDir, 15, pagesQty);
        savedPanelDir.getPages().addAll(savedPages);
        dirsRepo.saveAndFlush(savedPanelDir);
                
        PersistableWebDirectory foundDir = 
                dataOperator.findWebDirectoryNotNull(savedPanelDir.getName(), WEBPANEL, savedUser.getId());
        PersistableWebPage foundPage = 
                dataOperator.findWebPageInDirectoryNotNull(foundDir.getPages(), "another_page_name");
        foundPage.getName();
        fail();
    }

    /**
     * Test of findWebDirectoryNotNull method, of class UserWebObjectsDataOperatorWorker.
     */
    @Test(expected = None.class)
    public void testFindWebDirectoryNotNull_noException() {  
        int pagesQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedPanelDir = newFakeDir(savedUser, WEBPANEL, 3);
        List<PersistableWebPage> savedPages = newFakePages(savedPanelDir, 15, pagesQty);
        savedPanelDir.setPages(savedPages);
        savedPanelDir = dirsRepo.saveAndFlush(savedPanelDir);
        
        int expectedDirsQty = 1;
        int assuredActualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
        int expectedPagesQty = pagesQty;
        int assuredActualPagesQty = countRowsInTable(jdbcTemplate, "pages");

        PersistableWebDirectory foundDir = 
                dataOperator.findWebDirectoryNotNull(savedPanelDir.getName(), WEBPANEL, savedUser.getId());
        
        assertEquals(foundDir.getPages().size(), pagesQty);
        assertEquals(foundDir.getName(), savedPanelDir.getName());
        assertEquals(expectedDirsQty, assuredActualDirsQty);
        assertEquals(expectedPagesQty, assuredActualPagesQty);
    }
    
    @Test(expected = BadDataRequestArgumentsException.class)
    public void testFindWebDirectoryNotNull_exceptionThrown() {  
        int pagesQty = 5;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedPanelDir = newFakeDir(savedUser, WEBPANEL, 3);
        List<PersistableWebPage> savedPages = newFakePages(savedPanelDir, 15, pagesQty);
        savedPanelDir.setPages(savedPages);
        savedPanelDir = dirsRepo.saveAndFlush(savedPanelDir);

        PersistableWebDirectory foundDir = 
                dataOperator.findWebDirectoryNotNull("another_name", WEBPANEL, savedUser.getId());
        foundDir.getName();
        fail();
    }

    /**
     * Test of findWebDirectoryNotNullNotEmpty method, of class UserWebObjectsDataOperatorWorker.
     */
    @Test(expected = None.class)
    public void testFindWebDirectoryNotNullNotEmpty_noException() {
        int pagesQty = 3;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedPanelDir = newFakeDir(savedUser, WEBPANEL, 3);
        List<PersistableWebPage> savedPages = newFakePages(savedPanelDir, 15, pagesQty);
        savedPanelDir.setPages(savedPages);
        savedPanelDir = dirsRepo.saveAndFlush(savedPanelDir);
        
        int expectedDirsQty = 1;
        int assuredActualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
        int expectedPagesQty = pagesQty;
        int assuredActualPagesQty = countRowsInTable(jdbcTemplate, "pages");
        
        PersistableWebDirectory foundDir = 
                dataOperator.findWebDirectoryNotNullNotEmpty(savedPanelDir.getName(), WEBPANEL, savedUser.getId());
        
        assertEquals(savedPanelDir.getPages().size(), foundDir.getPages().size());
        assertEquals(expectedDirsQty, assuredActualDirsQty);
        assertEquals(expectedPagesQty, assuredActualPagesQty);
    }
    
    @Test(expected = BadDataRequestArgumentsException.class)
    public void testFindWebDirectoryNotNullNotEmpty_exceptionThrown() {
        int pagesQty = 0;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(3));
        PersistableWebDirectory savedPanelDir = newFakeDir(savedUser, WEBPANEL, 3);
        savedPanelDir.setPages(new ArrayList<>());
        savedPanelDir = dirsRepo.saveAndFlush(savedPanelDir);
        
        int expectedDirsQty = 1;
        int assuredActualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
        int expectedPagesQty = pagesQty;
        int assuredActualPagesQty = countRowsInTable(jdbcTemplate, "pages");
        
        assertEquals(expectedDirsQty, assuredActualDirsQty);
        assertEquals(expectedPagesQty, assuredActualPagesQty);
        
        PersistableWebDirectory foundDir = 
                dataOperator.findWebDirectoryNotNullNotEmpty(savedPanelDir.getName(), WEBPANEL, savedUser.getId());
        foundDir.getName();
        fail();
    }

    /**
     * Test of findWebDirectoriesNotEmpty method, of class UserWebObjectsDataOperatorWorker.
     */
    @Test(expected = None.class)
    public void testFindWebDirectoriesNotEmpty_noException() {
        int dirsQty = 4;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(9));
        List<PersistableWebDirectory> savedDirs = newFakeDirs(savedUser, WEBPANEL, 6, dirsQty);
        savedDirs = dirsRepo.save(savedDirs);
        dirsRepo.flush();
        
        int expectedDirsQty = dirsQty;
        int assuredActualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
        
        List<PersistableWebDirectory> foundDirs = 
                dataOperator.findWebDirectoriesNotEmpty(WEBPANEL, savedUser.getId());
        
        assertEquals(dirsQty, foundDirs.size());
        assertEquals(expectedDirsQty, assuredActualDirsQty);
    }
    
    @Test(expected = BadDataRequestArgumentsException.class)
    public void testFindWebDirectoriesNotEmpty_exceptionThrown() {
        int dirsQty = 4;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(9));
        List<PersistableWebDirectory> savedDirs = newFakeDirs(savedUser, WEBPANEL, 6, dirsQty);
        savedDirs = dirsRepo.save(savedDirs);
        dirsRepo.flush();
        
        int expectedDirsQty = dirsQty;
        int assuredActualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
        
        assertEquals(expectedDirsQty, assuredActualDirsQty);
        
        List<PersistableWebDirectory> foundDirs = 
                dataOperator.findWebDirectoriesNotEmpty(BOOKMARKS, savedUser.getId());
        foundDirs.size();
        fail();  
    }
    
    /**
     * Test of findDirectoryFromDirectoriesNotNull method, of class UserWebObjectsDataOperatorWorker.
     */
    @Test(expected = None.class)
    public void testFindDirectoryFromDirectoriesNotNull_noException() {
        int dirsQty = 4;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(9));
        List<PersistableWebDirectory> savedDirs = newFakeDirs(savedUser, WEBPANEL, 6, dirsQty);
        savedDirs = dirsRepo.save(savedDirs);
        dirsRepo.flush();
        
        int expectedDirsQty = dirsQty;
        int assuredActualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
        
        String expectedDirName = DIR_NAME_TEMPLATE + 8;
        
        List<PersistableWebDirectory> foundDirs = 
                dataOperator.findWebDirectoriesNotEmpty(WEBPANEL, savedUser.getId());
        
        PersistableWebDirectory foundDir = 
                dataOperator.findDirectoryFromDirectoriesNotNull(foundDirs, expectedDirName);
        
        assertEquals(expectedDirName, foundDir.getName());
        assertEquals(expectedDirsQty, assuredActualDirsQty);
    }
    
    @Test(expected = BadDataRequestArgumentsException.class)
    public void testFindDirectoryFromDirectoriesNotNull_exceptionThrown() {
        int dirsQty = 4;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(9));
        List<PersistableWebDirectory> savedDirs = newFakeDirs(savedUser, WEBPANEL, 6, dirsQty);
        savedDirs = dirsRepo.save(savedDirs);
        dirsRepo.flush();
        
        int expectedDirsQty = dirsQty;
        int assuredActualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
                
        List<PersistableWebDirectory> foundDirs = 
                dataOperator.findWebDirectoriesNotEmpty(WEBPANEL, savedUser.getId());
        
        assertEquals(expectedDirsQty, assuredActualDirsQty);
        
        PersistableWebDirectory foundDir = 
                dataOperator.findDirectoryFromDirectoriesNotNull(foundDirs, "another_name");
        foundDir.getName();
        fail();        
    }

    /**
     * Test of saveModifiedDirectories method, of class UserWebObjectsDataOperatorWorker.
     */
    @Test
    public void testSaveModifiedDirectories() {
        int dirsQty = 4;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(9));
        List<PersistableWebDirectory> savedDirs = newFakeDirs(savedUser, WEBPANEL, 6, dirsQty);
        savedDirs = dataOperator.saveModifiedDirectories(savedDirs);
        
        int expectedDirsQty = dirsQty;
        int assuredActualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
        
        List<PersistableWebDirectory> foundDirs = dirsRepo.findAll();
        assertEquals(expectedDirsQty, assuredActualDirsQty);
        assertEquals(savedDirs.size(), foundDirs.size());
        assertTrue(savedDirs.containsAll(foundDirs) && foundDirs.containsAll(savedDirs));
    }

    /**
     * Test of saveModifiedDirectory method, of class UserWebObjectsDataOperatorWorker.
     */
    @Test
    public void testSaveModifiedDirectory() {
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(9));
        PersistableWebDirectory savedDir = newFakeDir(savedUser, WEBPANEL, 3);
        savedDir = dataOperator.saveModifiedDirectory(savedDir);
        
        int expectedDirsQty = 1;
        int assuredActualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
        
        PersistableWebDirectory foundDir = dirsRepo.findOne(savedDir.getId());
        assertEquals(expectedDirsQty, assuredActualDirsQty);
        assertEquals(savedDir.getName(), foundDir.getName());
        
        String newName = "new_name";
        int pagesQty = 3;
        List<PersistableWebPage> newPages = newFakePages(savedDir, 3, pagesQty);   
        savedDir.setName(newName);
        savedDir.getPages().addAll(newPages);
        savedDir = dataOperator.saveModifiedDirectory(savedDir);
        
        int expectedPagesQty = pagesQty;
        int assuredActualPagesQty = countRowsInTable(jdbcTemplate, "pages");
        foundDir = dirsRepo.findOne(savedDir.getId());
        assertEquals(expectedPagesQty, assuredActualPagesQty);
        assertEquals(savedDir.getPages().size(), foundDir.getPages().size());
        assertEquals(newName, foundDir.getName());
        
        for (int i = 0; i < savedDir.getPages().size(); i++) {
            savedDir.getPages().get(i).setName("new_page_name_" + i);
        }
        savedDir = dataOperator.saveModifiedDirectory(savedDir);
        foundDir = dirsRepo.findOne(savedDir.getId());
        assertTrue(savedDir.getPages().containsAll(foundDir.getPages()) && foundDir.getPages().containsAll(savedDir.getPages()));
        
    }

    /**
     * Test of deleteDirectory method, of class UserWebObjectsDataOperatorWorker.
     */
    @Test
    public void testDeleteDirectory() {
        int dirsQty = 4;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(9));
        List<PersistableWebDirectory> savedDirs = newFakeDirs(savedUser, WEBPANEL, 6, dirsQty);
        savedDirs = dataOperator.saveModifiedDirectories(savedDirs);
        
        int expectedDirsQty = dirsQty;
        int assuredActualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
        assertEquals(expectedDirsQty, assuredActualDirsQty);
        
        PersistableWebDirectory dirToDelete = savedDirs.get(2);
        dataOperator.deleteDirectory(dirToDelete);
        
        expectedDirsQty = dirsQty - 1;
        assuredActualDirsQty = countRowsInTable(jdbcTemplate, "dirs");
        assertEquals(expectedDirsQty, assuredActualDirsQty);
    }

    /**
     * Test of deletePage method, of class UserWebObjectsDataOperatorWorker.
     */
    @Test
    public void testDeletePage() {
        int dirsQty = 4;
        PersistableUser savedUser = usersRepo.saveAndFlush(newFakeUser(9));
        List<PersistableWebDirectory> savedDirs = newFakeDirs(savedUser, WEBPANEL, 6, dirsQty);
        savedDirs = dataOperator.saveModifiedDirectories(savedDirs);
        
        PersistableWebDirectory workedDir = savedDirs.get(2);
        
        int pagesQty = 5;
        List<PersistableWebPage> newPages = newFakePages(workedDir, 3, pagesQty);   
        workedDir.getPages().addAll(newPages);
        
        dataOperator.saveModifiedDirectory(workedDir);
        
        int expectedPagesQty = pagesQty;
        int assuredActualPagesqty = countRowsInTable(jdbcTemplate, "pages");
        assertEquals(expectedPagesQty, assuredActualPagesqty);
                
        PersistableWebPage deletedPage = workedDir.getPages().get(3);
        workedDir.getPages().remove(deletedPage);
        dataOperator.saveModifiedDirectory(workedDir);
        
        expectedPagesQty = pagesQty - 1;
        assuredActualPagesqty = countRowsInTable(jdbcTemplate, "pages");
        assertEquals(expectedPagesQty, assuredActualPagesqty);
        
        PersistableWebDirectory foundDir = 
                dataOperator.findWebDirectoryNotNullNotEmpty(workedDir.getName(), WEBPANEL, savedUser.getId());
        assertEquals(expectedPagesQty, foundDir.getPages().size());        
    }
}