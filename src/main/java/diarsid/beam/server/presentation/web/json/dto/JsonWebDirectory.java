/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.json.dto;

import java.util.List;

import diarsid.beam.server.domain.entities.jpa.PersistableWebDirectory;

import static java.util.stream.Collectors.toList;

/**
 *
 * @author Diarsid
 */
public class JsonWebDirectory {
    
    private final String name;
    private final int order;
    private final List<JsonWebPage> pages;
    
    public JsonWebDirectory(PersistableWebDirectory persistableDir) {
        this.name = persistableDir.getName();
        this.order = persistableDir.getOrder();
        this.pages = persistableDir.getPages().stream()
                .map(persistablePage -> new JsonWebPage(persistablePage))
                .collect(toList());
    }
}
