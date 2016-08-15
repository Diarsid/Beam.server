/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.services.domain.exceptions;

import diarsid.beam.server.util.JsonErrorObject;

import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;

/**
 *
 * @author Diarsid
 */
public class UsersServiceUnknownLogicException 
        extends RuntimeException
        implements ConvertableToJsonErrorObject {

    private static final int HTTP_ERROR_STATUS_CODE; 
    static {
        HTTP_ERROR_STATUS_CODE = SC_INTERNAL_SERVER_ERROR;
    }
    
    /**
     * Constructs an instance of <code>UnknownUsersServiceLogicException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public UsersServiceUnknownLogicException(String msg) {
        super(msg);
    }
    
    @Override
    public JsonErrorObject convertToJsonErrorObject() {
        return new JsonErrorObject(HTTP_ERROR_STATUS_CODE, this.getMessage());
    }  
}
