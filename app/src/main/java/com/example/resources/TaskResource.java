package com.example.resources;

import java.util.List;
import java.util.Optional;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import com.example.dao.TaskDAO;
import com.example.dto.Task;

@Path("/task")
public class TaskResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {

        Optional<List<Task>> optionalTasks = new TaskDAO().getAllTasks();
        if (optionalTasks.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No tasks found")
                    .build();
        }

        List<Task> tasks = optionalTasks.get();
        return Response.ok(tasks).build();
    }
}
