package com.bav.ordermanagementsystem.ui.myOrders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewStubProxy;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.activity.MainActivity;
import com.bav.ordermanagementsystem.adapter.myOrders.MyOrdersAdapter;
import com.bav.ordermanagementsystem.databinding.FragmentMyOrdersBinding;
import com.bav.ordermanagementsystem.databinding.OrderEmployeeTextviewBinding;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.Order;
import com.bav.ordermanagementsystem.entity.OrderStatus;
import com.bav.ordermanagementsystem.service.UserService;
import com.bav.ordermanagementsystem.ui.createOrder.CreateOrderFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

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

        createOrder = binding.buttonCreateOrders;
        createOrder.setOnClickListener(v -> {
            Navigation.findNavController(container).navigate(R.id.nav_select_orderItems);
        });

        DatabaseClient.getInstance(context).getAppDatabase().orderDao().getByClientIdAndStatus(userService.getUserDetails().getValue().getId(), OrderStatus.ACTIVE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(activeOrders -> {
                    DatabaseClient.getInstance(context).getAppDatabase().orderDao().getByClientIdAndStatus(userService.getUserDetails().getValue().getId(), OrderStatus.PENDING)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(pendingOrders -> {
                                activeOrders.addAll(pendingOrders);
                                MyOrdersAdapter adapter = new MyOrdersAdapter(context, activeOrders, R.layout.fragment_my_order);
                                binding.ordersList.setAdapter(adapter);
                            });
                });
        return root;
    }
}
