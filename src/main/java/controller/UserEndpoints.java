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
import service.impl.InvalidLoginException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Path("/")
@ApplicationScoped
@Stateless
public class UserEndpoints {
    @EJB
    IUserService service ;

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(LoginData data){
        try{
            User currentUser = service.logIn(data.getUsername(),data.getPassword());
                return Response.ok(currentUser).build();
        }
        catch (InvalidPassword e) {
            Map<String,String> responseObj= new HashMap<>();
            responseObj.put("error",e.getMessage());
            return Response.status(Response.Status.FORBIDDEN).entity(responseObj).build();
        }
         catch (InvalidLoginException e) {
             Map<String,String> responseObj= new HashMap<>();
             responseObj.put("error",e.getMessage());
             return Response.status(Response.Status.FORBIDDEN).entity(responseObj).build();
        }catch (Exception e){
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

                return Response.ok(newUser).build();

            }

        catch (Exception e){
            e.printStackTrace();
            Map<String,String> responseObj= new HashMap<>();
            responseObj.put("error",e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseObj).build();
        } catch (InvalidEmailException e) {
            e.printStackTrace();
            Map<String,String> responseObj= new HashMap<>();
            responseObj.put("error",e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(responseObj).build();
        } catch (InvalidRealnameException ex) {
            ex.printStackTrace();
            Map<String,String> responseObj= new HashMap<>();
            responseObj.put("error",ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(responseObj).build();
        } catch (InvalidUsernameException ex2) {
            ex2.printStackTrace();
            Map<String,String> responseObj= new HashMap<>();
            responseObj.put("error",ex2.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(responseObj).build();
        }

    }
}
