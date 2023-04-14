package com.bav.ordermanagementsystem.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "order_status")
public class OrderStatus {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    private String title;

    public OrderStatus(){}
    public OrderStatus(Long id, String title){
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
