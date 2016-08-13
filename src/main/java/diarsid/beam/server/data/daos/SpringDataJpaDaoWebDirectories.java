/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.daos;

import java.util.List;

import org.springframework.stereotype.Repository;

import diarsid.beam.server.data.daos.springdata.repositories.jpa.RepositoryWebDirectories;
import diarsid.beam.server.data.entities.jpa.PersistableWebDirectory;

@Repository
public class SpringDataJpaDaoWebDirectories implements DaoWebDirectories {
    
    private final RepositoryWebDirectories dirsRepo;
    
    public SpringDataJpaDaoWebDirectories(RepositoryWebDirectories dirsRepo) {
        this.dirsRepo = dirsRepo;
    }

    @Override
    public List<PersistableWebDirectory> getByUserId(int userId) {
        return this.dirsRepo.getByUserIdOrderByOrder(userId);
    }

    @Override
    public List<PersistableWebDirectory> getByPlaceAndUserId(String place, int userId) {
        return this.dirsRepo.getByPlaceAndUserIdOrderByOrder(place, userId);
    }

    @Override
    public PersistableWebDirectory findByNameAndPlaceAndUserId(String name, String place, int userId) {
        return this.dirsRepo.findByNameAndPlaceAndUserId(name, place, userId);
    }

    @Override
    public int countByUserAndPlace(String place, int userId) {
        return this.dirsRepo.countByUserAndPlace(place, userId);
    }
    
    @Override
    public List<PersistableWebDirectory> saveAndFlush(List<PersistableWebDirectory> dirs) {
        List<PersistableWebDirectory> saved = this.dirsRepo.save(dirs);
        this.dirsRepo.flush();
        return saved;
    }
    
    @Override
    public PersistableWebDirectory saveAndFlush(PersistableWebDirectory dir) {
        return this.dirsRepo.saveAndFlush(dir);
    }
    
    @Override
    public void deleteAndFlush(PersistableWebDirectory dir) {
        this.dirsRepo.delete(dir);
        this.dirsRepo.flush();        
    }
    
    @Override
    public boolean exists(int id) {
        return this.dirsRepo.exists(id);
    }
}
