package controller;

import core.exceptions.InvalidEmailException;
import core.exceptions.InvalidPassword;
import core.exceptions.InvalidRealnameException;
import core.exceptions.InvalidUsernameException;
import core.model.LoginData;
import core.model.User;
import core.service.IProductSerivce;
import core.service.IUserService;
import helper.JWTHelper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@ApplicationScoped
@Stateless
public class UserEndpoints {
    @EJB
    IUserService service ;
    JWTHelper jwtHelper = new JWTHelper();
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(LoginData data) throws InvalidPassword {
        try{
            User currentUser = service.logIn(data.getUsername(),data.getPassword());
            if(currentUser != null){

                return Response.ok(currentUser).build();

            }
        }
        catch (Exception e){
            return Response.ok(new User()).build();
        }
        return Response.ok(new User()).build();
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
        } catch (InvalidRealnameException e) {
            e.printStackTrace();
        } catch (InvalidUsernameException e) {
            e.printStackTrace();
        }
        return Response.ok(new User()).build();
    }
}
