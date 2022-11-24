package org.example.springmvc.controllers.authentication;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.dto.UserDto;
import org.example.springmvc.exceptions.InvalidUserDataException;
import org.example.springmvc.facades.AuthenticationFacade;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserService;
import org.example.springmvc.session.AuthContext;
import org.example.springmvc.validations.flags.Unique;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {
    private final AuthenticationFacade authorizationFacade;

    private final AuthContext authContext;

    private final UserService userService;
    @GetMapping
    public String getSignUpPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "sign_up";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String signUp(@Validated(Unique.class) final UserDto userDto,
                            BindingResult bindingResult) throws InvalidUserDataException {
        if (bindingResult.hasErrors()) {
            throw new InvalidUserDataException(bindingResult, "sign_up");
        } else {
            if (authorizationFacade.signUp(userDto.getUsername(), userDto.getPassword())) {
                User currentUser = userService.getUser(userDto.getUsername());
                authContext.setUser(currentUser);
                authContext.setCurrentUsername(currentUser.getUsername());
                authContext.setAuthorized(true);
                return "redirect:allusers";
            } else {
                return "redirect:signup";
            }
        }
    }
}
