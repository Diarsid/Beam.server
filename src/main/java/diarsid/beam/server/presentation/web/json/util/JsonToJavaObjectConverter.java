/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.presentation.web.json.util;

import java.io.IOException;
import java.io.InputStream;

import com.google.gson.JsonObject;

/**
 *
 * @author Diarsid
 */
public interface JsonToJavaObjectConverter {

    JsonObject objectivize(String json);

    Object objectivize(InputStream source, Class clazz) throws IOException;
}
