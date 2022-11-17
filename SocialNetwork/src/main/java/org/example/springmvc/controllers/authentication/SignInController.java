package org.example.springmvc.controllers.authentication;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.dto.CreateUserDto;
import org.example.springmvc.exceptions.InvalidCredentialException;
import org.example.springmvc.exceptions.InvalidUserDataException;
import org.example.springmvc.facades.AuthenticationFacade;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserService;
import org.example.springmvc.session.AuthContext;
import org.example.springmvc.validations.flags.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/signin")
@RequiredArgsConstructor
public class SignInController {
    @Autowired
    private final AuthenticationFacade authorizationFacade;
    private final UserService userService;
    private final AuthContext authContext;

    @GetMapping
    public String getSignInPage() {
        return "sign_in";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String signIn(final @Validated(Credentials.class) CreateUserDto userDto, final BindingResult bindingResult, ModelMap model,
                            final HttpServletRequest request) throws InvalidUserDataException, InvalidCredentialException {
        if (bindingResult.hasErrors()) {
            throw new InvalidUserDataException(bindingResult, "sign_in");
        } else {
            if (authorizationFacade.signIn(userDto.getUsername(), userDto.getPassword())) {
                User currentUser = userService.getUser(userDto.getUsername());
                request.getSession().setAttribute("currentUser", currentUser);
                request.getSession().setAttribute("username", userDto.getUsername());
                authContext.setAuthorized(true);
                return "redirect:allusers";
            } else {
                return "redirect:signin";
            }
        }
    }
}
