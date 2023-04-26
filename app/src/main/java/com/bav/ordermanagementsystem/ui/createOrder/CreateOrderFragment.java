package com.bav.ordermanagementsystem.ui.createOrder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.activity.LoginActivity;
import com.bav.ordermanagementsystem.databinding.FragmentCreateOrderBinding;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.Order;
import com.bav.ordermanagementsystem.entity.OrderItem;
import com.bav.ordermanagementsystem.entity.OrderStatus;
import com.bav.ordermanagementsystem.service.UserService;
import com.bav.ordermanagementsystem.ui.myOrders.MyOrdersFragment;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class CreateOrderFragment extends Fragment {

    private FragmentCreateOrderBinding binding;
    private UserService userService;

    private TextInputEditText title, address;
    private Button createOrder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateOrderBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        userService = UserService.getInstance(getContext());

        title = binding.title;
        address = binding.address;

        createOrder = binding.buttonCreateOrder;
        createOrder.setOnClickListener(v -> {
            if (title.getText().toString().length() < 10){
                Toast.makeText(getContext(), R.string.errorCreateOrderTitle, Toast.LENGTH_SHORT).show();
                return;
            }
            else if (address.getText().toString().length() < 10){
                Toast.makeText(getContext(), R.string.errorCreateOrderAddress, Toast.LENGTH_SHORT).show();
                return;
            }

            List<OrderItem> items = new ArrayList<>();
            //Дописать получение items
            /*if (items.size() == 0){
                Toast.makeText(getContext(), R.string.orderItemWasNotSelected, Toast.LENGTH_SHORT).show();
                return;
            }*/
            double price = 0;
            for (OrderItem x : items){
                price += x.getPrice();
            }

            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());

            //Прописать валидацию
            Order order = new Order();
            order.setAddress(address.getText().toString());
            order.setTitle(title.getText().toString());
            order.setClient_id(userService.getUserDetails().getId());
            order.setPrice(price);
            order.setDate(dateFormat.format(currentDate));
            order.setItems(items);
            order.setStatus(OrderStatus.PENDING);

            Completable.fromAction(() -> DatabaseClient.getInstance(getContext()).getAppDatabase().orderDao().insert(order))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableCompletableObserver() {
                        @Override
                        public void onComplete() {
                            Toast.makeText(getContext(), R.string.orderCreated, Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(container).navigate(R.id.nav_my_orders);
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(getContext(), R.string.orderNotCreated, Toast.LENGTH_LONG).show();
                        }
                    });
        });

        return root;
    }
}
