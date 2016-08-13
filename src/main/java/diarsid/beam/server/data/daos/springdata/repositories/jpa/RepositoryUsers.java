/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.data.daos.springdata.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import diarsid.beam.server.data.entities.jpa.PersistableUser;

/**
 *
 * @author Diarsid
 */

@Repository
public interface RepositoryUsers extends JpaRepository<PersistableUser, Integer> {
    
    PersistableUser findByNicknameAndPassword(String nickname, String password);
    
    @Query(
            "SELECT COUNT(user) " +
            "FROM PersistableUser AS user " +
            "WHERE user.nickname = :nick ")
    int countByNickname(
            @Param("nick") String nickname);
}
