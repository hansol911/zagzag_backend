package com.jtrio.zagzag.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDTO createUser(@RequestBody @Valid UserCommand.CreateUser user) {
        return userService.createUser(user);
    }

    @PutMapping(value = "/{id}")
    public UserDTO updateUser(@RequestBody UserCommand.UpdateUser user, @PathVariable Long id) {
        return userService.updateUser(user, id);
    }
}