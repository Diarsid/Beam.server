/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.domain.services.validation;

import diarsid.beam.server.presentation.web.json.dto.UserLoginRequestData;
import diarsid.beam.server.presentation.web.json.dto.UserRegistrationRequestData;

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
