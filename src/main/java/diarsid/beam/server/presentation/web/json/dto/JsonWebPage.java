/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.json.dto;

import diarsid.beam.server.domain.entities.jpa.PersistableWebPage;

/**
 *
 * @author Diarsid
 */
public class JsonWebPage {
    
    private final String name;
    private final String url;
    
    public JsonWebPage(PersistableWebPage persistablePage) {
        this.name = persistablePage.getName();
        this.url = persistablePage.getUrl();
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }
}
