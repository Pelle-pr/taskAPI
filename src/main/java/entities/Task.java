package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.crypto.Cipher;
import javax.persistence.*;

@Entity
public class Task implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String date;

    private String description;

    @ManyToMany
    private List<Employee> employeeList;

    @ManyToOne
    private Customer customer;

    public Task() {
    }

    public Task(String title, String date, String description) {
        this.title = title;
        this.date = date;
        this.description = description;
    }

    public void addEmployee (Employee e){
        if(this.employeeList == null){
            this.employeeList = new ArrayList<>();
        }
        this.employeeList.add(e);
        e.getTaskList().add(this);
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

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
