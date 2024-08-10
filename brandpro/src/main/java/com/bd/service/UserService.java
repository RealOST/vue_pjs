package com.bd.service;

import com.bd.pojo.User;

public interface UserService {
    User login(User user);

    boolean register(User user);

    void add(User user);
}
