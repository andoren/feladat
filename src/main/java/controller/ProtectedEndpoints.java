package controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import core.exceptions.InvalidEmailException;
import core.exceptions.InvalidRealnameException;
import core.exceptions.InvalidUsernameException;
import core.model.Product;
import core.model.Role;
import core.model.User;
import core.service.IProductSerivce;
import core.service.IUserService;
import helper.IJWTHelper;
import helper.JWTHelper;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Path("/protected")
@ApplicationScoped
@Stateless
public class ProtectedEndpoints {

    @EJB
    IProductSerivce productService;
    @EJB
    IUserService userService;
    @EJB
    IJWTHelper JWTHelper;
    @POST
    @Path("/addproduct")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthProducts(@HeaderParam("Authorization") String token, Product product){
        Response.ResponseBuilder builder = null;

        try{
            User user = JWTHelper.decodeJWT(token.split(":")[1]);
            builder = Response.ok(productService.addProduct(product));

        }
        catch (JWTVerificationException e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "The token is invalid");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        catch (Exception e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidRealnameException e) {
            e.printStackTrace();
        } catch (InvalidUsernameException e) {
            e.printStackTrace();
        }
        return builder.build();
    }
    @GET
    @Path("/notauthproducts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotAuthProducts(@HeaderParam("Authorization") String token){
        Response.ResponseBuilder builder = null;
        try{
            User user = JWTHelper.decodeJWT(token.split(":")[1]);
            if(user.getRole() == Role.admin)builder = Response.ok(productService.getNotAuthorizedProducts());
            else{
                Map<String, String> responseObj = new HashMap<>();
                responseObj.put("error", "You are not administrator to see this page.");
                builder = Response.status(Response.Status.FORBIDDEN).entity(responseObj);
            }
        }
        catch (JWTVerificationException e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "The token is invalid");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        catch (Exception e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidRealnameException e) {
            e.printStackTrace();
        } catch (InvalidUsernameException e) {
            e.printStackTrace();
        }
        return builder.build();
    }
    @GET
    @Path("/getproductsbyuserid/{id:[0-9][0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsByUserId(@HeaderParam("Authorization") String token, @PathParam("id") int id){
        Response.ResponseBuilder builder = null;
        try{
            User user = JWTHelper.decodeJWT(token.split(":")[1]);
            if(user.getId() == id)builder = Response.ok(productService.getProductsByUserId(user));
            else{
                Map<String, String> responseObj = new HashMap<>();
                responseObj.put("error", "No no no! I'm watching you !;)");
                builder = Response.status(Response.Status.FORBIDDEN).entity(responseObj);
            }
        }
        catch (JWTVerificationException e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "The token is invalid");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        catch (Exception e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidRealnameException e) {
            e.printStackTrace();
        } catch (InvalidUsernameException e) {
            e.printStackTrace();
        }
        return builder.build();
    }
    @GET
    @Path("/getusers")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(@HeaderParam("Authorization") String token ){
        Response.ResponseBuilder builder = null;
        try{
            User user = JWTHelper.decodeJWT(token.split(":")[1]);
            if(user.getRole() == Role.admin)builder = Response.ok(userService.getAllUsers());
            else{
                Map<String, String> responseObj = new HashMap<>();
                responseObj.put("error", "You are not administrator to see this page.");
                builder = Response.status(Response.Status.FORBIDDEN).entity(responseObj);
            }
        }
        catch (JWTVerificationException e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "The token is invalid");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        catch (Exception e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidRealnameException e) {
            e.printStackTrace();
        } catch (InvalidUsernameException e) {
            e.printStackTrace();
        }
        return builder.build();

    }
    @POST
    @Path("/buyproduct")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response buyProduct(@HeaderParam("Authorization") String token, Product product){
        Response.ResponseBuilder builder = null;
        try{
            User user = JWTHelper.decodeJWT(token.split(":")[1]);
            product.setIsSold(true);
            Calendar today = Calendar.getInstance();
            product.setSold_date(today.getTime());
            if(user.getId() != product.getOwner().getId())builder = Response.ok(productService.buyProduct(product,user));
            else{
                Map<String, String> responseObj = new HashMap<>();
                responseObj.put("error", "You cannot buy your own product!");
                builder = Response.status(Response.Status.FORBIDDEN).entity(responseObj);
            }
        }
        catch (JWTVerificationException e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "The token is invalid");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        catch (Exception e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidRealnameException e) {
            e.printStackTrace();
        } catch (InvalidUsernameException e) {
            e.printStackTrace();
        }
        return builder.build();
    }
    @POST
    @Path("/authproduct")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authProduct(@HeaderParam("Authorization") String token, Product product){
        Response.ResponseBuilder builder = null;
        try{
            User user = JWTHelper.decodeJWT(token.split(":")[1]);
            if(user.getRole() == Role.admin)builder = Response.ok(productService.authProduct(product));
            else{
                Map<String, String> responseObj = new HashMap<>();
                responseObj.put("error", "You are not administrator to see this page.");
                builder = Response.status(Response.Status.FORBIDDEN).entity(responseObj);
            }
        }
        catch (JWTVerificationException e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "The token is invalid");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        catch (Exception e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidRealnameException e) {
            e.printStackTrace();
        } catch (InvalidUsernameException e) {
            e.printStackTrace();
        }
        return builder.build();
    }
    @POST
    @Path("/modifyuser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response modifyUser(@HeaderParam("Authorization") String token, User user){
        Response.ResponseBuilder builder = null;
        try{
            User getuser = JWTHelper.decodeJWT(token.split(":")[1]);
            if(getuser.getRole() == Role.admin)builder = Response.ok(Response.ok(userService.modifyUser(user)));
            else{
                Map<String, String> responseObj = new HashMap<>();
                responseObj.put("error", "You are not an administrator.");
                builder = Response.status(Response.Status.FORBIDDEN).entity(responseObj);
            }
        }
        catch (JWTVerificationException e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "The token is invalid");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        catch (Exception e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidRealnameException e) {
            e.printStackTrace();
        } catch (InvalidUsernameException e) {
            e.printStackTrace();
        }
        return builder.build();
    }
    @DELETE
    @Path("deleteuser/{id:[0-9][0-9]*}")
    public Response deleteUser(@HeaderParam("Authorization") String token,@PathParam("id") int id){
        Response.ResponseBuilder builder = null;
        try{
            User getuser = JWTHelper.decodeJWT(token.split(":")[1]);
            if(getuser.getRole() == Role.admin)builder = Response.ok(userService.deleteUserById(id));
            else{
                Map<String, String> responseObj = new HashMap<>();
                responseObj.put("error", "You are not an administrator.");
                builder = Response.status(Response.Status.FORBIDDEN).entity(responseObj);
            }
        }
        catch (JWTVerificationException e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "The token is invalid");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        catch (Exception e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidRealnameException e) {
            e.printStackTrace();
        } catch (InvalidUsernameException e) {
            e.printStackTrace();
        }
        return builder.build();
    }
    @GET
    @Path("getuserbyid/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@HeaderParam("Authorization") String token, @PathParam("id") int id){
        Response.ResponseBuilder builder = null;
        try{
            User getuser = JWTHelper.decodeJWT(token.split(":")[1]);
            if(getuser.getRole() == Role.admin)builder = Response.ok(userService.getUserById(id));
            else{
                Map<String, String> responseObj = new HashMap<>();
                responseObj.put("error", "You are not an administrator.");
                builder = Response.status(Response.Status.FORBIDDEN).entity(responseObj);
            }
        }
        catch (JWTVerificationException e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "The token is invalid");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        catch (Exception e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidRealnameException e) {
            e.printStackTrace();
        } catch (InvalidUsernameException e) {
            e.printStackTrace();
        }
        return builder.build();
    }

    @GET
    @Path("getuseraddresses")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAddresses(@HeaderParam("Authorization") String token){
        Response.ResponseBuilder builder = Response.status(Response.Status.BAD_REQUEST);
        try{
            User getuser = JWTHelper.decodeJWT(token.split(":")[1]);
            builder = Response.ok(userService.getUserAddresses(getuser.getId()));

        }
        catch (JWTVerificationException e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", "The token is invalid");
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        }
        catch (Exception e){
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidRealnameException e) {
            e.printStackTrace();
        } catch (InvalidUsernameException e) {
            e.printStackTrace();
        }
        return builder.build();
    }
}
