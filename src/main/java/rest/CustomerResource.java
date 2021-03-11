package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CustomerDTO;
import dto.UserDTO;
import errorhandling.MissingInput;
import facades.CustomerFacade;
import facades.UserFacade;
import security.errorhandling.AuthenticationException;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("customers")
public class CustomerResource {


    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static final CustomerFacade CUSTOMER_FACADE = CustomerFacade.getCustomerFacade(EMF);



    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addCustomer(String customer) throws AuthenticationException, MissingInput {
        CustomerDTO customerDTO = GSON.fromJson(customer,CustomerDTO.class);
        CustomerDTO addedCustomer = CUSTOMER_FACADE.addCustomer(customerDTO);

        return GSON.toJson(addedCustomer);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllCustomers (){
        List<CustomerDTO> customerDTOS = CUSTOMER_FACADE.getAllCustomers();

        return GSON.toJson(customerDTOS);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String getCustomerById (@PathParam("id") int id){
        CustomerDTO customerDTO = CUSTOMER_FACADE.getCustomerById(id);

        return GSON.toJson(customerDTO);
    }


    @DELETE
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String deleteCustomerById (@PathParam("id") int id){
        CustomerDTO customerDTO = CUSTOMER_FACADE.deleteCustomer(id);

        return GSON.toJson(customerDTO);
    }


}
