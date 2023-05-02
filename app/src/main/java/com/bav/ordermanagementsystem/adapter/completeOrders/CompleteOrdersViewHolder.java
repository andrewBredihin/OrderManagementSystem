package com.bav.ordermanagementsystem.adapter.completeOrders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.adapter.RecyclerViewHolderInterface;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.Order;
import com.bav.ordermanagementsystem.entity.OrderStatus;
import com.bav.ordermanagementsystem.service.UserService;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class CompleteOrdersViewHolder extends RecyclerView.ViewHolder implements RecyclerViewHolderInterface {
    final TextView title, date;

    public CompleteOrdersViewHolder(View view) {
        super(view);
        title = view.findViewById(R.id.complete_order_title);
        date = view.findViewById(R.id.complete_order_date);
    }

    @Override
    public void setOrder(Order item) {
        title.setText(item.getTitle());
        date.setText(item.getDate());
    }
}
