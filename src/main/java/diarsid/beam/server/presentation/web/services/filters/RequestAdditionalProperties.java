/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.presentation.web.services.filters;

import diarsid.beam.server.domain.services.jwtauth.JwtUserInfo;

/**
 *
 * @author Diarsid
 */
public enum RequestAdditionalProperties {
    
    USER ("user", JwtUserInfo.class);
    
    private final String propertyName;
    private final Class propertyObjectClass;
    
    private RequestAdditionalProperties(String name, Class clazz) {
        this.propertyName = name;
        this.propertyObjectClass = clazz;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public Class getPropertyObjectClass() {
        return this.propertyObjectClass;
    }
}
