package com.bav.ordermanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.entity.Client;
import com.bav.ordermanagementsystem.entity.Employee;
import com.bav.ordermanagementsystem.service.UserDetails;
import com.bav.ordermanagementsystem.service.UserService;

import java.util.Map;

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

        login = findViewById(R.id.editTextLoginLoginPage);
        password = findViewById(R.id.editTextPasswordLoginPage);
        btnLogin = findViewById(R.id.buttonLoginLoginPage);
        btnRegistration = findViewById(R.id.buttonRegistrationLoginPage);
        icon = findViewById(R.id.imageViewLoginPage);
        title = findViewById(R.id.textViewTitleLoginPage);
        error = findViewById(R.id.textViewErrorLoginPage);

        btnLogin.setOnClickListener(v -> {
            UserDetails user = null;
            userService = UserService.getInstance(getApplication());
            Map<String, UserDetails> userMap = userService.getUser(login.getText().toString(), password.getText().toString());
            if (userMap == null){
                error.setVisibility(View.VISIBLE);
            }
            else if (userMap.get("client") != null){
                user = (Client)userMap.get("client");
            }
            else if (userMap.get("employee") != null){
                user = (Employee)userMap.get("employee");
            }

            if (user != null){
                userService.setUser(user);
                Intent intent = new Intent(getApplication(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnRegistration.setOnClickListener(v -> {
            Intent intent = new Intent(getApplication(), RegistrationActivity.class);
            startActivity(intent);
        });
    }
}
