package com.bav.ordermanagementsystem.entity;

import android.os.Parcelable;

import java.io.Serializable;

public interface OrderItem extends Serializable {

    double getPrice();

    Long getId();

    String getTitle();
}
