package com.example.messageservice.repository;

import com.example.messageservice.model.User;

public interface UserServiceInterface {
    long findIdByUser(User user);
}
