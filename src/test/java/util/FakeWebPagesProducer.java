/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.util.ArrayList;
import java.util.List;

import diarsid.beam.server.domain.entities.jpa.PersistableWebDirectory;
import diarsid.beam.server.domain.entities.jpa.PersistableWebPage;

/**
 *
 * @author Diarsid
 */
public class FakeWebPagesProducer {
    
    public final static String PAGE_NAME_TEMPLATE;
    public final static String URL_TEMPLATE;
    static {
        PAGE_NAME_TEMPLATE = "page_name_";
        URL_TEMPLATE = "http://_page_url_";
    }
    
    private FakeWebPagesProducer() {
    }
    
    public static List<PersistableWebPage> newFakePages(
            PersistableWebDirectory dir, int nameIncrementor, int qty) {
        List<PersistableWebPage> newPages = new ArrayList<>();
        PersistableWebPage newPage = null;
        for (int i = 0; i < qty; i++) {
            newPage = new PersistableWebPage();
            newPage.setOrder(i);
            newPage.setDir(dir);
            newPage.setName(PAGE_NAME_TEMPLATE + nameIncrementor);
            newPage.setUrl(URL_TEMPLATE + nameIncrementor);
            nameIncrementor++;
            newPages.add(newPage);
        }
        return newPages;
    }
    
    public static PersistableWebPage newFakePage(
            PersistableWebDirectory dir, int nameIncrementor) {
        PersistableWebPage newPage = new PersistableWebPage();
        newPage.setOrder(0);
        newPage.setDir(dir);
        newPage.setName(PAGE_NAME_TEMPLATE + nameIncrementor);
        newPage.setUrl(URL_TEMPLATE + nameIncrementor);
        return newPage;
    }
}
