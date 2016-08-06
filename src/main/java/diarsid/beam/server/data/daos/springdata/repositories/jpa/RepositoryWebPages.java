/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.data.daos.springdata.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import diarsid.beam.server.data.entities.jpa.PersistableWebPage;

/**
 *
 * @author Diarsid
 */

@Repository
public interface RepositoryWebPages extends JpaRepository<PersistableWebPage, Integer> {
    
    @Modifying
    @Query(
            "UPDATE PersistableWebPage AS page " +
            "SET page.name = :newName " +
            "WHERE page.pageId = :id")
    public int renameWebPage(
            @Param("newName") String newName, 
            @Param("id") int pageId);
    
    @Modifying
    @Query(
            "UPDATE PersistableWebPage AS page " +
            "SET page.url = :newUrl " +
            "WHERE page.pageId = :id")
    public int changePageUrl(
            @Param("newUrl") String newUrl, 
            @Param("id") int pageId);
}
