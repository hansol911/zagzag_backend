package com.jtrio.zagzag.comment;

import com.jtrio.zagzag.exception.QnaNotFoundException;
import com.jtrio.zagzag.exception.UserAuthorityException;
import com.jtrio.zagzag.model.Comment;
import com.jtrio.zagzag.model.QnA;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.qna.QnaRepository;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final QnaRepository qnaRepository;

    public CommentDTO createComment(CommentCommand command, Long userId, Long qnaId) {
        User user = userRepository.findById(userId).orElseThrow();
        QnA qna = qnaRepository.findById(qnaId).orElseThrow(() -> new QnaNotFoundException("qna not found"));
        if (qna.isSecret() && !qna.getUser().equals(user)) {
            throw new UserAuthorityException("cannot create comment");
        }
        Comment comment = commentRepository.save(command.toComment(user, qna));
        return CommentDTO.toDTO(comment);
    }
}
