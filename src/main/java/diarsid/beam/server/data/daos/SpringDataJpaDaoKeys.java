/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import diarsid.beam.server.data.daos.springdata.repositories.jpa.RepositoryKeys;
import diarsid.beam.server.data.entities.KeyIdPair;
import diarsid.beam.server.data.entities.jpa.PersistableKey;

@Repository
public class SpringDataJpaDaoKeys implements DaoKeys {
    
    private static final Logger logger;
    static {
        logger = LoggerFactory.getLogger(SpringDataJpaDaoKeys.class);
    }
    
    private final RepositoryKeys keysRepo;
    
    public SpringDataJpaDaoKeys(RepositoryKeys keysRepo) {
        this.keysRepo = keysRepo;
    }

    @Override
    public Map<String, KeyIdPair> getKeys() {
        List<PersistableKey> keys = this.keysRepo.findAll();
        Map<String, KeyIdPair> keysIds = new HashMap<>();
        for (PersistableKey key : keys) {
            keysIds.put(key.getNaturalId(), key.constructCryptoKey());
        }
        logger.info("obtained " + keysIds.size() + " keys from database.");
        return keysIds;
    }

    @Override
    public void persistKeys(Set<KeyIdPair> keys) {
        List<PersistableKey> keysToPersist = keys.stream()
                .map(keyPair -> new PersistableKey(keyPair))
                .collect(Collectors.toList());
        keysToPersist = this.keysRepo.save(keysToPersist);        
        logger.info("saved " + keysToPersist.size() + " keys.");
    }
}
