/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.util;

import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Component;

/**
 *
 * @author Diarsid
 */

@Component
public class RandomHexadecimalStringGenerator implements RandomStringGenerator {
    
    private final Random random;
    
    public RandomHexadecimalStringGenerator() {
        this.random = new Random();
    }
    
    @Override
    public String randomString(int length) {
        String random = this.newRandomHexadecimalString();
        if ( random.length() > length ) {
            return random.substring(0, length);
        } else {
            return this.cyclicGenerationFor(length);
        }
    }
    
    private String newRandomHexadecimalString() {
        return UUID.randomUUID().toString().replace("-", "");
    }
    
    private String cyclicGenerationFor(int length) {
        StringBuilder generated = new StringBuilder();
        while ( generated.length() < length ) {
            generated.append(this.newRandomHexadecimalString());
        }
        if ( generated.length() > length ) {
            return generated.toString().substring(0, length);
        } else {
            return generated.toString();
        }
    }

    @Override
    public String randomString(int minLength, int maxLength) {
        int randomLength = -1;
        while ( randomLength < minLength ) {            
            randomLength = random.nextInt(maxLength + 1);
        }
        return this.randomString(randomLength);
    }
}
