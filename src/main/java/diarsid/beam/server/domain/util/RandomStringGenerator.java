/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.domain.util;

/**
 *
 * @author Diarsid
 */
public interface RandomStringGenerator {
    
    String randomString(int length);

    String randomString(int minLength, int maxLength);    
}
