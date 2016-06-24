/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.entities;

/**
 *
 * @author Diarsid
 */
public class ObjectData {
    
    private final String data;
    private final int value;
    private final boolean status;
    
    public ObjectData(String data, int value, boolean status) {
        this.data = data;
        this.value = value;
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public int getValue() {
        return value;
    }

    public boolean isStatus() {
        return status;
    }
}
