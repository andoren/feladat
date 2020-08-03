package controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import core.exceptions.InvalidEmailException;
import core.exceptions.InvalidRealnameException;
import core.exceptions.InvalidUsernameException;
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
import java.util.Calendar;
import java.util.Date;

@Path("/protected")
@ApplicationScoped
@Stateless
public class ProtectedEndpoints {

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
    @POST
    @Path("/buyproduct")
    @Produces("application/json")
    public Response buyProduct(@HeaderParam("Authorization") String token, Product product){
        User user = new User();
        try {
            String validToken = token.split(":")[1];
            user = JWTHelper.decodeJWT(validToken);
        }catch (JWTVerificationException ex){
            return Response.ok("JWT token is invalid").status(403).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return Response.ok("\'error\': Ismeretlen szerver hiba").status(500).build();
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidRealnameException e) {
            e.printStackTrace();
        } catch (InvalidUsernameException e) {
            e.printStackTrace();
        }
        product.setIsSold(true);
        Calendar today = Calendar.getInstance();

        product.setSold_date(today.getTime());
        return Response.ok(productService.buyProduct(product,user)).build();
    }
    @POST
    @Path("/authproduct")
    @Produces("application/json")
    public Response authProduct(@HeaderParam("Authorization") String token, Product product){
        User user = new User();
        try {
            String validToken = token.split(":")[1];
            user = JWTHelper.decodeJWT(validToken);
        }catch (JWTVerificationException ex){
            return Response.ok("JWT token is invalid").status(403).build();
        }
        catch (Exception e){
            e.printStackTrace();
            return Response.ok("\'error\': Ismeretlen szerver hiba").status(500).build();
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidRealnameException e) {
            e.printStackTrace();
        } catch (InvalidUsernameException e) {
            e.printStackTrace();
        }

        return Response.ok(productService.authProduct(product)).build();
    }
}
