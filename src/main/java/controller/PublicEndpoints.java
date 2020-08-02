package controller;

import core.model.User;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/public")
@RequestScoped
@Stateless
public class PublicEndpoints {


    @EJB
    IProductSerivce productService ;

    @GET
    @Path("/getproducts")
    @Produces("application/json")
    public Response getAuthProducts(){
        return Response.ok(productService.getAuthorizedProducts()
        ).build();
    }
    @GET
    @Path("/getproductbyid/{id:[0-9][0-9]*}")
    @Produces("application/json")
    public Response getProductsByUserId(@PathParam("id") int id){

        return Response.ok(productService.getProductById(id)).build();
    }

}
