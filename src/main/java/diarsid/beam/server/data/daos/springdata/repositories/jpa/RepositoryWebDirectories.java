/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.data.daos.springdata.repositories.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import diarsid.beam.server.data.entities.jpa.PersistableWebDirectory;

/**
 *
 * @author Diarsid
 */

@Repository
public interface RepositoryWebDirectories 
        extends JpaRepository<PersistableWebDirectory, Integer> {
    
    List<PersistableWebDirectory> getByUserIdOrderByOrder(int userId);
    
    List<PersistableWebDirectory> getByPlaceAndUserIdOrderByOrder(String place, int userId);
    
    PersistableWebDirectory getByNameAndPlaceAndUserId(String name, String place, int userId);
    
    @Modifying
    @Query(
            "UPDATE PersistableWebDirectory AS dir " +
            "SET dir.name = :newName " +
            "WHERE dir.id = :dirId")
    int renameDirectory(
            @Param("newName") String newName, 
            @Param("dirId") int dirId);
}
