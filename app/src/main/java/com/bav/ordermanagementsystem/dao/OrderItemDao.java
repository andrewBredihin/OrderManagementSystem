package com.bav.ordermanagementsystem.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.bav.ordermanagementsystem.entity.OrderItem;

@Dao
public interface OrderItemDao {

    @Insert
    void insert(OrderItem orderItem);

    @Delete
    void delete(OrderItem orderItem);

    @Update
    void update(OrderItem orderItem);
}
