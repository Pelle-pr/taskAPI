package entities;

import dto.CustomerDTO;
import dto.EmployeeDTO;
import dto.TaskDTO;
import errorhandling.MissingInput;
import facades.CustomerFacade;
import facades.EmployeeFacade;
import facades.TaskFacade;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tester {
    public static void main(String[] args) throws MissingInput {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

        EntityManager em = emf.createEntityManager();

        Employee e1 = new Employee("Pelle Rasmussen", "pelle@mail.dk", 28261990);
        Employee e2 = new Employee("Johann Aakjaer", "johann@mail.dk", 20111081);
        Employee e3 = new Employee("Allan Hansen", "allan@mail.dk", 12345678);
        Employee e4 = new Employee("Kenneth Andersen", "kenneth@mail.dk", 94956732);
        EmployeeDTO employeeDTO = new EmployeeDTO(e4);


        Customer customer = new Customer("Henning Hansen", "Smedeløkken 11", 24550457, "Henning@mail.dk");
        Task task = new Task("Antenne Montering", "04-03-2025", "Skal have monteret 2 antenne til DK og SVT");

        Customer c1 = new Customer("Henning Hansen", "Smedeløkken 44, Allinge", 24582158, "Henning@mail.dk");
        Customer c2 = new Customer("Lone Madsen", "Tejnvej 22, Allinge", 52156652, "Lone@mail.dk");
        Customer c3 = new Customer("Albert Karlsen", "Rønnevej 66, Nexø", 24582158, "Albert@mail.dk");


        task.addEmployee(e1);
        task.addEmployee(e2);
        c1.addTask(task);

        TaskDTO taskDTO = new TaskDTO(task);

        EmployeeFacade employeeFacade = EmployeeFacade.getEmployeeFacade(emf);
        CustomerFacade customerFacade = CustomerFacade.getCustomerFacade(emf);
        TaskFacade taskFacade = TaskFacade.getTaskFacade(emf);

        taskFacade.addTask(taskDTO);


         //    employeeFacade.addEmployee(employeeDTO);
             //employeeFacade.deleteEmployee(4);
//        em.getTransaction().begin();
//        em.persist(cu1);
//        em.persist(cu2);
//        em.persist(cu3);
//        em.getTransaction().commit();
    }


}
