/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.entities;

import diarsid.beam.server.data.entities.jpa.PersistableWebPage;

import java.util.Comparator;

/**
 *
 * @author Diarsid
 */
public class WebPageComparator implements Comparator<PersistableWebPage> {
    
    public WebPageComparator() {
    }
    
    @Override
    public int compare(PersistableWebPage one, PersistableWebPage other) {
//        if ( one.getDir().getDirOrder() == other.getDir().getDirOrder() ) {
//            if ( one.getPageOrder() < other.getPageOrder() ) {
//                return -1;
//            } else if (one.getPageOrder() > other.getPageOrder()) {
//                return 1;
//            } else {
//                return 0;
//            }
//        } else if ( one.getDir().getDirOrder() > other.getDir().getDirOrder() ) {
//            return 1;
//        } else {
//            return -1;
//        }        
        return 0;
    }
}
