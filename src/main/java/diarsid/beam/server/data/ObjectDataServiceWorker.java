/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.data;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import diarsid.beam.server.data.entities.ObjectData;

/**
 *
 * @author Diarsid
 */

@Service
public class ObjectDataServiceWorker {
    
    private final static Logger LOGGER;
    static {
        LOGGER = LoggerFactory.getLogger(ObjectDataServiceWorker.class);
    }
    
    private final Random random;
    private int counter;    
    
    public ObjectDataServiceWorker() {
        LOGGER.info("ObjectDataProvider created.");
        this.counter = 0;
        this.random = new Random();
    }
    
    public ObjectData newObjectData() {
        return new ObjectData("object #" + this.increase(), this.increase(), (this.increase()%2 == 0));
    }
    
    private int increase() {
        this.counter = counter + this.random.nextInt(10);
        return this.counter;
    }
}
