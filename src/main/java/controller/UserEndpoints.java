package controller;

import core.exceptions.InvalidEmailException;
import core.exceptions.InvalidPassword;
import core.exceptions.InvalidRealnameException;
import core.exceptions.InvalidUsernameException;
import core.model.LoginData;
import core.model.User;
import core.service.IUserService;
import service.impl.InvalidLoginException;

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
    public Response Register(User user) throws InvalidPassword {
        System.out.println(user.getId());
        try{
            User newUser = new User(user);
            User currentUser = service.addUser(newUser);
            if(currentUser != null){

                return Response.ok(currentUser).build();

            }
        }
        catch (Exception e){
            return Response.ok(new User()).build();
        } catch (InvalidEmailException e) {
            e.printStackTrace();
        } catch (InvalidRealnameException ex) {
            ex.printStackTrace();
        } catch (InvalidUsernameException ex2) {
            ex2.printStackTrace();
        }
        return Response.ok(new User()).build();
    }
}
