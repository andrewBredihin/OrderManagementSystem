package com.bav.ordermanagementsystem.service;

public interface UserDetailsService {

    UserDetails getUser(String login, String password);

    boolean saveUser(UserDetails user);

    boolean deleteUser(UserDetails user);

    boolean updateUser(UserDetails user);
}
