package com.bav.ordermanagementsystem.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Collection;
import java.util.List;

@Entity(tableName = "order", foreignKeys = {
        @ForeignKey(entity = Employee.class, parentColumns = "id", childColumns = "employee_id"),
        @ForeignKey(entity = Client.class, parentColumns = "id", childColumns = "client_id"),
        @ForeignKey(entity = OrderStatus.class, parentColumns = "id", childColumns = "status_id")
})
public class Order {

    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(index = true)
    private Long employee_id;

    @ColumnInfo(index = true)
    private Long client_id;

    @ColumnInfo(index = true)
    private Long status_id;

    @Ignore
    private List<OrderItem> items;

    private double price;

    private String address;

    private String title;

    private String date;


    public Order(){}

    public Order(String title){
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public Long getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Long status_id) {
        this.status_id = status_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Collection<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
