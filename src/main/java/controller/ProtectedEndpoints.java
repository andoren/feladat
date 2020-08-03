package controller;

import core.model.Product;
import core.model.User;
import core.service.IProductSerivce;
import core.service.IUserService;
import helper.JWTHelper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/protected")
@ApplicationScoped
@Stateless
public class ProtectedEndpoints {
    JWTHelper jwtHelper = new JWTHelper();
    @EJB
    IProductSerivce productService;
    @EJB
    IUserService userService;
    @POST
    @Path("/addproduct")
    @Produces("application/json")
    public Response getAuthProducts(Product product){
        return Response.ok(productService.addProduct(product)
        ).build();
    }
    @GET
    @Path("/notauthproducts")
    @Produces("application/json")
    public Response getNotAuthProducts(){
        return Response.ok(productService.getNotAuthorizedProducts()).build();
    }
    @GET
    @Path("/getproductsbyuserid/{id:[0-9][0-9]*}")
    @Produces("application/json")
    public Response getProductsByUserId(@PathParam("id") int id){
        User user = new User();
        user.setId(id); ;
        return Response.ok(productService.getProductsByUserId(user)).build();
    }
    @GET
    @Path("/getusers")
    @Produces("application/json")
    public Response getUsers(){
        return Response.ok(userService.getAllUsers()).build();
    }
}
