package org.example.springmvc.controllers.restcontrollers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
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