package com.jtrio.zagzag.qna;

import com.jtrio.zagzag.comment.CommentDTO;
import com.jtrio.zagzag.enums.QnAStatus;
import com.jtrio.zagzag.model.Comment;
import com.jtrio.zagzag.model.QnA;
import com.jtrio.zagzag.util.NicknameUtil;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class QnaDTO {
    @Data
    public static class CreateQna {
        private String userEmail;
        private String question;
        private boolean secret;
        private LocalDateTime created;

        public static CreateQna toDTO(QnA qna) {
            CreateQna qnaDTO = new CreateQna();
            qnaDTO.setUserEmail(qna.getUser().getEmail());
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

        public static ReadQna toDTO(QnA qna, List<Comment> comments, Long userId) {
            ReadQna qnaDTO = new ReadQna();
            List<CommentDTO> commentDTOS = comments.stream().map(CommentDTO::toDTO).collect(Collectors.toList());
            qnaDTO.setNickname(NicknameUtil.getNick(qna.getUser().getEmail()));
            qnaDTO.setCreated(qna.getCreated());
            qnaDTO.setSecret(qna.isSecret());
            qnaDTO.setQuestion(qna.getQnaStatus() == QnAStatus.DELETED ? "사용자의 요청에 의해 삭제되었습니다." : (qna.isSecret() && !qna.getUser().getId().equals(userId) ? "비밀글입니다." : qna.getQuestion()));
            qnaDTO.setComment(qna.isSecret() && !qna.getUser().getId().equals(userId) ? null : commentDTOS);
            return qnaDTO;
        }
    }

    @Data
    public static class UpdateQna {
        private String userEmail;
        private String question;
        private boolean secret;
        private LocalDateTime updated;
        private List<CommentDTO> comment;

        public static UpdateQna toDTO(QnA qna, List<Comment> comments) {
            UpdateQna qnaDTO = new UpdateQna();
            List<CommentDTO> commentDTOS = comments.stream().map(CommentDTO::toDTO).collect(Collectors.toList());
            qnaDTO.setUserEmail(qna.getUser().getEmail());
            qnaDTO.setQuestion(qna.getQuestion());
            qnaDTO.setComment(commentDTOS);
            qnaDTO.setSecret(qna.isSecret());
            qnaDTO.setUpdated(qna.getUpdated());
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