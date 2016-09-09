/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.domain.services.webobjects;

import java.util.List;

import diarsid.beam.server.domain.entities.WebObject;

/**
 *
 * @author Diarsid
 */
public class WebObjectsNamesIncrementor {
    
    private static final String REPLACABLE = "s";
    
    static {
    }
    
    private final ThreadLocal<Format> format;
    
    public WebObjectsNamesIncrementor() {
        this.format = ThreadLocal.withInitial(() -> Format.SPACE_AND_PARENTHESIS);
    }
    
    public void setIncrementFormat(Format format) {
        this.format.set(format);
    }
    
    public String incrementName(List<? extends WebObject> items, String nameToIncrement) {
        int nameIncrementCounter = 1;
        String incrementedName = nameToIncrement;
        while ( this.itemsContainName(items, incrementedName)) {
            nameIncrementCounter++;
            incrementedName = nameToIncrement + incrementFormat(nameIncrementCounter);
        }
        return incrementedName;
    }
    
    private boolean itemsContainName(List<? extends WebObject> items, String name) {
        return items.stream()
                .filter(item -> item.getName().equals(name))
                .findFirst()
                .isPresent();
    }
    
    private String incrementFormat(int incrementValue) {
        return this.format.get().stringFormat.replace(REPLACABLE, Integer.toString(incrementValue));
    }
    
    public enum Format {
        
        SPACE_AND_PARENTHESIS (" (" + REPLACABLE + ")"),
        UNDERSCORE_AND_PARENTHESIS ("_(" + REPLACABLE + ")"),
        SPACE_AND_BRACKETS (" [" + REPLACABLE + "]"),
        UNDERSCORE_AND_BRACKETS ("_[" + REPLACABLE + "]");
        
        private final String stringFormat;
        
        private Format(String format) {
            this.stringFormat = format;
        }
    }
}
