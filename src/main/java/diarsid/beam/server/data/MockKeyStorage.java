/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.impl.crypto.MacProvider;

/**
 *
 * @author Diarsid
 */
public class MockKeyStorage {
    
    private final static Logger LOGGER;
    static {
        LOGGER = LoggerFactory.getLogger(MockKeyStorage.class);
    }
    
    private final Random random;
    private final List<String> keyIds;
    private final Map<String, KeyIdPair> keys;
    
    public MockKeyStorage() {
        this.keys = new HashMap<>();
        this.keyIds = new ArrayList<>();
        this.random = new Random();
        generateKeyIds();
        generateKeys();
        LOGGER.info("created.");
    }
    
    private void generateKeyIds() {
        this.keyIds.add("QedREfdsa");
        this.keyIds.add("fE14gfDSd");
        this.keyIds.add("Ajds2LSLD");
        this.keyIds.add("5kSk8Skfd");
        this.keyIds.add("OSldfkm562");
        this.keyIds.add("KSskfl62n");
        this.keyIds.add("SldMV72L");
        this.keyIds.add("09SHnfdlk67");
        this.keyIds.add("Txkfdlls78");
    }
    
    private void generateKeys() {        
        Key generatedKey;
        for ( String keyId : this.keyIds ) {
            generatedKey = MacProvider.generateKey();
            this.keys.put(keyId, new KeyIdPair(keyId, generatedKey));
        }
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
