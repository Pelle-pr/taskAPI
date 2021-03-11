package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CustomerDTO;
import dto.EmployeeDTO;
import errorhandling.MissingInput;
import facades.CustomerFacade;
import facades.EmployeeFacade;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("employees")
public class EmployeeResource {


    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final EmployeeFacade EMPLOYEE_FACADE = EmployeeFacade.getEmployeeFacade(EMF);



    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addEmployee(String employee) throws AuthenticationException, MissingInput {
        EmployeeDTO employeeDTO = GSON.fromJson(employee,EmployeeDTO.class);
        EmployeeDTO addedEmployee = EMPLOYEE_FACADE.addEmployee(employeeDTO);

        return GSON.toJson(addedEmployee);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllEmployees (){
        List<EmployeeDTO> employeeDTOS = EMPLOYEE_FACADE.getAllEmployees();

        return GSON.toJson(employeeDTOS);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getEmployeeById (@PathParam("id") int id) throws MissingInput {
        EmployeeDTO employeeDTO = EMPLOYEE_FACADE.getEmployeeById(id);

        return GSON.toJson(employeeDTO);
    }


    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String deleteEmployeeById (@PathParam("id") int id) throws MissingInput {
       EmployeeDTO employeeDTO = EMPLOYEE_FACADE.deleteEmployee(id);

        return GSON.toJson(employeeDTO);
    }


}
