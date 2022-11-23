package org.example.springmvc.controllers.mainpage;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserService;
import org.example.springmvc.session.AuthContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/allusers")
@RequiredArgsConstructor
@Log4j2
public class AllUsersController {
    private final UserService userService;
    private final AuthContext authContext;

    @GetMapping()
    public String getAllUsers(final ModelMap model,
                              final @RequestParam(name = "search", required = false) String searchPrefix) {
        log.info("Filter users with prefix: [{}]", searchPrefix);
        List<User> users = userService.getAllFilteredUsers(searchPrefix);
        model.addAttribute("users", users);
        model.addAttribute("currentUsername", authContext.getCurrentUsername());
        return "all_users";
    }
}
