package com.bav.ordermanagementsystem.ui.myOrders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.activity.MainActivity;
import com.bav.ordermanagementsystem.adapter.myOrders.MyOrdersAdapter;
import com.bav.ordermanagementsystem.databinding.FragmentMyOrdersBinding;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.Order;
import com.bav.ordermanagementsystem.entity.OrderStatus;
import com.bav.ordermanagementsystem.service.UserService;
import com.bav.ordermanagementsystem.ui.createOrder.CreateOrderFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MyOrdersFragment extends Fragment {

    private FragmentMyOrdersBinding binding;
    private UserService userService;

    private Button createOrder;

    @SuppressLint("CheckResult")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMyOrdersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Context context = getContext();

        userService = UserService.getInstance(context);

        RecyclerView recyclerView = binding.recyclerViewMyOrders;

        DatabaseClient.getInstance(context).getAppDatabase().orderDao().getByClientId(userService.getUserDetails().getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orders -> {
                    MyOrdersAdapter adapter;
                    adapter = new MyOrdersAdapter(context, orders, R.layout.fragment_my_order);
                    recyclerView.setAdapter(adapter);
                });

        createOrder = binding.buttonCreateOrders;
        createOrder.setOnClickListener(v -> {
            Navigation.findNavController(container).navigate(R.id.nav_create_order);
        });

        return root;
    }
}
