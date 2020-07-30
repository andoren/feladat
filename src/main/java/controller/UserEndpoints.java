package controller;

import core.exceptions.InvalidPassword;
import core.model.LoginData;
import core.model.User;
import core.service.IProductSerivce;
import core.service.IUserService;
import helper.JWTHelper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@RequestScoped
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
        User currentUser = service.logIn(data.getUsername(),data.getPassword());
        if(currentUser != null){

            return Response.ok(currentUser).build();

        }
        else {

            return Response.ok(new User()).build();
        }

    }
}
