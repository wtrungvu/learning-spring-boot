package com.wtrungvu.learningspringboot.clientproxy;

import com.wtrungvu.learningspringboot.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public interface UserResourceV1 {
    @GET
    @Produces(APPLICATION_JSON)
    List<User> fetchUsers(@QueryParam("gender") String gender);

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{userUid}")
    Response fetchUser(@PathParam("userUid") UUID userUid);

    @POST
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(APPLICATION_JSON)
    Response insertNewUser(@RequestBody User user);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    Response updateUser(@RequestBody User user);

    @DELETE
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Path("{userUid}")
    Response deleteUser(@PathParam("userUid") UUID userUid);
}
