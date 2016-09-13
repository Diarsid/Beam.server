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
public class WebPlacementNameInvalidException extends DomainRuntimeException {

    public WebPlacementNameInvalidException(String msg) {
        super(msg);
    }
}
