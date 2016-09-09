/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.services.providers;

import diarsid.beam.server.domain.services.exceptions.BadDataRequestArgumentsException;
import diarsid.beam.server.domain.services.exceptions.NickNameIsNotFreeException;
import diarsid.beam.server.domain.services.exceptions.UserLoginInvalidException;
import diarsid.beam.server.domain.services.exceptions.UserRegistrationInvalidException;
import diarsid.beam.server.domain.services.exceptions.UsersServiceUnknownLogicException;
import diarsid.beam.server.domain.services.exceptions.WebObjectNameInvalidException;
import diarsid.beam.server.domain.services.exceptions.WebObjectUrlInvalidException;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

/**
 *
 * @author Diarsid
 */
public class DomainExceptionOnHttpStatusMapper {
    
    private DomainExceptionOnHttpStatusMapper() {
    }
    
    public static int defineHttpStatusOf(RuntimeException e) {
        if ( e instanceof BadDataRequestArgumentsException ) {
            return SC_BAD_REQUEST;
        }
        if ( e instanceof NickNameIsNotFreeException ) {
            return SC_BAD_REQUEST;
        }
        if ( e instanceof UserLoginInvalidException ) {
            return SC_UNAUTHORIZED;
        }
        if ( e instanceof UserRegistrationInvalidException ) {
            return SC_UNAUTHORIZED;
        }
        if ( e instanceof UsersServiceUnknownLogicException ) {
            return SC_INTERNAL_SERVER_ERROR;
        }
        if ( e instanceof WebObjectNameInvalidException ) {
            return SC_BAD_REQUEST;
        }
        if ( e instanceof WebObjectUrlInvalidException ) {
            return SC_BAD_REQUEST;
        }
        return SC_INTERNAL_SERVER_ERROR;
    }
}
