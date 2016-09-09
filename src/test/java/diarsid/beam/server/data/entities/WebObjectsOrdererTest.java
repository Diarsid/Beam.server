/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.entities;

import diarsid.beam.server.domain.entities.OrderableWebObject;
import diarsid.beam.server.domain.services.webobjects.WebObjectsOrderer;

import java.util.List;

import org.junit.Test;

import diarsid.beam.server.domain.entities.jpa.PersistableWebPage;

import static java.util.Collections.sort;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import static util.Util.produceOrderableWebItems;

import static java.util.Collections.sort;
import static java.util.Collections.sort;
import static java.util.Collections.sort;
import static java.util.Collections.sort;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import static java.util.Collections.sort;
import static java.util.Collections.sort;
import static java.util.Collections.sort;


/**
 *
 * @author Diarsid
 */

public class WebObjectsOrdererTest {
    
    private final WebObjectsOrderer orderer;

    public WebObjectsOrdererTest() {
        orderer = new WebObjectsOrderer();
    }

    /**
     * Test of reorderWebItemsAccordingToNewOrder method, of class WebObjectsOrderer.
     */
    @Test
    public void testReorderWebItemsAccordingToNewOrder_straightOrder() {
        List<OrderableWebObject> tested = produceOrderableWebItems();
        List<OrderableWebObject> notModified = produceOrderableWebItems();
        List<OrderableWebObject> expected = produceOrderableWebItems();
        int itemOldOrder = 2;
        int itemNewOrder = 4;
        
        expected.get(2).setOrder(4);
        expected.get(3).setOrder(2);
        expected.get(4).setOrder(3);
        sort(expected);
        
        orderer.reorderWebItemsAccordingToNewOrder(tested, itemOldOrder, itemNewOrder);
        assertEquals(expected, tested);
        assertNotEquals(notModified, tested);
    }
    
    @Test
    public void testReorderWebItemsAccordingToNewOrder_straightOrder_wrongHighBoundary() {
        List<OrderableWebObject> tested = produceOrderableWebItems();
        List<OrderableWebObject> notModified = produceOrderableWebItems();
        List<OrderableWebObject> expected = produceOrderableWebItems();
        int itemOldOrder = 2;
        int itemNewOrder = 1000;
        
        expected.get(2).setOrder(5);
        expected.get(3).setOrder(2);
        expected.get(4).setOrder(3);
        expected.get(5).setOrder(4);
        sort(expected);
        
        orderer.reorderWebItemsAccordingToNewOrder(tested, itemOldOrder, itemNewOrder);
        assertEquals(expected, tested);
        assertNotEquals(notModified, tested);
    }
    
    @Test
    public void testReorderWebItemsAccordingToNewOrder_straightOrder_wrongLowBoundary() {
        List<OrderableWebObject> tested = produceOrderableWebItems();
        List<OrderableWebObject> notModified = produceOrderableWebItems();
        List<OrderableWebObject> expected = produceOrderableWebItems();
        int itemOldOrder = -1;
        int itemNewOrder = 4;
        
        expected.get(0).setOrder(4);
        expected.get(1).setOrder(0);
        expected.get(2).setOrder(1);
        expected.get(3).setOrder(2);
        expected.get(4).setOrder(3);
        sort(expected);
        
        orderer.reorderWebItemsAccordingToNewOrder(tested, itemOldOrder, itemNewOrder);
        assertEquals(expected, tested);
        assertNotEquals(notModified, tested);
    }
    
    @Test
    public void testReorderWebItemsAccordingToNewOrder_reverseOrder() {
        List<OrderableWebObject> tested = produceOrderableWebItems();
        List<OrderableWebObject> notModified = produceOrderableWebItems();
        List<OrderableWebObject> expected = produceOrderableWebItems();
        int itemOldOrder = 4;
        int itemNewOrder = 2;
        
        expected.get(4).setOrder(2);
        expected.get(2).setOrder(3);
        expected.get(3).setOrder(4);
        sort(expected);
        
        orderer.reorderWebItemsAccordingToNewOrder(tested, itemOldOrder, itemNewOrder);
        assertEquals(expected, tested);
        assertNotEquals(notModified, tested);
    }
    
