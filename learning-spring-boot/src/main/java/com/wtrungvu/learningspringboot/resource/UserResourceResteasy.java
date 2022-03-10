package com.wtrungvu.learningspringboot.resource;

import com.wtrungvu.learningspringboot.model.User;
import com.wtrungvu.learningspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Validated
@Component
@Path("api/v1/users")
public class UserResourceResteasy {

    private UserService userService;

    @Autowired
    public UserResourceResteasy(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces(APPLICATION_JSON)
    public List<User> fetchUsers(@QueryParam("gender") String gender) {
        return userService.getAllUsers(Optional.ofNullable(gender));
    }

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{userUid}")
    public User fetchUser(@PathParam("userUid") UUID userUid) {
        return userService
                .getUser(userUid)
                .orElseThrow(() -> new NotFoundException("User " + userUid + " not found"));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(APPLICATION_JSON)
    public void insertNewUser(@Valid User user) {
        userService.insertUser(user);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Path("{userUid}")
    public void deleteUser(@PathParam("userUid") UUID userUid) {
        userService.removeUser(userUid);
    }

    private Response getIntegerResponse(int result) {
        if (result == 1) {
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

}
