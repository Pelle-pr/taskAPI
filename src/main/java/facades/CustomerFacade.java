package facades;

import dto.CustomerDTO;
import dto.EmployeeDTO;
import dto.TaskDTO;
import entities.Customer;
import entities.Employee;
import entities.Task;
import errorhandling.MissingInput;
import org.eclipse.persistence.platform.server.CustomServerPlatform;
import org.glassfish.jersey.internal.inject.Custom;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CustomerFacade {

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public CustomerDTO addCustomer(CustomerDTO c) throws MissingInput {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Customer> customerTypedQuery = em.createQuery("Select c from Customer c where c.address = :address AND c.phone = :phone", Customer.class);
        customerTypedQuery.setParameter("phone", c.getPhone()).setParameter("address", c.getAddress());

        if (customerTypedQuery.getResultList().size() > 0) {
            throw new MissingInput("Customer already exist!");
        } else {
            Customer customer = new Customer(c.getName(), c.getAddress(), c.getPhone(), c.getEmail());

            try {
                em.getTransaction().begin();
                em.persist(customer);
                em.getTransaction().commit();

                return new CustomerDTO(customer);
            } finally {
                em.close();
            }
        }


    }

    public List<CustomerDTO> getAllCustomers () {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Customer> typedQuery = em.createQuery("SELECT c from Customer c", Customer.class);
        List<Customer> customerList = typedQuery.getResultList();
        List<CustomerDTO> customerDTOS = new ArrayList<>();

        for (Customer c: customerList){
            customerDTOS.add(new CustomerDTO(c));
        }

        return customerDTOS;
    }

    public CustomerDTO getCustomerById (int id){
        EntityManager em = emf.createEntityManager();

        Customer customer = em.find(Customer.class, id);

        return new CustomerDTO(customer);
    }

    public CustomerDTO deleteCustomer (int id){
        EntityManager em = emf.createEntityManager();

        Customer customer = em.find(Customer.class, id);

        try {
            em.getTransaction().begin();
            em.remove(customer);
            em.getTransaction().commit();
        }finally {
            em.close();
        }
        return new CustomerDTO(customer);
    }

}
