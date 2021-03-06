package controller;

import core.exceptions.InvalidEmailException;
import core.exceptions.InvalidPassword;
import core.exceptions.InvalidRealnameException;
import core.exceptions.InvalidUsernameException;
import core.model.Address;
import core.model.LoginData;
import core.model.User;
import core.model.UserAndAdress;
import core.service.IUserService;
import helper.IJWTHelper;
import service.impl.expections.InvalidLoginException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/")
@ApplicationScoped
@Stateless
public class UserEndpoints {
    @EJB
    private IUserService service ;
    @EJB
    private IJWTHelper JWTHelper;
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(LoginData data){
        try{
            User currentUser = service.logIn(data.getUsername(),data.getPassword());
            currentUser.setToken(JWTHelper.generateJWT(currentUser));
            return Response.ok(currentUser).build();
        }
        catch (InvalidPassword |InvalidLoginException  e) {
            Map<String,String> responseObj= new HashMap<>();
            responseObj.put("error",e.getMessage());
            return Response.status(Response.Status.FORBIDDEN).entity(responseObj).build();
        }
         catch (Exception e){
            e.printStackTrace();
            Map<String,String> responseObj= new HashMap<>();
            responseObj.put("error",e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(responseObj).build();
        }

    }
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Register(UserAndAdress ua) throws InvalidPassword {
        try{
                User newUser = new User(ua.getUser());
                newUser = service.addUser(newUser);
                for (Address address:ua.getAddresses()) {
                    address.setUserid(newUser);
                    service.addAddressToUser(address);
                }
                 newUser.setToken(JWTHelper.generateJWT(newUser));
                return Response.ok(newUser).build();
            }

        catch (Exception e){
            e.printStackTrace();
            Map<String,String> responseObj= new HashMap<>();
            responseObj.put("error",e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseObj).build();
        } catch (InvalidRealnameException | InvalidUsernameException|InvalidEmailException e) {
            e.printStackTrace();
            Map<String,String> responseObj= new HashMap<>();
            responseObj.put("error",e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(responseObj).build();
        }

    }
}
