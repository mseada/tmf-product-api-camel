package com.mseada.babysitter.model;

import java.time.LocalDate;
import java.util.List;

public class Customer {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String area;
    private int childrenCount;
    private List<String> childrenAges;
    private LocalDate registrationDate;
    private String status;

    public Customer() {}

    public Customer(String id, String name, String email, String phone, String address,
                    String area, int childrenCount, List<String> childrenAges,
                    LocalDate registrationDate, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.area = area;
        this.childrenCount = childrenCount;
        this.childrenAges = childrenAges;
        this.registrationDate = registrationDate;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public int getChildrenCount() { return childrenCount; }
    public void setChildrenCount(int childrenCount) { this.childrenCount = childrenCount; }

    public List<String> getChildrenAges() { return childrenAges; }
    public void setChildrenAges(List<String> childrenAges) { this.childrenAges = childrenAges; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
