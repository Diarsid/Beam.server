/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.data.daos.springdata.repositories.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import diarsid.beam.server.data.entities.jpa.PersistableUserDirs;

/**
 *
 * @author Diarsid
 */

@Repository
public interface RepositoryUserDirs extends JpaRepository<PersistableUserDirs, Integer> {
    
    PersistableUserDirs getById(int userId);
}
