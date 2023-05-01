package com.bav.ordermanagementsystem.ui.createOrder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bav.ordermanagementsystem.databinding.FragmentCreateOrderGetItemsBinding;
import com.bav.ordermanagementsystem.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderGetItemsFragment extends Fragment {

    private List<OrderItem> items;

    private FragmentCreateOrderGetItemsBinding binding;
    private OrderGetItemsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(OrderGetItemsViewModel.class);
        binding = FragmentCreateOrderGetItemsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
}
