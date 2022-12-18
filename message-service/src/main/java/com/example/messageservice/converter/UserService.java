package com.example.messageservice.converter;

import com.example.messageservice.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface{
    @Override
    public long findIdByUser(User user) {
        return user.getId();
    }
}
