package com.bav.ordermanagementsystem.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.Client;
import com.bav.ordermanagementsystem.entity.Employee;
import com.bav.ordermanagementsystem.fragment.RegistrationFragment;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class RegistrationActivity extends AppCompatActivity{

    private Button btnRegistration;
    private ImageButton btnBack;

    private DatabaseClient databaseClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        databaseClient = DatabaseClient.getInstance(getApplicationContext());

        btnRegistration = findViewById(R.id.buttonRefistrationRegistrationPage);
        btnBack = findViewById(R.id.buttonBackRegistrationPage);

        btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

        btnRegistration.setOnClickListener(v -> {
            RegistrationFragment fragment = (RegistrationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container_view_registration);
            Client client = fragment.getClient();
            if (client != null){
                databaseClient.getAppDatabase().clientDao().getByLogin(client.getLogin())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableMaybeObserver<Client>() {
                            @Override
                            public void onSuccess(Client client) {
                                Toast.makeText(getApplicationContext(), R.string.errorLoginAlreadyUsingRegistrationPage, Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                databaseClient.getAppDatabase().employeeDao().getByLogin(client.getLogin())
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new DisposableMaybeObserver<Employee>() {
                                            @Override
                                            public void onSuccess(Employee employee) {
                                                Toast.makeText(getApplicationContext(), R.string.errorLoginAlreadyUsingRegistrationPage, Toast.LENGTH_LONG).show();
                                            }

                                            @Override
                                            public void onError(Throwable e) {

                                            }

                                            @Override
                                            public void onComplete() {
                                                Completable.fromAction(() -> DatabaseClient.getInstance(getApplicationContext()).getAppDatabase().clientDao().insert(client))
                                                        .subscribeOn(Schedulers.io())
                                                        .observeOn(AndroidSchedulers.mainThread())
                                                        .subscribe(new DisposableCompletableObserver() {
                                                            @Override
                                                            public void onComplete() {
                                                                Toast.makeText(getApplicationContext(), R.string.savedSuccessfullyRegistrationPage, Toast.LENGTH_SHORT).show();
                                                                Intent intent = new Intent(getApplication(), LoginActivity.class);
                                                                startActivity(intent);
                                                            }

                                                            @Override
                                                            public void onError(Throwable e) {
                                                                Toast.makeText(getApplicationContext(), R.string.errorSaveRegistrationPage, Toast.LENGTH_LONG).show();
                                                            }
                                                        });
                                            }
                                        });
                            }
                        });
            }
        });
    }
}
