package com.jtrio.zagzag.comment;

import com.jtrio.zagzag.enums.CommenterType;
import com.jtrio.zagzag.model.Comment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long id;
    private CommenterType commenterType;
    private String content;
    private LocalDateTime created;

    public static CommentDTO toDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setCommenterType(comment.getCommenterType());
        commentDTO.setContent(comment.getContent());
        commentDTO.setCreated(comment.getCreated());
        return commentDTO;
    }
}
