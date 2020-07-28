package controller;

import core.service.IProductSerivce;
import service.impl.ProductService;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/public")

public class PublicEndpoints {


    IProductSerivce productService ;
    public PublicEndpoints(){
        productService = new ProductService();
    }
    @GET
    @Path("/getProducts")
    @Produces("application/json")
    public Response getAuthProducts(){
        return Response.ok(productService.getAuthorizedProducts()
        ).build();
    }

}
