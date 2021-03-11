package facades;

import dto.TaskDTO;
import dto.UserDTO;
import entities.*;
import errorhandling.MissingInput;
import security.errorhandling.AuthenticationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class TaskFacade {

    private static EntityManagerFactory emf;
    private static TaskFacade instance;

    private TaskFacade() {
    }

    public static TaskFacade getTaskFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TaskFacade();
        }
        return instance;
    }

   public TaskDTO addTask (TaskDTO taskDTO){

        EntityManager em = emf.createEntityManager();
        Task newTask = new Task(taskDTO.getTitle(), taskDTO.getDate(),taskDTO.getDescription());
        Customer customer = em.find(Customer.class,taskDTO.getCustomer().getId());

        customer.addTask(newTask);


        for (int i = 0; i < taskDTO.getEmployeeList().size(); i++) {
           Employee employee = em.find(Employee.class, taskDTO.getEmployeeList().get(i).getId());
           newTask.addEmployee(employee);
        }

        try {
            em.getTransaction().begin();
            em.persist(newTask);
            em.getTransaction().commit();
        }finally {
            em.close();
        }

        return new TaskDTO(newTask);


   }

   public List<TaskDTO> getTasksByCid (int id) throws MissingInput {

        EntityManager em = emf.createEntityManager();

        TypedQuery<Task> tasks = em.createQuery("select t from Task t where t.customer.id = :cid", Task.class);
        tasks.setParameter("cid", id);
        List<Task> taskList = tasks.getResultList();

        if (taskList.size() < 1 ){
            throw new MissingInput("No tasks found for this customer");
        }
        List<TaskDTO> taskDTOS = new ArrayList<>();

        for (Task t: taskList){
            taskDTOS.add(new TaskDTO(t));
        }

        return taskDTOS;
   }

}
