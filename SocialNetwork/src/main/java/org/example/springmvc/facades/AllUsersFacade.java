package org.example.springmvc.facades;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserService;
import org.example.springmvc.session.AuthContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class AllUsersFacade {
    private final UserService userService;
    private final AuthContext authContext;

    public Map<String, Object> filterUsers(final String searchPrefix, final Integer pageNumber, final Integer pageSize) {
        int usersQty;
        if (searchPrefix != null) {
            usersQty = userService.countFilteredUsers(searchPrefix);
        } else {
            usersQty = userService.countAllUsers();
        }
        int pageNumbersQty = Math.round((float) usersQty / pageSize);
        List<User> users = userService.getFilteredUsers(searchPrefix, pageNumber, pageSize);
        Map<String, Object> attributesMap = new HashMap<>();
        attributesMap.put("users", users);
        attributesMap.put("currentUsername", authContext.getCurrentUsername());
        attributesMap.put("pageNumbersQty", pageNumbersQty);
        attributesMap.put("pageSize", pageSize);
        attributesMap.put("search", searchPrefix);
        return attributesMap;
    }
}
