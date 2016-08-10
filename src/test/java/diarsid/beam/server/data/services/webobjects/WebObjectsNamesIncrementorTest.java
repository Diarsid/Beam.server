/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.services.webobjects;

import diarsid.beam.server.data.services.webobjects.WebObjectsNamesIncrementor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import static util.Util.namedWeItemStub;

import diarsid.beam.server.data.entities.WebObject;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Diarsid
 */
public class WebObjectsNamesIncrementorTest {

    public WebObjectsNamesIncrementorTest() {
    }

    /**
     * Test of setIncrementFormat method, of class WebObjectsNamesIncrementor.
     */
    @Test
    public void testSetIncrementFormat() {
        WebObjectsNamesIncrementor incrementor = new WebObjectsNamesIncrementor();
        incrementor.setIncrementFormat(WebObjectsNamesIncrementor.Format.UNDERSCORE_AND_BRACKETS);
        List<WebObject> items = new ArrayList<>();
        items.add(namedWeItemStub("name"));
        items.add(namedWeItemStub("name_[1]"));
        items.add(namedWeItemStub("anotherName"));
        items.add(namedWeItemStub("one more name"));
        
        String nameToIncrement = "name";
        String incrementedName = incrementor.incrementName(items, nameToIncrement);
        assertEquals("name_[2]", incrementedName);
    }

    /**
     * Test of incrementName method, of class WebObjectsNamesIncrementor.
     */
    @Test
    public void testIncrementName_secondIncrement() {
        WebObjectsNamesIncrementor incrementor = new WebObjectsNamesIncrementor();
        List<WebObject> items = new ArrayList<>();
        items.add(namedWeItemStub("name"));
        items.add(namedWeItemStub("name (2)"));
        items.add(namedWeItemStub("anotherName"));
        items.add(namedWeItemStub("one more name"));
        
        String nameToIncrement = "name";
        String incrementedName = incrementor.incrementName(items, nameToIncrement);
        assertEquals("name (3)", incrementedName);
    }
    
    @Test
    public void testIncrementName_firstIncrement() {
        WebObjectsNamesIncrementor incrementor = new WebObjectsNamesIncrementor();
        List<WebObject> items = new ArrayList<>();
        items.add(namedWeItemStub("name"));
        items.add(namedWeItemStub("anotherName"));
        items.add(namedWeItemStub("one more name"));
        
        String nameToIncrement = "name";
        String incrementedName = incrementor.incrementName(items, nameToIncrement);
        assertEquals("name (2)", incrementedName);
    }
}