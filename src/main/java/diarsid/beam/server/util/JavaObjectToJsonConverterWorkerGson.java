/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.util;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

/**
 *
 * @author Diarsid
 */

@Component
public class JavaObjectToJsonConverterWorkerGson implements JavaObjectToJsonConverter {
    
    private final Gson gsonInstance; 
    
    public JavaObjectToJsonConverterWorkerGson() {
        gsonInstance = new Gson();
    }
    
    @Override
    public String jsonizeToString(Object o) {
        return gsonInstance.toJson(o);
    }
    
    @Override
    public byte[] jsonizeToBytes(Object o) {
        return gsonInstance.toJson(o).getBytes();
    }
}
