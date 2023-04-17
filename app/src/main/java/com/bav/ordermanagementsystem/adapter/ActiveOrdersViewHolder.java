package com.bav.ordermanagementsystem.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.recyclerview.widget.RecyclerView;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.entity.Order;

public class ActiveOrdersViewHolder extends RecyclerView.ViewHolder implements RecyclerViewHolderInterface{
    final Button itemTitle;
    final ImageButton backButton, completeButton;

    public ActiveOrdersViewHolder(View view) {
        super(view);
        itemTitle = view.findViewById(R.id.activeOrdersOrderButton);
        backButton = view.findViewById(R.id.activeOrdersOrderBack);
        completeButton = view.findViewById(R.id.activeOrdersOrderComplete);
    }

    @Override
    public void setOrder(Order item) {
        itemTitle.setText(item.getTitle());
    }
}
