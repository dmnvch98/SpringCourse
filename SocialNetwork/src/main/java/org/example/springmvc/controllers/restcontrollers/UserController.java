package org.example.springmvc.controllers.restcontrollers;

import lombok.RequiredArgsConstructor;
import org.example.springmvc.converter.UserConverter;
import org.example.springmvc.dto.UserDto;
import org.example.springmvc.dto.UserRestDto;
import org.example.springmvc.facades.AuthenticationFacade;
import org.example.springmvc.model.User;
import org.example.springmvc.service.UserService;
import org.example.springmvc.validations.flags.Unique;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    public static final String DEFAULT_PAGE_SIZE = "5";
    public static final String DEFAULT_PAGE_NUMBER = "0";

    private final UserService userService;
    private final UserConverter userConverter;

    private final AuthenticationFacade authenticationFacade;

    @GetMapping("/{id}")
    public ResponseEntity<UserRestDto> getUser(@PathVariable @NumberFormat final long id) {
        if (userService.isExist(id)) {
            final User user = userService.getUser(id);
            return ResponseEntity.ok(userConverter.userToUserRestDto(user));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<List<UserRestDto>> getUsers(final @RequestParam(name = "search", required = false) String searchPrefix,
    final @RequestParam(name = "pageNumber", required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer pageNumber,
    final @RequestParam(name = "pageSize", required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize) {
        return ResponseEntity.ok(userService
                .getFilteredUsers(searchPrefix, pageNumber, pageSize).stream()
                .map(userConverter::userToUserRestDto)
                .toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRestDto> createUser(@Validated(Unique.class) @RequestBody final UserDto userDto) {
            if (authenticationFacade.signUp(
                    userDto.getUsername(),
                    userDto.getPassword(),
                    userDto.getRole().toUpperCase()
            )) {
                User currentUser = userService.getUser(userDto.getUsername());
                return ResponseEntity.ok(userConverter.userToUserRestDto(currentUser));
            } else {
                return null;
            }
       }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable long id) {
        if (userService.isExist(id)) {
            if (userService.deleteUserById(id) != null) {
                return ResponseEntity.ok().body("User deleted. User id: " + id);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.internalServerError().body("Some error occurred");
    }
}
