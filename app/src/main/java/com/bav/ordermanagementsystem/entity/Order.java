package com.bav.ordermanagementsystem.entity;

import com.bav.ordermanagementsystem.db.annotations.Column;
import com.bav.ordermanagementsystem.db.annotations.Entity;
import com.bav.ordermanagementsystem.db.annotations.GeneratedValue;
import com.bav.ordermanagementsystem.db.annotations.GenerationType;
import com.bav.ordermanagementsystem.db.annotations.Id;
import com.bav.ordermanagementsystem.db.annotations.JoinColumn;
import com.bav.ordermanagementsystem.db.annotations.ManyToOne;
import com.bav.ordermanagementsystem.db.annotations.OneToMany;

import java.sql.Date;
import java.util.Collection;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private OrderStatus status;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "date", nullable = false)
    private Date date;

    @OneToMany(mappedBy = "order")
    private Collection<OrderItem> items;

    public Order(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Collection<OrderItem> getItems() {
        return items;
    }

    public void setItems(Collection<OrderItem> items) {
        this.items = items;
    }
}
