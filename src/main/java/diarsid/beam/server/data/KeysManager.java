/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.impl.crypto.MacProvider;

import diarsid.beam.server.data.entities.KeyIdPair;
import diarsid.beam.server.util.RandomStringGenerator;
import diarsid.beam.server.data.daos.DaoKeys;

/**
 *
 * @author Diarsid
 */

@Component
public class KeysManager {
    
    private final static Logger logger;
    static {
        logger = LoggerFactory.getLogger(KeysManager.class);
    }
    
    private final DaoKeys keysDao;
    private final Random random;
    private final RandomStringGenerator stringGenerator;
    private final List<String> keyIds;
    private final Map<String, KeyIdPair> keys;
    private final int keysQty;
    private final int minIdLength;
    private final int maxIdLength;
    
    public KeysManager(DaoKeys keysDao, RandomStringGenerator stringGenerator, int keysQty) {
        this.keysDao = keysDao;
        this.stringGenerator = stringGenerator;
        this.keysQty = keysQty;
        this.keys = new HashMap<>();
        this.keyIds = new ArrayList<>();
        this.random = new Random();
        this.minIdLength = 15;
        this.maxIdLength = 25;
        logger.info("created.");
    }
    
    @PostConstruct
    public void processKeys() {
        logger.info("processing keys...");
        this.keys.putAll(this.keysDao.getKeys());
        if ( this.keys.isEmpty() ) {
            logger.info("creating keys...");
            this.generateAndSaveNewKeys(this.keysQty);
        } else if ( this.keys.size() < this.keysQty ) {
            logger.info("creating additional keys..."); 
            this.generateAndSaveNewKeys(this.keysQty - this.keys.size());
        }
        this.keys.putAll(this.keysDao.getKeys());
        
        this.keyIds.addAll(this.keys.keySet());
    } 

    private void generateAndSaveNewKeys(int qtyToGenerate) {
        Set<KeyIdPair> generatedKeys = new HashSet<>();
        for (int i = 0; i < qtyToGenerate; i++) {
            generatedKeys.add(new KeyIdPair(
                    this.stringGenerator.randomString(this.minIdLength, this.maxIdLength), 
                    MacProvider.generateKey()));
        }
        this.keysDao.persistKeys(generatedKeys);
    }
    
    public KeyIdPair getRandomKeyIdPair() {
        return this.keys.get(this.getRandomKeyId());
    }

    private String getRandomKeyId() {
        return this.keyIds.get(this.getRandomKeyNumber());
    }

    private int getRandomKeyNumber() {
        return this.random.nextInt(this.keyIds.size());
    }
    
    public Key getKeyById(String id) {
        return this.keys.get(id).getKey();
    }
}
