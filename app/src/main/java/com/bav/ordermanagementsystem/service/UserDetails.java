package com.bav.ordermanagementsystem.service;

import java.io.Serializable;

public interface UserDetails extends Serializable {

    String getLogin();

    String getPassword();

    String getRole();

    Long getId();

    String getFullName();

    String getAdditionalInformation();

}
