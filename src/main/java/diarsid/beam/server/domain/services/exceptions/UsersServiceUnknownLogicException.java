/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.domain.services.exceptions;

/**
 *
 * @author Diarsid
 */
public class UsersServiceUnknownLogicException extends DomainRuntimeException {

    /**
     * Constructs an instance of <code>UnknownUsersServiceLogicException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public UsersServiceUnknownLogicException(String msg) {
        super(msg);
    }
}
