package com.jtrio.zagzag.qna;

import com.jtrio.zagzag.enums.QnAStatus;
import com.jtrio.zagzag.model.Comment;
import com.jtrio.zagzag.model.QnA;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class QnaDTO {
    @Data
    public static class CreateQna {
        private String nickname;
        private String question;
        private boolean secret;
        private LocalDateTime created;

        public static QnaDTO.CreateQna toDTO(QnA qna){
            QnaDTO.CreateQna qnaDTO = new QnaDTO.CreateQna();
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
        private List<Long> commentId;

        public static QnaDTO.ReadQna toDTO(QnA qna){
            QnaDTO.ReadQna qnaDTO = new QnaDTO.ReadQna();
            List<Long> comments = new ArrayList<>();
            for (int i=0; i<qna.getComment().size(); i++){
                comments.add(qna.getComment().get(i).getId());
            }
            String nick = qna.getUser().getEmail();
            nick = nick.replaceAll("([\\w.])(?:[\\w.]*)(@.*)", "$1****$2");
            qnaDTO.setNickname(nick);
            qnaDTO.setQuestion(qna.getQuestion());
            qnaDTO.setSecret(qna.isSecret());
            qnaDTO.setCreated(qna.getCreated());
            qnaDTO.setCommentId(comments);

            return qnaDTO;
        }
    }

    @Data
    public static class DeleteQna {
        private QnAStatus qnaStatus;

        public static QnaDTO.DeleteQna toDTO(QnA qna){
            QnaDTO.DeleteQna qnaDTO = new QnaDTO.DeleteQna();
            qnaDTO.setQnaStatus(qna.getQnaStatus());

            return qnaDTO;
        }
    }
}