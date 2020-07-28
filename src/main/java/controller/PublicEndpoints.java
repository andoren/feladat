package controller;

import core.service.IProductSerivce;
import service.dao.IProductDAO;
import service.impl.ProductService;


import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/public")
@RequestScoped
@Stateless
public class PublicEndpoints {


    @EJB
    IProductSerivce productService ;

    @GET
    @Path("/getProducts")
    @Produces("application/json")
    public Response getAuthProducts(){
        return Response.ok(productService.getAuthorizedProducts()
        ).build();
    }

}
