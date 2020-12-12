package com.jtrio.zagzag.user;

import com.jtrio.zagzag.exception.CheckEmailException;
import com.jtrio.zagzag.exception.UserNotFoundException;
import com.jtrio.zagzag.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserDTO signUp(@RequestBody @Valid UserCommand.CreateUser user) {
        return userService.signUp(user);
    }

    @PutMapping(value = "/{id}")
    public UserDTO updateUser(@RequestBody UserCommand.UpdateUser user, @PathVariable Long id) {
        return userService.updateUser(user, id);
    }
}