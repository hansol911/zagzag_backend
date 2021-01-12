package com.jtrio.zagzag.comment;

import com.jtrio.zagzag.model.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentCommand {
    @NotBlank
    private String content;

    public Comment toComment(User user, QnA qna) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(user);
        comment.setQnA(qna);
        return comment;
    }
}
