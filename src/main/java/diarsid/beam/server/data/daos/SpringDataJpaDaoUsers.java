/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.daos;

import org.springframework.stereotype.Repository;

import diarsid.beam.server.data.daos.springdata.repositories.jpa.RepositoryUsers;
import diarsid.beam.server.domain.entities.jpa.PersistableUser;

@Repository
public class SpringDataJpaDaoUsers implements DaoUsers {
    
    private final RepositoryUsers usersRepo;
    
    public SpringDataJpaDaoUsers(RepositoryUsers repository) {
        this.usersRepo = repository;
    }

    @Override
    public PersistableUser getUserByNicknameAndPassword(String nickname, String password) {
        return this.usersRepo.findByNicknameAndPassword(nickname, password);
    }

    @Override
    public PersistableUser getUserById(int id) {
        return this.usersRepo.findOne(id);
    }

    @Override
    public boolean isNickNameFree(String nickname) {
        int countUsersWithGivenNick = this.usersRepo.countByNickname(nickname);
        return ( countUsersWithGivenNick == 0 );
    }

    @Override
    public PersistableUser addUser(PersistableUser user) {
        return this.usersRepo.saveAndFlush(user);
    }

    @Override
    public boolean existsBy(int id) {        
        return this.usersRepo.exists(id);
    }
}
