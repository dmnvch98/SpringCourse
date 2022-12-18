package com.example.messageservice.repository;

import com.example.messageservice.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface{
    @Override
    public long findIdByUser(User user) {
        return user.getId();
    }
}
