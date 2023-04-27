package com.bav.ordermanagementsystem.adapter.activeOrders;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.adapter.RecyclerViewHolderInterface;
import com.bav.ordermanagementsystem.databinding.FragmentActiveOrdersOrderBinding;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.Order;
import com.bav.ordermanagementsystem.entity.OrderStatus;
import com.bav.ordermanagementsystem.service.UserService;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class ActiveOrdersViewHolder extends RecyclerView.ViewHolder implements RecyclerViewHolderInterface {
    final Button itemTitle;
    final ImageButton backButton, completeButton;
    private View view;

    public ActiveOrdersViewHolder(View view) {
        super(view);
        this.view = view;
        itemTitle = view.findViewById(R.id.activeOrdersOrderButton);
        backButton = view.findViewById(R.id.activeOrdersOrderBack);
        completeButton = view.findViewById(R.id.activeOrdersOrderComplete);
    }

    @Override
    public void setOrder(Order item) {
        itemTitle.setText(item.getTitle());

        backButton.setOnClickListener(v -> {
            item.setStatus(OrderStatus.PENDING);
            item.setEmployee_id(null);
            Completable.fromAction(() -> DatabaseClient.getInstance(view.getContext()).getAppDatabase().orderDao().update(item))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableCompletableObserver() {
                        @Override
                        public void onComplete() {
                            Toast.makeText(view.getContext(), R.string.orderStartActive, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        });

        completeButton.setOnClickListener(v -> {
            item.setStatus(OrderStatus.COMPLETED);
            Completable.fromAction(() -> DatabaseClient.getInstance(view.getContext()).getAppDatabase().orderDao().update(item))
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
        });
    }
}
