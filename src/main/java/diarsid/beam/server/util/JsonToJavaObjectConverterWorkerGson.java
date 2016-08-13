/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 *
 * @author Diarsid
 */

@Component
public class JsonToJavaObjectConverterWorkerGson implements JsonToJavaObjectConverter {
    
    private final Gson gsonInstance;
    
    public JsonToJavaObjectConverterWorkerGson() {
        this.gsonInstance = new Gson();
    }
    
    @Override
    public Object objectivize(InputStream source, Class clazz) throws IOException {
        return gsonInstance.fromJson(new InputStreamReader(source), clazz);
    }
    
    @Override
    public JsonObject objectivize(String json) {
        return gsonInstance.fromJson(json, JsonObject.class);
    }
}
