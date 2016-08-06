/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.entities;

import java.util.List;

import org.junit.Test;

import diarsid.beam.server.data.entities.jpa.PersistableWebPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import static diarsid.beam.server.tests.util.Util.produceOrderableWebItems;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 *
 * @author Diarsid
 */
public class WebItemsOrdererTest {
    
    private final WebItemsOrderer orderer;

    public WebItemsOrdererTest() {
        orderer = new WebItemsOrderer();
    }

    /**
     * Test of reorderWebItemsAccordingToNewOrder method, of class WebItemsOrderer.
     */
    @Test
    public void testReorderWebItemsAccordingToNewOrder_straightOrder() {
        List<OrderableWebItem> tested = produceOrderableWebItems();
        List<OrderableWebItem> notModified = produceOrderableWebItems();
        List<OrderableWebItem> expected = produceOrderableWebItems();
        int itemOldOrder = 2;
        int itemNewOrder = 4;
        
        expected.get(2).setOrder(4);
        expected.get(3).setOrder(2);
        expected.get(4).setOrder(3);
        
        orderer.reorderWebItemsAccordingToNewOrder(tested, itemOldOrder, itemNewOrder);
        assertEquals(expected, tested);
        assertNotEquals(notModified, tested);
    }
    
    @Test
    public void testReorderWebItemsAccordingToNewOrder_straightOrder_wrongHighBoundary() {
        List<OrderableWebItem> tested = produceOrderableWebItems();
        List<OrderableWebItem> notModified = produceOrderableWebItems();
        List<OrderableWebItem> expected = produceOrderableWebItems();
        int itemOldOrder = 2;
        int itemNewOrder = 1000;
        
        expected.get(2).setOrder(5);
        expected.get(3).setOrder(2);
        expected.get(4).setOrder(3);
        expected.get(5).setOrder(4);
        
        orderer.reorderWebItemsAccordingToNewOrder(tested, itemOldOrder, itemNewOrder);
        assertEquals(expected, tested);
        assertNotEquals(notModified, tested);
    }
    
    @Test
    public void testReorderWebItemsAccordingToNewOrder_straightOrder_wrongLowBoundary() {
        List<OrderableWebItem> tested = produceOrderableWebItems();
        List<OrderableWebItem> notModified = produceOrderableWebItems();
        List<OrderableWebItem> expected = produceOrderableWebItems();
        int itemOldOrder = -1;
        int itemNewOrder = 4;
        
        expected.get(0).setOrder(4);
        expected.get(1).setOrder(0);
        expected.get(2).setOrder(1);
        expected.get(3).setOrder(2);
        expected.get(4).setOrder(3);
        
        orderer.reorderWebItemsAccordingToNewOrder(tested, itemOldOrder, itemNewOrder);
        assertEquals(expected, tested);
        assertNotEquals(notModified, tested);
    }
    
    @Test
    public void testReorderWebItemsAccordingToNewOrder_reverseOrder() {
        List<OrderableWebItem> tested = produceOrderableWebItems();
        List<OrderableWebItem> notModified = produceOrderableWebItems();
        List<OrderableWebItem> expected = produceOrderableWebItems();
        int itemOldOrder = 4;
        int itemNewOrder = 2;
        
        expected.get(4).setOrder(2);
        expected.get(2).setOrder(3);
        expected.get(3).setOrder(4);
        
        orderer.reorderWebItemsAccordingToNewOrder(tested, itemOldOrder, itemNewOrder);
        assertEquals(expected, tested);
        assertNotEquals(notModified, tested);
    }
    
    @Test
    public void testReorderWebItemsAccordingToNewOrder_reverseOrder_wrongHighBoundary() {
        List<OrderableWebItem> tested = produceOrderableWebItems();
        List<OrderableWebItem> notModified = produceOrderableWebItems();
        List<OrderableWebItem> expected = produceOrderableWebItems();
        int itemOldOrder = 200;
        int itemNewOrder = 2;
        
        expected.get(5).setOrder(2);
        expected.get(2).setOrder(3);
        expected.get(3).setOrder(4);
        expected.get(4).setOrder(5);
        
        orderer.reorderWebItemsAccordingToNewOrder(tested, itemOldOrder, itemNewOrder);
        assertEquals(expected, tested);
        assertNotEquals(notModified, tested);
    }
    
    @Test
    public void testReorderWebItemsAccordingToNewOrder_reverseOrder_wrongLowBoundary() {
        List<OrderableWebItem> tested = produceOrderableWebItems();
        List<OrderableWebItem> notModified = produceOrderableWebItems();
        List<OrderableWebItem> expected = produceOrderableWebItems();
        int itemOldOrder = 4;
        int itemNewOrder = -1;
        
        expected.get(4).setOrder(0);
        expected.get(0).setOrder(1);
        expected.get(1).setOrder(2);
        expected.get(2).setOrder(3);
        expected.get(3).setOrder(4);
        
        orderer.reorderWebItemsAccordingToNewOrder(tested, itemOldOrder, itemNewOrder);
        assertEquals(expected, tested);
        assertNotEquals(notModified, tested);
    }

