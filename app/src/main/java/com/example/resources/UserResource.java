package com.example.resources;

import java.util.List;
import java.util.Optional;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.example.dao.UserDAO;
import com.example.dto.User;

@Path("/users")
public class UserResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {

        Optional<List<User>> optionalUsers = new UserDAO().getAllUsers();
        if (optionalUsers.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No users found")
                    .build();
        }

        List<User> users = optionalUsers.get();
        return Response.ok(users).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {

        boolean isSuccess = false;

        if (!(user == null)) {
            isSuccess = new UserDAO().addUser(user);
        }
        if (isSuccess) {
            return Response.status(Response.Status.CREATED)
                    .entity(user)
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Failed to add user")
                    .build();
        }
    }

}
