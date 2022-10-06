package com.jtrio.zagzag.qna;

import com.jtrio.zagzag.enums.QnAStatus;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.QnA;
import com.jtrio.zagzag.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

import static com.jtrio.zagzag.enums.QnAStatus.NORMAL;

@Data
public class QnaCommand {
    @Data
    public static class CreateQna {
        @NotBlank
        private String question;
        @NotBlank
        private boolean secret;
        @NotBlank
        private Long productId;

        public QnA toQna(User user, Product product) {
            QnA qna = new QnA();
            qna.setUser(user);
            qna.setProduct(product);
            qna.setQuestion(question);
            qna.setSecret(secret);
            qna.setQnaStatus(NORMAL);
            return qna;
        }
    }

    @Data
    public static class UpdateQna {
        @NotBlank
        private String question;
        @NotBlank
        private boolean secret;
        @NotBlank
        private QnAStatus qnaStatus;
        @NotBlank
        private Long productId;

        public QnA toQna(User user, QnA qna) {
            qna.setUser(user);
            qna.setProduct(qna.getProduct());
            qna.setQuestion(question);
            qna.setSecret(secret);
            qna.setQnaStatus(qnaStatus);
            return qna;
        }
    }
}
