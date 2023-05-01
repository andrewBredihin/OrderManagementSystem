package com.bav.ordermanagementsystem.ui.createOrder;

import androidx.lifecycle.ViewModel;

import com.bav.ordermanagementsystem.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

public class OrderGetItemsViewModel extends ViewModel {

    private List<OrderItem> items;

    public OrderGetItemsViewModel(){
        //написать свою реализацию
        items = new ArrayList<>();
    }

    public List<OrderItem> getItems(){
        return items;
    }
}
