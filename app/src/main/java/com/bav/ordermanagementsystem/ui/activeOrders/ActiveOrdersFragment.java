package com.bav.ordermanagementsystem.ui.activeOrders;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.adapter.activeOrders.ActiveOrdersAdapter;
import com.bav.ordermanagementsystem.adapter.myOrders.MyOrdersAdapter;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.Order;
import com.bav.ordermanagementsystem.entity.OrderStatus;
import com.bav.ordermanagementsystem.service.UserService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class ActiveOrdersFragment extends Fragment {

    private View view;
    private UserService userService;

    public ActiveOrdersFragment(){
        super(R.layout.fragment_active_orders_main);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        Context context = getContext();
        userService = UserService.getInstance(context);

        RecyclerView recyclerView = view.findViewById(R.id.activeOrdersList);

        DatabaseClient.getInstance(context).getAppDatabase().orderDao().getByStatusId(OrderStatus.PENDING)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orders -> {
                    ActiveOrdersAdapter adapter;
                    adapter = new ActiveOrdersAdapter(context, orders, R.layout.fragment_active_orders_order);
                    recyclerView.setAdapter(adapter);
                });
    }
}
