package com.jtrio.zagzag.qna;

import com.jtrio.zagzag.enums.QnAStatus;
import com.jtrio.zagzag.model.Comment;
import com.jtrio.zagzag.model.QnA;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QnaDTO {
    @Data
    public static class CreateQna{
        private String nickname;
        private String question;
        private LocalDateTime created;
        private List<Comment> comment;

        public static QnaDTO.CreateQna toDTO(QnA qna){
            QnaDTO.CreateQna qnaDTO = new QnaDTO.CreateQna();
            String nick = qna.getUser().getEmail();
            nick = nick.replaceAll("([\\w.])(?:[\\w.]*)(@.*)", "$1****$2");
            qnaDTO.setNickname(nick);
            qnaDTO.setQuestion(qna.getQuestion());
            qnaDTO.setCreated(qna.getCreated());
            qnaDTO.setComment(qna.getComment());

            return qnaDTO;
        }
    }

    @Data
    public static class DeleteQna{
        private String nickname;
        private LocalDateTime created;
        private QnAStatus qnaStatus;

        public static QnaDTO.DeleteQna toDTO(QnA qna){
            QnaDTO.DeleteQna qnaDTO = new QnaDTO.DeleteQna();
            String nick = qna.getUser().getEmail();
            nick = nick.replaceAll("([\\w.])(?:[\\w.]*)(@.*)", "$1****$2");
            qnaDTO.setNickname(nick);
            qnaDTO.setCreated(qna.getCreated());
            qnaDTO.setQnaStatus(qna.getQnaStatus());

            return qnaDTO;
        }
    }

}

