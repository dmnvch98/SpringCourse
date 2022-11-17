package org.example.springmvc.controllers.authentication;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.dto.CreateUserDto;
import org.example.springmvc.exceptions.InvalidUserDataException;
import org.example.springmvc.facades.AuthenticationFacade;
import org.example.springmvc.session.AuthContext;
import org.example.springmvc.validations.flags.Unique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Validated
@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {
    @Autowired
    private final AuthenticationFacade authorizationFacade;

    private final AuthContext authContext;

    @GetMapping
    public String getSignUpPage() {
        return "sign_up";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String signIn(final HttpServletRequest request,
                            @Validated(Unique.class) final CreateUserDto userDto, BindingResult bindingResult) throws InvalidUserDataException {
        if (bindingResult.hasErrors()) {
            throw new InvalidUserDataException(bindingResult, "sign_up");
        } else {
            request.getSession().setAttribute("username", userDto.getUsername());
            authContext.setAuthorized(true);
            return authorizationFacade.signUp(userDto.getUsername(), userDto.getPassword())
                    ? "redirect:allusers"
                    : "redirect:signup";
        }
    }
}
