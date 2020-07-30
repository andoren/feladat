package controller;

import core.model.User;
import core.service.IProductSerivce;
import core.service.IUserService;

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

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthProducts(User user){
        User currentUser = service.logIn(user.getUsername(),user.getPassword());
        if(currentUser != null){
            return Response.ok(currentUser).build();

        }
        else {
            return Response.ok(user).build();
        }

    }
}
