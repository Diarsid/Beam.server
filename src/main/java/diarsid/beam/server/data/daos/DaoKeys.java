/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.data.daos;

import diarsid.beam.server.domain.entities.KeyIdPair;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author Diarsid
 */
public interface DaoKeys {
    
    Map<String, KeyIdPair> getKeys();
    
    void persistKeys(Set<KeyIdPair> keys);
}
