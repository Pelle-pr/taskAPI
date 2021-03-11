package dto;

import entities.Customer;

public class CustomerDTO {

    private int id;
    private String name;
    private String address;
    private int phone;
    private String email;


    public CustomerDTO(Customer c) {
        this.id = c.getId();
        this.name = c.getName();
        this.address = c.getAddress();
        this.phone = c.getPhone();
        this.email = c.getEmail();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
