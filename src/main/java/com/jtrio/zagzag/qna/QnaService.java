package com.jtrio.zagzag.qna;

import com.jtrio.zagzag.comment.CommentRepository;
import com.jtrio.zagzag.exception.ProductNotFoundException;
import com.jtrio.zagzag.exception.QnaNotFoundException;
import com.jtrio.zagzag.exception.UserAuthorityException;
import com.jtrio.zagzag.model.Comment;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.QnA;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.product.ProductRepository;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.jtrio.zagzag.enums.QnAStatus.DELETED;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {
    private final QnaRepository qnaRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;

    //qna등록
    public QnaDTO.CreateQna createQna(QnaCommand.CreateQna command, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        Product product = productRepository.findById(command.getProductId()).orElseThrow(() -> new ProductNotFoundException("product not found"));
        QnA qna = qnaRepository.save(command.toQna(user, product));
        return QnaDTO.CreateQna.toDTO(qna);
    }

    //qna조회
    public List<QnaDTO.ReadQna> readQna(Long userId, Long productId, Pageable pageable) {
        productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("product not found"));
        List<QnA> qnas = qnaRepository.findByProductId(productId, pageable);
        return qnas.stream().map(qna -> QnaDTO.ReadQna.toDTO(qna, commentRepository.findByQnAId(qna.getId()), userId)).collect(Collectors.toList());
    }

    //qna수정
    public QnaDTO.UpdateQna updateQna(QnaCommand.UpdateQna command, Long userId, Long qnaId) {
        User user = userRepository.findById(userId).orElseThrow();
        QnA qna = qnaRepository.findById(qnaId).orElseThrow(() -> new QnaNotFoundException("qna not found"));
        List<Comment> comments = commentRepository.findByQnAId(qna.getId());
        if (!qna.getUser().equals(user)) {
            throw new UserAuthorityException("cannot update qna");
        }
        qnaRepository.save(command.toQna(user, qna));
        return QnaDTO.UpdateQna.toDTO(qna, comments);
    }

    //qna삭제
    public QnaDTO.DeleteQna deleteQna(Long userId, Long id) {
        User user = userRepository.findById(userId).orElseThrow();
        QnA qna = qnaRepository.findById(id).orElseThrow(() -> new QnaNotFoundException("qna not found"));
        if (!qna.getUser().equals(user)) {
            throw new UserAuthorityException("cannot delete qna");
        }
        qna.setQnaStatus(DELETED);
        qnaRepository.save(qna);
        return QnaDTO.DeleteQna.toDTO(qna);
    }
}
