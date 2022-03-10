package com.wtrungvu.learningspringboot.clientproxy;

import com.wtrungvu.learningspringboot.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
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
    User fetchUser(@PathParam("userUid") UUID userUid);

    @POST
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(APPLICATION_JSON)
    void insertNewUser(@RequestBody User user);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON_VALUE)
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    void updateUser(@RequestBody User user);

    @DELETE
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    @Path("{userUid}")
    void deleteUser(@PathParam("userUid") UUID userUid);
}
