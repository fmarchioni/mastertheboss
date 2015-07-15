/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mastertheboss.jmsbrowser.rest;

import com.mastertheboss.jmsbrowser.EJBBrowser;
 
import com.mastertheboss.jmsbrowser.bean.MessageDTO;
import java.util.List;
import javax.inject.Inject;
 

 
import javax.ws.rs.*;

@Path("/list")
public class RESTService {

   @Inject EJBBrowser ejb;

    @GET
    @Produces("application/json")
    public List<MessageDTO> listMessages(@QueryParam("queue") String queue) {

        List<MessageDTO> list = ejb.browseMessage(queue);
        return list;
    }

    
}
