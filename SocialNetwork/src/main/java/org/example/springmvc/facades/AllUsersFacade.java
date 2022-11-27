package org.example.springmvc.facades;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class AllUsersFacade {
    private final UserService userService;

    public Map<String, Object> filterUsers(final String searchPrefix, final Integer pageNumber, final Integer pageSize) {
        long usersQty;
        if (searchPrefix != null) {
            usersQty = userService.countFilteredUsers(searchPrefix);
        } else {
            usersQty = userService.countAllUsers();
        }
        List<User> users = userService.getFilteredUsers(searchPrefix, pageNumber, pageSize);
        Map<String, Object> attributesMap = new HashMap<>();
        attributesMap.put("users", users);
        attributesMap.put("usersQty", usersQty);
        return attributesMap;
    }
}
