/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.data.entities;

import java.util.List;

/**
 *
 * @author Diarsid
 */
public interface OrderableWebItemCollection {
    
    List<? extends OrderableWebItem> getItems();
}
