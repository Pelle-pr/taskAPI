package facades;

import dto.EmployeeDTO;
import dto.UserDTO;
import entities.Employee;
import entities.Role;
import entities.User;
import errorhandling.MissingInput;
import security.errorhandling.AuthenticationException;

import javax.enterprise.inject.Typed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFacade {

    private static EntityManagerFactory emf;
    private static EmployeeFacade instance;

    private EmployeeFacade() {
    }

    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    public EmployeeDTO addEmployee(EmployeeDTO dto) throws MissingInput {

        EntityManager em = emf.createEntityManager();

        Employee newEmployee = new Employee(dto.getName(), dto.getEmail(), dto.getPhone());

        checkIfEmailIsInUse(dto, em);

        try {
            em.getTransaction().begin();
            em.persist(newEmployee);
            em.getTransaction().commit();
        } finally {
            em.close();

        }
        return new EmployeeDTO(newEmployee);

    }

    public EmployeeDTO deleteEmployee(int id) throws MissingInput {

        EntityManager em = emf.createEntityManager();

        Employee employee = em.find(Employee.class, id);

        if (employee == null) {
            throw new MissingInput("No Employees with the provided ID");
        }

        try {
            em.getTransaction().begin();
            em.remove(employee);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new EmployeeDTO(employee);
    }

    public EmployeeDTO editEmployee(EmployeeDTO employeeDTO) {

        EntityManager em = emf.createEntityManager();

        Employee employee = em.find(Employee.class, employeeDTO.getId());

        employee.setName(employeeDTO.getName());
        employee.setEmail(employee.getEmail());
        employee.setPhone(employee.getPhone());

        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new EmployeeDTO(employee);

    }

    public EmployeeDTO getEmployeeById (int id) throws MissingInput {
        EntityManager em = emf.createEntityManager();

        Employee employee = em.find(Employee.class, id);

        if (employee == null){
            throw new MissingInput("No Employee found with the provided ID");
        }

        return new EmployeeDTO(employee);
    }

    public List<EmployeeDTO> getAllEmployees () {
        EntityManager em = emf.createEntityManager();

        TypedQuery<Employee> typedQuery = em.createQuery("SELECT e from Employee e", Employee.class);
        List<Employee> employeeList = typedQuery.getResultList();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for (Employee e: employeeList){
            employeeDTOS.add(new EmployeeDTO(e));
        }

        return employeeDTOS;
    }

    private void checkIfEmailIsInUse(EmployeeDTO dto, EntityManager em) throws MissingInput {

        TypedQuery<Employee> employeeTypedQuery = em.createQuery("SELECT e from Employee e where e.email = :email", Employee.class);
        employeeTypedQuery.setParameter("email", dto.getEmail());

        List<Employee> employeeList = employeeTypedQuery.getResultList();

        if (employeeList.size() > 0) {
            throw new MissingInput("That email is already in use!");
        }
    }

}
