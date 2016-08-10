/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.data.daos;

import diarsid.beam.server.web.services.auth.UserLoginRequestData;
import diarsid.beam.server.data.entities.jpa.PersistableUser;

/**
 *
 * @author Diarsid
 */
public interface DaoUsers {
    
    PersistableUser getUserByNickAndPass(UserLoginRequestData login);
    
    PersistableUser getUserById(int id);
    
    public boolean isNickNameFree(String nickName);
    
    public int addUser(PersistableUser user);
}
