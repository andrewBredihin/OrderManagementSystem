package com.bav.ordermanagementsystem.service;

import android.content.Context;

import com.bav.ordermanagementsystem.db.DatabaseClient;

public class UserService implements UserDetailsService {

    private Context mCtx;
    private static UserService mInstance;
    private DatabaseClient client;

    private UserDetails user;


    private UserService(Context mCtx) {
        this.mCtx = mCtx;
        client = DatabaseClient.getInstance(mCtx);
    }


    public static synchronized UserService getInstance(Context mCtx) {
        if (mInstance == null) {
            mInstance = new UserService(mCtx);
        }
        return mInstance;
    }

    @Override
    public UserDetails getUser(String login, String password) {
        //Прописать взаимодействие с БД
        return null;
    }

    @Override
    public boolean saveUser(UserDetails user) {
        //Прописать взаимодействие с БД
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

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }
}
