package org.example.springmvc.controllers.mainpage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.facades.AllUsersFacade;
import org.example.springmvc.session.AuthContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/allusers")
@RequiredArgsConstructor
@Slf4j
public class AllUsersController {
    private final AllUsersFacade allUsersFacade;
    private final AuthContext authContext;

    @GetMapping()
    public String filterUsers(final ModelMap model,
                              final @RequestParam(name = "search", required = false) String searchPrefix,
                              final @RequestParam(name = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                              final @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        model.addAllAttributes(allUsersFacade.filterUsers(searchPrefix, pageNumber, pageSize));
        model.addAttribute("currentUsername", authContext.getCurrentUsername());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("search", searchPrefix);
        return "all_users";
    }
}
