package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.TaskDTO;
import dto.UserDTO;
import errorhandling.MissingInput;
import facades.TaskFacade;
import facades.UserFacade;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("tasks")
public class TaskResource {


    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final TaskFacade TASK_FACADE = TaskFacade.getTaskFacade(EMF);



    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addTask (String task) {
        TaskDTO taskDTO = GSON.fromJson(task,TaskDTO.class);
        TaskDTO taskAdded = TASK_FACADE.addTask(taskDTO);

        return GSON.toJson(taskAdded);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getTaskByCid(@PathParam("id") int id) throws MissingInput {
        List<TaskDTO> taskDTOS = TASK_FACADE.getTasksByCid(id);

        return GSON.toJson(taskDTOS);
    }
}
