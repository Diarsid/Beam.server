/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.domain.services.jwtauth;

import java.security.Key;

import diarsid.beam.server.domain.entities.KeyIdPair;

/**
 *
 * @author Diarsid
 */
public interface KeysService {

    Key getKeyById(String id);

    KeyIdPair getRandomKeyIdPair();    
}
