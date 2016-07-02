/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import diarsid.beam.server.entities.User;

/**
 *
 * @author Diarsid
 */
public class MockUserStorage {
    
    private final static Logger LOGGER;
    static {
        LOGGER = LoggerFactory.getLogger(MockUserStorage.class);
    }
    
    private final Map<Long, User> users;
    
    public MockUserStorage() {
        this.users = new HashMap<>();
        LOGGER.info("created.");
    }
    
    public void addUser(User user) {
        this.users.put(user.getId(), user);
    }
    
    public User getUserByNickAndPass(String nickName, String password) {
        for (User user : users.values()) {
            if ( user.getNickName().equals(nickName) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    
    public boolean isNickNameFree(String nickName) {
        for (User user : users.values()) {
            if ( user.getNickName().equals(nickName) ) {
                return false;
            }
        }
        return true;
    }
}
