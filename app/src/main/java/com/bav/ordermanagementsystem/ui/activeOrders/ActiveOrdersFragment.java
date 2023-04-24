package com.bav.ordermanagementsystem.ui.activeOrders;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.adapter.activeOrders.ActiveOrdersAdapter;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.Order;
import com.bav.ordermanagementsystem.entity.OrderStatus;
import com.bav.ordermanagementsystem.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class ActiveOrdersFragment extends Fragment {

    private View view;

    public ActiveOrdersFragment(){
        super(R.layout.fragment_active_orders_main);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;

        //TEST
        RecyclerView recyclerView = view.findViewById(R.id.activeOrdersList);
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("Test 1"));
        orders.add(new Order("Test 2"));
        orders.add(new Order("Test 3"));
        orders.add(new Order("Test 4"));
        orders.add(new Order("Test 5"));
        ActiveOrdersAdapter adapter = new ActiveOrdersAdapter(view.getContext(), orders, R.layout.fragment_active_orders_order);
        recyclerView.setAdapter(adapter);
        //TEST

        /*GetOrdersTask task = new GetOrdersTask();
        task.execute();*/
    }

    class GetOrdersTask extends AsyncTask<Void, Void, List<Order>> {

        @Override
        protected List<Order> doInBackground(Void... voids) {
            Long employeeId = UserService.getInstance(getContext()).getUserDetails().getId();
            return DatabaseClient.getInstance(getContext()).getAppDatabase().orderDao().getByEmployeeIdAndStatusId(employeeId, OrderStatus.ACTIVE).getValue();
        }

        @Override
        protected void onPostExecute(List<Order> orders) {
            super.onPostExecute(orders);
            RecyclerView recyclerView = view.findViewById(R.id.activeOrdersList);
            ActiveOrdersAdapter adapter = new ActiveOrdersAdapter(view.getContext(), orders, R.layout.fragment_active_orders_order);
            recyclerView.setAdapter(adapter);
        }
    }
}
