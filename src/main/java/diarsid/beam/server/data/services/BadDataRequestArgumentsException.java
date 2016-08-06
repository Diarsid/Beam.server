/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.data.services;

/**
 *
 * @author Diarsid
 */
public class BadDataRequestArgumentsException extends RuntimeException {

    /**
     * Creates a new instance of <code>BadDataRequestArgumentsException</code> without detail
     * message.
     */
    public BadDataRequestArgumentsException() {
    }

    /**
     * Constructs an instance of <code>BadDataRequestArgumentsException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public BadDataRequestArgumentsException(String msg) {
        super(msg);
    }
}
