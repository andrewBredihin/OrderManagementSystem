package com.bav.ordermanagementsystem.ui.createOrder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bav.ordermanagementsystem.databinding.FragmentCreateOrderGetItemsBinding;
import com.bav.ordermanagementsystem.entity.OrderItem;

import java.util.List;

public class CreateOrderGetItemsFragment extends Fragment implements CreateOrderGetItems {

    private List<OrderItem> items;

    private FragmentCreateOrderGetItemsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateOrderGetItemsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public List<OrderItem> getItems() {
        return items;
    }
}
