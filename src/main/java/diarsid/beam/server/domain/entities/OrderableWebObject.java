/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.domain.entities;

/**
 *
 * @author Diarsid
 */
public interface OrderableWebObject extends WebObject, Comparable<OrderableWebObject> {
    
    int getOrder();
    
    void setOrder(int newOrder);
    
    @Override
    default int compareTo(OrderableWebObject item) {
        if ( this.getOrder() > item.getOrder() ) {
            return 1;
        } else if ( this.getOrder() < item.getOrder() ) {
            return -1;
        } else {
            return 0;
        }
    }
}
