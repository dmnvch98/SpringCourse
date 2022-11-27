package org.example.springmvc.controllers.authentication;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.dto.UserDto;
import org.example.springmvc.exceptions.InvalidCredentialException;
import org.example.springmvc.exceptions.InvalidUserDataException;
import org.example.springmvc.facades.AuthenticationFacade;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserService;
import org.example.springmvc.session.AuthContext;
import org.example.springmvc.validations.flags.Credentials;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/signin")
@RequiredArgsConstructor
public class SignInController {
    private final AuthenticationFacade authorizationFacade;
    private final UserService userService;
    private final AuthContext authContext;

    @GetMapping
    public String getSignInPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "sign_in";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String signIn(@Validated(Credentials.class) @ModelAttribute UserDto userDto) throws InvalidCredentialException {
        if (authorizationFacade.signIn(userDto.getUsername(), userDto.getPassword())) {
            User currentUser = userService.getUser(userDto.getUsername());
            authContext.setUser(currentUser);
            authContext.setCurrentUsername(currentUser.getUsername());
            authContext.setAuthorized(true);
            return "redirect:allusers";
        } else {
            return "redirect:signin";
        }
    }
}
