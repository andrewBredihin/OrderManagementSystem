package com.bav.ordermanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.Client;
import com.bav.ordermanagementsystem.entity.Employee;
import com.bav.ordermanagementsystem.service.UserDetails;
import com.bav.ordermanagementsystem.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    private EditText login, password;
    private Button btnLogin, btnRegistration;
    private ImageView icon;
    private TextView title, error;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userService = UserService.getInstance(getApplicationContext());
        userService.setUserDetails(null);

        login = findViewById(R.id.editTextLoginLoginPage);
        password = findViewById(R.id.editTextPasswordLoginPage);
        btnLogin = findViewById(R.id.buttonLoginLoginPage);
        btnRegistration = findViewById(R.id.buttonRegistrationLoginPage);
        icon = findViewById(R.id.imageViewLoginPage);
        title = findViewById(R.id.textViewTitleLoginPage);
        error = findViewById(R.id.textViewErrorLoginPage);

        btnLogin.setOnClickListener(v -> {
            getClient();
        });

        btnRegistration.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), RegistrationActivity.class);
            startActivity(intent);
        });
    }

    private void getClient(){
        DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().clientDao().getByLoginAndPassword(login.getText().toString(), password.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableMaybeObserver<Client>() {
                    @Override
                    public void onSuccess(Client client) {
                        userService.setUserDetails(client);
                        Intent intent = new Intent(getApplication(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        getEmployee();
                    }
                });
    }
    private void getEmployee(){
        DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().employeeDao().getByLoginAndPassword(login.getText().toString(), password.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableMaybeObserver<Employee>() {
                    @Override
                    public void onSuccess(Employee employee) {
                        userService.setUserDetails(employee);
                        Intent intent = new Intent(getApplication(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getApplicationContext(), R.string.errorLoginPage, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
