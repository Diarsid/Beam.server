/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.presentation.web.services;

/**
 * Marker interface.
 * 
 * Jersey classes autodiscovering feature doesn't work well with Spring Boot 
 * because Jersey implementation is not able to scan fat jars. Being started from 
 * Spring Boot fat jar, Jersey cannot manage to find its components if they are 
 * declared via ResourceConfig.packages(String) or other Jersey scanning APIs.
 * 
 * The only way to get Jersey working inside of Spring Boot fat jar is to 
 * register all custom JAX-RS component classes (such as filters, exception
 * mappers, entity readers and writers, path resources etc.) manually using
 * ResourceConfig.register(Class).
 * 
 * In order to manually register JAX-RS components in Jersey ResourceConfig 
 * instance it is required to get all custom JAX-RS components classes declared 
 * and used by this application and inject them manually in ResourceConfig.
 * 
 * This interface is used to unify all custom JAX-RS component classes in 
 * order to retrieve them from Spring via ApplicationContext.getBeansOfType() 
 * using this interface class as an argument.
 * 
 * Thus, in order to be registered automatically, all JAX-RS components should 
 * implement this interface.
 * 
 * @author Diarsid
 */
public interface JAXRSAutoRegistrableComponent {    
}
