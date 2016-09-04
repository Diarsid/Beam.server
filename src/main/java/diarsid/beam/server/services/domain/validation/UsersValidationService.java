/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.services.domain.validation;

import diarsid.beam.server.services.web.dto.UserLoginRequestData;
import diarsid.beam.server.services.web.dto.UserRegistrationRequestData;

/**
 *
 * @author Diarsid
 */
public interface UsersValidationService {

    ValidationResult validateEmail(String email);

    ValidationResult validateLoginInfo(UserLoginRequestData login);

    ValidationResult validateName(String name);

    ValidationResult validateNick(String nick);

    ValidationResult validatePassword(String pass);

    ValidationResult validateRegistrationInfo(UserRegistrationRequestData registration);    
}
