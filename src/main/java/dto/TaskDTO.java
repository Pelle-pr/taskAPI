package dto;

import entities.Customer;
import entities.Employee;
import entities.Task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDTO {
    private int id;
    private String title;
    private String date;
    private String description;
    private List<EmployeeDTO> employeeList = new ArrayList<>();
    private CustomerDTO customer;

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.date = task.getDate();
        this.description = task.getDescription();
        this.customer = new CustomerDTO(task.getCustomer());
        for (int i = 0; i < task.getEmployeeList().size(); i++) {
            this.employeeList.add(new EmployeeDTO(task.getEmployeeList().get(i)));

        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<EmployeeDTO> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeDTO> employeeList) {
        this.employeeList = employeeList;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customerDTO) {
        this.customer = customer;
    }
}
