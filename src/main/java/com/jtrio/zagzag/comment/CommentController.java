package com.jtrio.zagzag.comment;

import com.jtrio.zagzag.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentDTO createComment(@RequestBody @Valid CommentCommand command, @AuthenticationPrincipal SecurityUser securityUser, @RequestParam Long qnaId) {
        return commentService.createComment(command, securityUser.getUserId(), qnaId);
    }
}