    @Test
    public void testReorderWebItemsAccordingToNewOrder_reverseOrder_wrongHighBoundary() {
        List<OrderableWebObject> tested = produceOrderableWebItems();
        List<OrderableWebObject> notModified = produceOrderableWebItems();
        List<OrderableWebObject> expected = produceOrderableWebItems();
        int itemOldOrder = 200;
        int itemNewOrder = 2;
        
        expected.get(5).setOrder(2);
        expected.get(2).setOrder(3);
        expected.get(3).setOrder(4);
        expected.get(4).setOrder(5);
        sort(expected);
        
        orderer.reorderWebItemsAccordingToNewOrder(tested, itemOldOrder, itemNewOrder);
        assertEquals(expected, tested);
        assertNotEquals(notModified, tested);
    }
    
    @Test
    public void testReorderWebItemsAccordingToNewOrder_reverseOrder_wrongLowBoundary() {
        List<OrderableWebObject> tested = produceOrderableWebItems();
        List<OrderableWebObject> notModified = produceOrderableWebItems();
        List<OrderableWebObject> expected = produceOrderableWebItems();
        int itemOldOrder = 4;
        int itemNewOrder = -1;
        
        expected.get(4).setOrder(0);
        expected.get(0).setOrder(1);
        expected.get(1).setOrder(2);
        expected.get(2).setOrder(3);
        expected.get(3).setOrder(4);
        sort(expected);
        
        orderer.reorderWebItemsAccordingToNewOrder(tested, itemOldOrder, itemNewOrder);
        assertEquals(expected, tested);
        assertNotEquals(notModified, tested);
    }

    /**
     * Test of reorderToExtractWebItemLater method, of class WebObjectsOrderer.
     */
    @Test
    public void testReorderToExtractWebItemLater() {
        List<OrderableWebObject> tested = produceOrderableWebItems();
        List<OrderableWebObject> notModified = produceOrderableWebItems();
        List<OrderableWebObject> expected = produceOrderableWebItems();
        int removedItemOrder = 3;
                
        expected.get(4).setOrder(3);
        expected.get(5).setOrder(4);        
        
        orderer.reorderToExtractWebItemLater(tested, removedItemOrder);
        
        assertEquals(expected, tested);
        assertNotEquals(notModified, tested);
    }
    
    @Test
    public void testReorderToExtractWebItemLater_wrongLowBoundary() {
        List<OrderableWebObject> tested = produceOrderableWebItems();
        List<OrderableWebObject> notModified = produceOrderableWebItems();
        List<OrderableWebObject> expected = produceOrderableWebItems();
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
        List<OrderableWebObject> tested = produceOrderableWebItems();
        List<OrderableWebObject> notModified = produceOrderableWebItems();
        List<OrderableWebObject> expected = produceOrderableWebItems();
        int removedItemOrder = 1000;
        
        orderer.reorderToExtractWebItemLater(tested, removedItemOrder);
        
        assertEquals(expected, tested);
        assertEquals(notModified, tested);
    }

    /**
     * Test of reorderToInsertWebItemLater method, of class WebObjectsOrderer.
     */
    @Test
    public void testReorderToInsertWebItemLater() {
        List<OrderableWebObject> tested = produceOrderableWebItems();
        List<OrderableWebObject> notModified = produceOrderableWebItems();
        List<OrderableWebObject> expected = produceOrderableWebItems();
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
        List<OrderableWebObject> tested = produceOrderableWebItems();
        List<OrderableWebObject> notModified = produceOrderableWebItems();
        List<OrderableWebObject> expected = produceOrderableWebItems();
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
        List<OrderableWebObject> tested = produceOrderableWebItems();
        List<OrderableWebObject> notModified = produceOrderableWebItems();
        List<OrderableWebObject> expected = produceOrderableWebItems();
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