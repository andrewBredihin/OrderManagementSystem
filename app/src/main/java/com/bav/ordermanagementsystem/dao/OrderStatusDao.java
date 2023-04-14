package com.bav.ordermanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bav.ordermanagementsystem.entity.OrderStatus;

import java.util.List;

@Dao
public interface OrderStatusDao {

    @Query("SELECT * FROM order_status")
    List<OrderStatus> getAll();

    @Query("SELECT * FROM order_status WHERE id = :orderStatusId")
    OrderStatus getById(Long orderStatusId);

    @Query("SELECT id FROM order_status WHERE title = :title")
    Long getIdByTitle(String title);

    @Insert
    void insert(OrderStatus status);

    @Delete
    void delete(OrderStatus status);

    @Update
    void update(OrderStatus status);
}
