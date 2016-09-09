/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.presentation.web.json.util;

/**
 *
 * @author Diarsid
 */
public interface JavaObjectToJsonConverter {

    byte[] jsonizeToBytes(Object o);

    String jsonizeToString(Object o);
}
