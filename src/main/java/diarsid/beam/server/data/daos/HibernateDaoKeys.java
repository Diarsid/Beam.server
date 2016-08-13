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

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import diarsid.beam.server.data.entities.KeyIdPair;
import diarsid.beam.server.data.entities.jpa.PersistableKey;

import static org.hibernate.criterion.CriteriaSpecification.DISTINCT_ROOT_ENTITY;

@Repository
public class HibernateDaoKeys implements DaoKeys {
    
    private static final Logger logger = LoggerFactory.getLogger(HibernateDaoKeys.class);
    
    private final SessionFactory sessionFactory;
    
    public HibernateDaoKeys(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        logger.info("created.");
    }

    @Override
    @Transactional
    public Map<String, KeyIdPair> getKeys() {
        Session session = sessionFactory.getCurrentSession();
        List<PersistableKey> keys = session
                .createCriteria(PersistableKey.class)
                .setResultTransformer(DISTINCT_ROOT_ENTITY)
                .list();
        Map<String, KeyIdPair> keysIds = new HashMap<>();
        for (PersistableKey key : keys) {
            keysIds.put(key.getNaturalId(), key.constructCryptoKey());
        }
        logger.info("obtained " + keysIds.size() + " keys from database.");
        return keysIds;
    }

    @Override
    @Transactional
    public void persistKeys(Set<KeyIdPair> keys) {
        Session session = sessionFactory.getCurrentSession();
        for (KeyIdPair keyPair : keys) {
            session.save(new PersistableKey(keyPair));
        }
        logger.info("saved " + keys.size() + " keys.");
    }
}
