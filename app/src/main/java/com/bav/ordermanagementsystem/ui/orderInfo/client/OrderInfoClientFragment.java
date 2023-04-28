package com.bav.ordermanagementsystem.ui.orderInfo.client;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.adapter.myOrders.MyOrdersAdapter;
import com.bav.ordermanagementsystem.databinding.FragmentOrderInfoClientBinding;
import com.bav.ordermanagementsystem.databinding.OrderAddressTextviewBinding;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.Employee;
import com.bav.ordermanagementsystem.entity.Order;
import com.bav.ordermanagementsystem.service.UserService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class OrderInfoClientFragment extends Fragment {

    private FragmentOrderInfoClientBinding binding;
    private UserService userService;
    private Context context;

    private Button canceledOrder;

    @SuppressLint("CheckResult")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderInfoClientBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        this.context = getContext();
        userService = UserService.getInstance(context);
        canceledOrder = binding.orderInfoCanceled;

        DatabaseClient.getInstance(context).getAppDatabase().orderDao().getById(getArguments().getLong("orderId"))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(order -> {
                    binding.setOrder(order);
                    ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle(order.getTitle());
                });



        if (getArguments().getLong("employeeId") != 0){
            DatabaseClient.getInstance(context).getAppDatabase().employeeDao().getById(getArguments().getLong("employeeId"))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableMaybeObserver<Employee>() {
                        @Override
                        public void onSuccess(Employee employee) {
                            binding.orderInfoEmployee.setOnInflateListener(new ViewStub.OnInflateListener() {
                                @Override
                                public void onInflate(ViewStub viewStub, View view) {
                                    OrderAddressTextviewBinding binding = DataBindingUtil.bind(view);
                                    binding.setEmployee(employee);
                                }
                            });
                            if (!binding.orderInfoEmployee.isInflated()) {
                                binding.orderInfoEmployee.getViewStub().inflate();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

        return root;
    }
}
