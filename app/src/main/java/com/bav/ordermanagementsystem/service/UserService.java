package com.bav.ordermanagementsystem.service;

import android.content.Context;
import com.bav.ordermanagementsystem.db.DatabaseClient;

public class UserService{

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

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails user) {
        this.userDetails = user;
    }
}
