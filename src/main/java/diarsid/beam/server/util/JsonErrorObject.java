/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.util;

/**
 *
 * @author Diarsid
 */
public class JsonErrorObject {
    
    private final int statusCode;
    private final String message;
    
    public JsonErrorObject(int status, String message) {
        this.statusCode = status;
        this.message = message;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getMessage() {
        return this.message;
    }
}
