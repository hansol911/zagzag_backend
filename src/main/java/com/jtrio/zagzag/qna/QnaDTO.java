package com.jtrio.zagzag.qna;

import com.jtrio.zagzag.comment.CommentDTO;
import com.jtrio.zagzag.enums.QnAStatus;
import com.jtrio.zagzag.model.Comment;
import com.jtrio.zagzag.model.QnA;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class QnaDTO {
    @Data
    public static class CreateQna {
        private String nickname;
        private String question;
        private boolean secret;
        private LocalDateTime created;

        public static CreateQna toDTO(QnA qna) {
            CreateQna qnaDTO = new CreateQna();
            String nick = qna.getUser().getEmail();
            nick = nick.replaceAll("([\\w.])(?:[\\w.]*)(@.*)", "$1****$2");
            qnaDTO.setNickname(nick);
            qnaDTO.setQuestion(qna.getQuestion());
            qnaDTO.setSecret(qna.isSecret());
            qnaDTO.setCreated(qna.getCreated());
            return qnaDTO;
        }
    }

    @Data
    public static class ReadQna {
        private String nickname;
        private String question;
        private boolean secret;
        private LocalDateTime created;
        private List<CommentDTO> comment;

        public static ReadQna toDTO(QnA qna, List<Comment> comments) {
            ReadQna qnaDTO = new ReadQna();
            String nick = qna.getUser().getEmail();
            nick = nick.replaceAll("([\\w.])(?:[\\w.]*)(@.*)", "$1****$2");
            List<CommentDTO> commentDTOS = comments.stream().map(CommentDTO::toDTO).collect(Collectors.toList());
            qnaDTO.setNickname(nick);
            qnaDTO.setCreated(qna.getCreated());
            qnaDTO.setSecret(qna.isSecret());
            qnaDTO.setQuestion(qna.isSecret() ? "비밀글입니다." : qna.getQuestion());
            qnaDTO.setComment(qna.isSecret() ? null : commentDTOS);
            return qnaDTO;
        }
    }

    @Data
    public static class DeleteQna {
        private String question;
        private QnAStatus qnaStatus;

        public static DeleteQna toDTO(QnA qna) {
            DeleteQna qnaDTO = new DeleteQna();
            qnaDTO.setQuestion("삭제된 글입니다.");
            qnaDTO.setQnaStatus(qna.getQnaStatus());
            return qnaDTO;
        }
    }
}