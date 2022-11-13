package org.example.springmvc.controllers.authentication;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.dto.CreateUserDto;
import org.example.springmvc.facades.AuthorizationFacade;
import org.example.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/signin")
@RequiredArgsConstructor
public class SignInController {
    @Autowired
    private final AuthorizationFacade authorizationFacade;

    @GetMapping
    public String getSignInPage() {
        return "sign_in";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String signIn(final CreateUserDto userDto, final HttpServletRequest request) {
        request.getSession().setAttribute("username", userDto.getUsername());
        return authorizationFacade.signIn(userDto.getUsername(), userDto.getPassword())
                ? "redirect:allusers"
                : "redirect:signin";
    }
}
