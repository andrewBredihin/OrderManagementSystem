package com.bav.ordermanagementsystem.interfaces;

import java.io.Serializable;

public interface UserDetails extends Serializable {

    /*String CLIENT = "client";
    String EMPLOYEE = "employee";
    String ADMIN = "admin";*/

    String getLogin();

    String getPassword();

    String getRole();

}