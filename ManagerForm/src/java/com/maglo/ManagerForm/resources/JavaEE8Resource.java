/*
 * Copyright 2022 Maglo Solutions Corp.
 */
package com.maglo.ManagerForm.resources;

import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author NDOZENG
 */

@Path("javaee8")
public class JavaEE8Resource {
    
    /**
     * JavaEE8Response : ping()
     * @return 
     */
    @GET
    public Response ping() {
        return Response
                .ok("ping")
                .build();
    }// fin de la méthode ping()
    
}// fin de la classe JavaEE8Resource
