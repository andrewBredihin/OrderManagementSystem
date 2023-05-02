package com.bav.ordermanagementsystem.service;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bav.ordermanagementsystem.db.DatabaseClient;

import kotlin.Suppress;

public class UserService{

    private Context mCtx;
    private static UserService mInstance;

    private MutableLiveData<UserDetails> userDetails;


    private UserService(Context mCtx) {
        this.mCtx = mCtx;
        this.userDetails = new MutableLiveData<>();
    }


    public static synchronized UserService getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new UserService(mCtx);
        }
        return mInstance;
    }

    public LiveData<UserDetails> getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails user) {
        userDetails.setValue(user);
    }
}
