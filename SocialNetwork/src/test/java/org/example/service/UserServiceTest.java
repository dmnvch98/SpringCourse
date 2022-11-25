package org.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import org.example.springmvc.model.User;
import org.example.springmvc.passwordhashing.PasswordHasher;
import org.example.springmvc.repository.UserJpa;
import org.example.springmvc.service.UserService;
import org.junit.Ignore;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Ignore
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserJpa userJpaDao;

    @Mock
    private PasswordHasher passwordHasher;
    @InjectMocks
    private UserService sut;

    @Test
    public void shouldThrowExceptionWhenUserExists() {
        final String username = "any_name3";
        final String password = "any_password3";
        final String role = "any_role";
        final Date creationDate = new Date();

        given(userJpaDao.existsUserByUsername(username)).willReturn(true);
        final IOException actual = assertThrows(
                IOException.class, () -> sut.save(username, password, role, creationDate));
        assertThat(actual)
                .hasMessage("User already exists");
    }

    @Test
    public void shouldNotThrowExceptionWhenUserNotExists() {
        final String username = "any_name3";
        final String password = "any_password3";
        final String role = "any_role";
        final Date creationDate = new Date();
        given(userJpaDao.existsUserByUsername(username)).willReturn(false);
        assertDoesNotThrow(() -> sut.save(username, password, role, creationDate));
    }

    @Test
    public void shouldFilterUsers() {
        final String username = "any_name3";
        final String password = "any_password3";
        final String filterPrefix = "any";
        List<User> expectedList = List.of(new User(username, password));
        given(userJpaDao.findUsersByUsernameIsLike(filterPrefix)).willReturn(Optional.of(expectedList));
        List<User> list = sut.getAllFilteredUsers(filterPrefix);
        assertThat(list == expectedList);
    }
}
