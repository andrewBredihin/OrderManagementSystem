package com.bav.ordermanagementsystem.adapter.employees;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.adapter.RecyclerViewHolderInterface;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.Employee;
import com.bav.ordermanagementsystem.entity.Order;
import com.bav.ordermanagementsystem.entity.OrderStatus;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class EmployeesViewHolder extends RecyclerView.ViewHolder {
    final Button btnEmployee;
    private View view;

    public EmployeesViewHolder(View view) {
        super(view);
        this.view = view;
        btnEmployee = view.findViewById(R.id.employeeName);
    }

    public void setEmployee(Employee employee) {
        btnEmployee.setText(employee.getFullName());
        btnEmployee.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putLong("id", employee.getId());
            Navigation.findNavController(view).navigate(R.id.nav_manager_user_info, bundle);
        });
    }
}
