/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.start;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Diarsid
 */

@WebListener
public class BeamServerServletContextListener implements ServletContextListener {
    
    private final Logger logger;
    
    public BeamServerServletContextListener() {
        this.logger = LoggerFactory.getLogger(BeamServerServletContextListener.class.getSimpleName());
    }
    
    @Override
    public void contextInitialized(ServletContextEvent event) {
        logger.info("context initialized.");
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        logger.info("context destroyed.");
    }
}
