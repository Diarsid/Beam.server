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
import com.google.gson.JsonObject;

import diarsid.beam.server.data.entities.ObjectData;
import diarsid.beam.server.web.services.auth.UserLoginRequestData;
import diarsid.beam.server.web.services.auth.UserRegistrationRequestData;

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
    
    public static UserRegistrationRequestData objectivizeUserRegistrationData(InputStream source) 
            throws IOException {
        return GSON.fromJson(new InputStreamReader(source), UserRegistrationRequestData.class);
    }
    
    public static UserLoginRequestData objectivizeUserLoginData(InputStream source) 
            throws IOException {
        return GSON.fromJson(new InputStreamReader(source), UserLoginRequestData.class);
    }
    
    public static JsonObject objectivize(String json) {
        return GSON.fromJson(json, JsonObject.class);
    }
}
