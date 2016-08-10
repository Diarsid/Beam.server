/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.data.services.webobjects.exceptions;

/**
 *
 * @author Diarsid
 */
public class InvalidWebObjectNameException extends RuntimeException {

    /**
     * Creates a new instance of <code>BadWebItemNameException</code> without detail message.
     */
    public InvalidWebObjectNameException() {
    }

    /**
     * Constructs an instance of <code>BadWebItemNameException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public InvalidWebObjectNameException(String msg) {
        super(msg);
    }
}
