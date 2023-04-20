package com.bav.ordermanagementsystem.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.bav.ordermanagementsystem.R;
import com.bav.ordermanagementsystem.activity.LoginActivity;
import com.bav.ordermanagementsystem.db.DatabaseClient;
import com.bav.ordermanagementsystem.entity.Client;
import com.bav.ordermanagementsystem.entity.Employee;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class UserService implements UserDetailsService {

    private Context mCtx;
    private static UserService mInstance;
    private DatabaseClient databaseClient;

    private UserDetails userDetails;


    private UserService(Context mCtx) {
        this.mCtx = mCtx;
        databaseClient = DatabaseClient.getInstance(mCtx);
    }


    public static synchronized UserService getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new UserService(mCtx);
        }
        return mInstance;
    }

    @Override
    public Map<String, UserDetails> getUser(String login, String password) {
        Map<String, UserDetails> user = new HashMap<>();
        return  user;
    }

    @SuppressLint("CheckResult")
    @Override
    public boolean saveUser(UserDetails user) {
        //Toast.makeText(mCtx, user.toString(), Toast.LENGTH_SHORT).show();
        AtomicBoolean result = new AtomicBoolean(false);

        if (user.getClass().getName().equals(Client.class.getName())){
            Completable.fromAction(() -> databaseClient.getAppDatabase().clientDao().insert((Client)user))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> result.set(true),
                            throwable -> Toast.makeText(mCtx, throwable.getMessage(), Toast.LENGTH_LONG).show());
            if (result.get())
                return true;
        }
        else if (user.getClass().getName().equals(Employee.class.getName())){
            Completable.fromAction(() -> databaseClient.getAppDatabase().employeeDao().insert((Employee) user))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> result.set(true),
                            throwable -> Toast.makeText(mCtx, R.string.errorSaveRegistrationPage, Toast.LENGTH_LONG).show());
            if (result.get())
                return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(UserDetails user) {
        //Прописать взаимодействие с БД
        return false;
    }

    @Override
    public boolean updateUser(UserDetails user) {
        //Прописать взаимодействие с БД
        return false;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails user) {
        this.userDetails = user;
    }
}
