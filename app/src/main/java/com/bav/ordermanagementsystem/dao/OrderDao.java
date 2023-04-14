package com.bav.ordermanagementsystem.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bav.ordermanagementsystem.entity.Order;

import java.util.List;

@Dao
public interface OrderDao {

    @Query("SELECT * FROM `order`")
    LiveData<List<Order>> getAll();

    @Query("SELECT * FROM `order` WHERE client_id = :clientId")
    LiveData<List<Order>> getByClientId(Long clientId);

    @Query("SELECT * FROM `order` WHERE employee_id = :employeeId")
    LiveData<List<Order>> getByEmployeeId(Long employeeId);

    @Query("SELECT * FROM `order` WHERE status_id = :statusId")
    LiveData<List<Order>> getByStatusId(Long statusId);

    @Query("SELECT * FROM `order` WHERE employee_id = :employeeId AND status_id = :statusId")
    LiveData<List<Order>> getByEmployeeIdAndStatusId(Long employeeId, Long statusId);


    @Insert
    void insert(Order order);

    @Delete
    void delete(Order order);

    @Update
    void update(Order order);
}
