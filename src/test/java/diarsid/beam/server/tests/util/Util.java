/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.tests.util;

import java.util.ArrayList;
import java.util.List;

import diarsid.beam.server.data.entities.OrderableWebItem;
import diarsid.beam.server.data.entities.WebItem;
import diarsid.beam.server.data.entities.jpa.PersistableWebPage;

/**
 *
 * @author Diarsid
 */
public class Util {
    
    Util() {
    }
    
    public static WebItem namedWeItemStub(String name) {
        return new WebItem() {
            
            @Override
            public String getName() {
                return name;
            }

            @Override
            public void setName(String name) {
                throw new UnsupportedOperationException(); 
            }
        };
    }
    
    public static List<OrderableWebItem> produceOrderableWebItems() {
        List<OrderableWebItem> items = new ArrayList<>();
        items.add(new PersistableWebPage());
        items.add(new PersistableWebPage());
        items.add(new PersistableWebPage());
        items.add(new PersistableWebPage());
        items.add(new PersistableWebPage());
        items.add(new PersistableWebPage());
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setOrder(i);
            items.get(i).setName("itemName:" + i+1);
        }
        return items;
    }
}