    /**
     * Test of reorderToExtractWebItemLater method, of class WebItemsOrderer.
     */
    @Test
    public void testReorderToExtractWebItemLater() {
        List<OrderableWebItem> tested = produceOrderableWebItems();
        List<OrderableWebItem> notModified = produceOrderableWebItems();
        List<OrderableWebItem> expected = produceOrderableWebItems();
        int removedItemOrder = 3;
                
        expected.get(4).setOrder(3);
        expected.get(5).setOrder(4);        
        
        orderer.reorderToExtractWebItemLater(tested, removedItemOrder);
        
        assertEquals(expected, tested);
        assertNotEquals(notModified, tested);
    }
    
    @Test
    public void testReorderToExtractWebItemLater_wrongLowBoundary() {
        List<OrderableWebItem> tested = produceOrderableWebItems();
        List<OrderableWebItem> notModified = produceOrderableWebItems();
        List<OrderableWebItem> expected = produceOrderableWebItems();
        int removedItemOrder = -1;
                
        expected.get(1).setOrder(0);
        expected.get(2).setOrder(1);
        expected.get(3).setOrder(2);
        expected.get(4).setOrder(3);
        expected.get(5).setOrder(4);        
        
        orderer.reorderToExtractWebItemLater(tested, removedItemOrder);
        
        assertEquals(expected, tested);
        assertNotEquals(notModified, tested);
    }
    
    @Test
    public void testReorderToExtractWebItemLater_wrongHighBoundary() {
        List<OrderableWebItem> tested = produceOrderableWebItems();
        List<OrderableWebItem> notModified = produceOrderableWebItems();
        List<OrderableWebItem> expected = produceOrderableWebItems();
        int removedItemOrder = 1000;
        
        orderer.reorderToExtractWebItemLater(tested, removedItemOrder);
        
        assertEquals(expected, tested);
        assertEquals(notModified, tested);
    }

    /**
     * Test of reorderToInsertWebItemLater method, of class WebItemsOrderer.
     */
    @Test
    public void testReorderToInsertWebItemLater() {
        List<OrderableWebItem> tested = produceOrderableWebItems();
        List<OrderableWebItem> notModified = produceOrderableWebItems();
        List<OrderableWebItem> expected = produceOrderableWebItems();
        int itermOrderToInsert = 3;
        PersistableWebPage insertedItemTested = new PersistableWebPage();
        PersistableWebPage insertedItemExpected = new PersistableWebPage();
        
        insertedItemExpected.setOrder(itermOrderToInsert);
        expected.get(3).setOrder(4);
        expected.get(4).setOrder(5);
        expected.get(5).setOrder(6);
        
        orderer.reorderToInsertWebItemLater(tested, insertedItemTested, itermOrderToInsert);
        
        assertEquals(insertedItemExpected.getOrder(), insertedItemTested.getOrder());
        assertEquals(expected, tested);
        assertNotEquals(notModified, tested);
    }
    
    @Test
    public void testReorderToInsertWebItemLater_wrongLowBoundary() {
        List<OrderableWebItem> tested = produceOrderableWebItems();
        List<OrderableWebItem> notModified = produceOrderableWebItems();
        List<OrderableWebItem> expected = produceOrderableWebItems();
        int itermOrderToInsert = -2;
        PersistableWebPage insertedItemTested = new PersistableWebPage();
        PersistableWebPage insertedItemExpected = new PersistableWebPage();
        
        insertedItemExpected.setOrder(0);
        expected.get(0).setOrder(1);
        expected.get(1).setOrder(2);
        expected.get(2).setOrder(3);
        expected.get(3).setOrder(4);
        expected.get(4).setOrder(5);
        expected.get(5).setOrder(6);
        
        orderer.reorderToInsertWebItemLater(tested, insertedItemTested, itermOrderToInsert);
        
        assertEquals(insertedItemExpected.getOrder(), insertedItemTested.getOrder());
        assertEquals(expected, tested);
        assertNotEquals(notModified, tested);
    }
    
    @Test
    public void testReorderToInsertWebItemLater_wrongHighBoundary() {
        List<OrderableWebItem> tested = produceOrderableWebItems();
        List<OrderableWebItem> notModified = produceOrderableWebItems();
        List<OrderableWebItem> expected = produceOrderableWebItems();
        int itermOrderToInsert = 1000;
        PersistableWebPage insertedItemTested = new PersistableWebPage();
        PersistableWebPage insertedItemExpected = new PersistableWebPage();
        
        insertedItemExpected.setOrder(6);
        
        orderer.reorderToInsertWebItemLater(tested, insertedItemTested, itermOrderToInsert);
        
        assertEquals(insertedItemExpected.getOrder(), insertedItemTested.getOrder());
        assertEquals(expected, tested);
        assertEquals(notModified, tested);
    }

}