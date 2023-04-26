package com.bav.ordermanagementsystem.adapter.myOrders;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.adapter.RecyclerViewHolderInterface;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.Order;
import com.bav.ordermanagementsystem.entity.OrderStatus;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class MyOrdersViewHolder extends RecyclerView.ViewHolder implements RecyclerViewHolderInterface {
    final Button itemTitle;
    final ImageButton deleteButton;
    private View view;

    public MyOrdersViewHolder(View view) {
        super(view);
        this.view = view;
        itemTitle = view.findViewById(R.id.myOrdersOrderButton);
        deleteButton = view.findViewById(R.id.myOrdersOrderDelete);
    }

    @Override
    public void setOrder(Order item) {
        itemTitle.setText(item.getTitle());
        deleteButton.setOnClickListener(v -> {
            if (item.getStatus().equals(OrderStatus.PENDING)){
                Completable.fromAction(() -> DatabaseClient.getInstance(view.getContext()).getAppDatabase().orderDao().delete(item))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Toast.makeText(view.getContext(), R.string.orderCanceled, Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
            } else {
                Toast.makeText(view.getContext(), R.string.orderInExecution, Toast.LENGTH_SHORT).show();
            }
        });
    }
}