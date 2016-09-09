/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diarsid.beam.server.domain.services.validation;

/**
 *
 * @author Diarsid
 */
public interface WebObjectsValidationService {

    ValidationResult isUrlValid(String url);

    ValidationResult isWebObjectNameValid(String webItemName);

    void validateUrl(String url);

    void validateWebObjectNames(String... webItemNames);    
}
