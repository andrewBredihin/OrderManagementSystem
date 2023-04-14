package com.bav.ordermanagementsystem.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bav.ordermanagementsystem.interfaces.UserDetails;

@Entity
public class Employee implements UserDetails {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String firstName;

    private String lastName;

    private Long phone;

    private String role;

    private String login;

    private String password;

    public Employee(){}
    public Employee(String role){
        this.role = role;
    }


    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
