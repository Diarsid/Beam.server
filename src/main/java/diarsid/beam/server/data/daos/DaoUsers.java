/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.data.daos;

import diarsid.beam.server.domain.entities.jpa.PersistableUser;

/**
 *
 * @author Diarsid
 */
public interface DaoUsers {
    
    PersistableUser getUserByNicknameAndPassword(String nickname, String password);
    
    PersistableUser getUserById(int id);
    
    boolean isNickNameFree(String nickName);
    
    PersistableUser addUser(PersistableUser user);
    
    boolean existsBy(int id);
}
