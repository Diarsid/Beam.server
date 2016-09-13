/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.domain.services.users;

import org.springframework.transaction.annotation.Transactional;

import diarsid.beam.server.domain.entities.jpa.PersistableUser;
import diarsid.beam.server.presentation.web.json.dto.UserLoginRequestData;
import diarsid.beam.server.presentation.web.json.dto.UserRegistrationRequestData;
import diarsid.beam.server.presentation.web.services.auth.UserRole;

/**
 *
 * @author Diarsid
 */

@Transactional
public interface UsersService {
    
    void checkUser(int userId);
    
    UserRole getRoleOf(int userId);
    
    PersistableUser findBy(int userId);
    
    PersistableUser findBy(UserLoginRequestData login);
    
    PersistableUser createUserBy(UserRegistrationRequestData registration); 
    
    boolean isNicknameFree(String nick);
}
