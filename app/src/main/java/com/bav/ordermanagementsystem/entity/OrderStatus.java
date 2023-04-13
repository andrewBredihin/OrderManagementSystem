package com.bav.ordermanagementsystem.entity;

import com.bav.ordermanagementsystem.db.annotations.Column;
import com.bav.ordermanagementsystem.db.annotations.Entity;
import com.bav.ordermanagementsystem.db.annotations.GeneratedValue;
import com.bav.ordermanagementsystem.db.annotations.GenerationType;
import com.bav.ordermanagementsystem.db.annotations.Id;

@Entity
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
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
