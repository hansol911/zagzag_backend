package com.jtrio.zagzag.user;

import com.jtrio.zagzag.aop.NoLogging;
import com.jtrio.zagzag.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @NoLogging
    @PostMapping
    public UserDTO createUser(@RequestBody @Valid UserCommand.CreateUser command) {
        return userService.createUser(command);
    }

    @GetMapping("/me")
    public UserDTO readUser(@AuthenticationPrincipal SecurityUser securityUser) {
        return userService.findById(securityUser.getUserId());
    }

    @PutMapping
    public UserDTO updateUser(@RequestBody UserCommand.UpdateUser command, @AuthenticationPrincipal SecurityUser securityUser) {
        return userService.updateUser(command, securityUser.getUserId());
    }
}