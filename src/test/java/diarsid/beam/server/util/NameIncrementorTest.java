/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import diarsid.beam.server.data.entities.WebItem;

import static org.junit.Assert.assertEquals;

import static diarsid.beam.server.tests.util.Util.namedWeItemStub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Diarsid
 */
public class NameIncrementorTest {

    public NameIncrementorTest() {
    }

    /**
     * Test of setIncrementFormat method, of class NameIncrementor.
     */
    @Test
    public void testSetIncrementFormat() {
        NameIncrementor incrementor = new NameIncrementor();
        incrementor.setIncrementFormat(NameIncrementor.Format.UNDERSCORE_AND_BRACKETS);
        List<WebItem> items = new ArrayList<>();
        items.add(namedWeItemStub("name"));
        items.add(namedWeItemStub("name_[1]"));
        items.add(namedWeItemStub("anotherName"));
        items.add(namedWeItemStub("one more name"));
        
        String nameToIncrement = "name";
        String incrementedName = incrementor.incrementName(items, nameToIncrement);
        assertEquals("name_[2]", incrementedName);
    }

    /**
     * Test of incrementName method, of class NameIncrementor.
     */
    @Test
    public void testIncrementName() {
        NameIncrementor incrementor = new NameIncrementor();
        List<WebItem> items = new ArrayList<>();
        items.add(namedWeItemStub("name"));
        items.add(namedWeItemStub("name (1)"));
        items.add(namedWeItemStub("anotherName"));
        items.add(namedWeItemStub("one more name"));
        
        String nameToIncrement = "name";
        String incrementedName = incrementor.incrementName(items, nameToIncrement);
        assertEquals("name (2)", incrementedName);
    }
}