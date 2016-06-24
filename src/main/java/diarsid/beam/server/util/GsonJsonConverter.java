/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;

import diarsid.beam.server.entities.ObjectData;

/**
 *
 * @author Diarsid
 */
public class GsonJsonConverter {
    
    private final static Gson GSON; 
    static {
        GSON = new Gson();
    }
    
    private GsonJsonConverter() {
    }
    
    public static String jsonizeToString(Object o) {
        return GSON.toJson(o);
    }
    
    public static byte[] jsonizeToBytes(Object o) {
        return GSON.toJson(o).getBytes();
    }
    
    public static ObjectData objectivizeObjectData(InputStream source) throws IOException {
        return GSON.fromJson(new InputStreamReader(source), ObjectData.class);
    }
}