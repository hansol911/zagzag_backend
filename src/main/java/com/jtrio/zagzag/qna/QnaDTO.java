package com.jtrio.zagzag.qna;

import com.jtrio.zagzag.model.Comment;
import com.jtrio.zagzag.model.QnA;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QnaDTO {
    private String nickname;
    private String question;
    private LocalDateTime created;
    private List<Comment> comment;


    public static QnaDTO toDTO(QnA qna){
        QnaDTO qnaDTO = new QnaDTO();
        String nick = qna.getUser().getEmail();
        nick = nick.replaceAll("([\\w.])(?:[\\w.]*)(@.*)", "$1****$2");
        qnaDTO.setNickname(nick);
        qnaDTO.setQuestion(qna.getQuestion());
        qnaDTO.setCreated(qna.getCreated());
        qnaDTO.setComment(qna.getComment());

        return qnaDTO;
    }
}
