package org.example.springmvc.controllers.restcontrollers;

import org.example.service.UserServiceTest;
import org.example.springmvc.config.security.jwt.Jwt;
import org.example.springmvc.config.security.service.AuthService;
import org.example.springmvc.converter.UserConverter;
import org.example.springmvc.facades.AuthenticationFacade;
import org.example.springmvc.service.UserService;
import org.example.springmvc.session.AuthContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@MockBeans({
        @MockBean(Jwt.class),
        @MockBean(AuthService.class),
        @MockBean(AuthContext.class),
        @MockBean(UserService.class),
        @MockBean(UserConverter.class),
        @MockBean(AuthenticationFacade.class)
})
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        final long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/users/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getUser() {
    }

    @Test
    void getUsers() {
    }

    @Test
    void createUser() {
    }

    @Test
    void deleteUser() {
    }
}