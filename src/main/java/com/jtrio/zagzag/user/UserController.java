package com.jtrio.zagzag.user;

import com.jtrio.zagzag.model.User;
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

    @PostMapping
    public UserDTO createUser(@RequestBody @Valid UserCommand.CreateUser user) {
        return userService.createUser(user);
    }

    @GetMapping("/me")
    public UserDTO readUser(@AuthenticationPrincipal SecurityUser securityUser){
        User user = userService.findByEmail(securityUser.getUsername());
        return UserDTO.toDTO(user);
    }

    @PutMapping(value = "/{id}")
    public UserDTO updateUser(@RequestBody UserCommand.UpdateUser user, @PathVariable Long id) {
        return userService.updateUser(user, id);
    }
}