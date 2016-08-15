/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.services.domain.users;

import diarsid.beam.server.data.entities.jpa.PersistableUser;
import diarsid.beam.server.services.web.auth.UserLoginRequestData;
import diarsid.beam.server.services.web.auth.UserRegistrationRequestData;
import diarsid.beam.server.services.web.auth.UserRole;

/**
 *
 * @author Diarsid
 */
public interface UsersService {
    
    void checkUser(int userId);
    
    UserRole getRoleOf(int userId);
    
    PersistableUser findBy(int userId);
    
    PersistableUser findBy(UserLoginRequestData login);
    
    PersistableUser createUserBy(UserRegistrationRequestData registration); 
    
    boolean isNicknameFree(String nick);
}
