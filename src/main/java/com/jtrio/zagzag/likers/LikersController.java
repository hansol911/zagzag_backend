package com.jtrio.zagzag.likers;

import com.jtrio.zagzag.model.Likers;
import com.jtrio.zagzag.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("likers")
@RequiredArgsConstructor
public class LikersController {
    private final LikersService likersService;

    @PostMapping
    public Likers createLike(@RequestBody @Valid LikersCommand command, @AuthenticationPrincipal SecurityUser securityUser) {
        return likersService.createLike(command, securityUser.getUserId());
    }
}
