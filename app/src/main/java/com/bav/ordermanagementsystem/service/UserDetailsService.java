package com.bav.ordermanagementsystem.service;

import java.util.Map;

public interface UserDetailsService {

    Map<String, UserDetails> getUser(String login, String password);

    boolean saveUser(UserDetails user);

    boolean deleteUser(UserDetails user);

    boolean updateUser(UserDetails user);
}
