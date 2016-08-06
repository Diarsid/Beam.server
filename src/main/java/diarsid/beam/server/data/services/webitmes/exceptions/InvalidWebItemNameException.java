/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.data.services.webitmes.exceptions;

/**
 *
 * @author Diarsid
 */
public class InvalidWebItemNameException extends RuntimeException {

    /**
     * Creates a new instance of <code>BadWebItemNameException</code> without detail message.
     */
    public InvalidWebItemNameException() {
    }

    /**
     * Constructs an instance of <code>BadWebItemNameException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public InvalidWebItemNameException(String msg) {
        super(msg);
    }
}
