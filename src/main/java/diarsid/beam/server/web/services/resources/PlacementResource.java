/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diarsid.beam.server.web.services.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

/**
 *
 * @author Diarsid
 */

@Path("webpages/{placement: (webpanel|bookmarks)}")
public class PlacementResource {
    
    private static final Logger logger;
    
    static {
        logger = LoggerFactory.getLogger(PlacementResource.class.getSimpleName());
    }
    
    public PlacementResource() {
    }
    
    @GET
    @Produces(TEXT_PLAIN)
    public String getPlacementContent(@PathParam("placement") String placement) {
        logger.trace("[GET " + placement + "]");
        return "all things contained in " + placement;
    }
}
