package com.bav.ordermanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.bav.ordermanagementsystem.entity.Client;
import com.bav.ordermanagementsystem.entity.Employee;

@Dao
public interface UserDao {

    @Query("SELECT * FROM employee WHERE login = :login AND password = :password")
    Employee getEmployeeByLoginAndPassword(String login, String password);

    @Query("SELECT * FROM client WHERE login = :login AND password = :password")
    Client getClientByLoginAndPassword(String login, String password);

}
