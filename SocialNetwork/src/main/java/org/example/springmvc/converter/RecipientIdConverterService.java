package org.example.springmvc.converter;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserService;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class RecipientIdConverterService implements RecipientIdConverter {
    private final UserService userService;
    @Override
    public User recipientIdToUser(long recipientId) {
        return userService.getUser(recipientId);
    }
}
