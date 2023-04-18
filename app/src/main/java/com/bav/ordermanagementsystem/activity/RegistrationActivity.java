package com.bav.ordermanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.entity.Client;
import com.bav.ordermanagementsystem.service.UserService;

public class RegistrationActivity extends AppCompatActivity {

    private EditText login, password, confirmPassword, firstName, lastName, secondName, email, phone;
    private Button btnRegistration;
    private ImageButton btnBack;
    private TextView error;

    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        login = findViewById(R.id.editTextLoginRegistrationPage);
        password = findViewById(R.id.editTextPasswordRegistrationPage);
        confirmPassword = findViewById(R.id.editTextConfirmPasswordRegistrationPage);
        firstName = findViewById(R.id.editTextFirstNameRegistrationPage);
        lastName = findViewById(R.id.editTextLastNameRegistrationPage);
        secondName = findViewById(R.id.editTextSecondNameRegistrationPage);
        email = findViewById(R.id.editTextEmailRegistrationPage);
        phone = findViewById(R.id.editTextPhoneRegistrationPage);

        error = findViewById(R.id.textViewErrorRegistrationPage);

        btnRegistration = findViewById(R.id.buttonRefistrationRegistrationPage);
        btnBack = findViewById(R.id.buttonBackRegistrationPage);

        btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

        btnRegistration.setOnClickListener(v -> {
            //Добавить валидацию полей
            /*if (){
                error.setText(R.string.errorRegistrationPage);
                error.setVisibility(View.VISIBLE);
            }*/
            if (!password.getText().toString().equals(confirmPassword.getText().toString())){
                error.setText(R.string.errorPasswordRegistrationPage);
                error.setVisibility(View.VISIBLE);
            }
            else {
                userService = UserService.getInstance(getApplication());

                Client client = new Client();
                client.setFirstName(firstName.getText().toString());
                client.setLastName(lastName.getText().toString());
                client.setEmail(email.getText().toString());
                client.setPhone(Long.valueOf(phone.getText().toString()));
                client.setLogin(login.getText().toString());
                client.setPassword(password.getText().toString());

                userService.saveUser(client);
            }
        });
    }
}
