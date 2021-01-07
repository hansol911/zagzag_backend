package com.jtrio.zagzag.qna;

import com.jtrio.zagzag.exception.ProductNotFoundException;
import com.jtrio.zagzag.exception.QnaNotFoundException;
import com.jtrio.zagzag.exception.UserNotFoundException;
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

import static com.jtrio.zagzag.enums.QnAStatus.DELETED;

@Service
@Transactional
@RequiredArgsConstructor
public class QnaService {
    private final QnaRepository qnaRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    //qna등록
    public QnaDTO createQna(QnaCommand command, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("회원이 아님"));
        Product product = productRepository.findById(command.getProductId()).orElseThrow(() -> new ProductNotFoundException("상품이 없음"));
        QnA qna = qnaRepository.save(command.toQna(user, product));
        return QnaDTO.toDTO(qna);
    }

    //qna조회
    public List<QnaDTO> readQna(Long userId, Long productId, Pageable pageable) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found"));
        productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("product not found"));
        List<QnA> qnas = qnaRepository.findByProductId(productId, pageable);
        List<QnaDTO> qnaDTOS = new ArrayList<>();
        for(QnA qna : qnas) {
            QnaDTO dto = QnaDTO.toDTO(qna);
            qnaDTOS.add(dto);
        }
        return qnaDTOS;
    }

    //qna수정
    public QnaDTO updateQna(QnaCommand command, Long id) {
        QnA qna = qnaRepository.findById(id).orElseThrow(() -> new QnaNotFoundException("qna not found"));
        qnaRepository.save(command.toQna(qna.getUser(), qna.getProduct()));
        return QnaDTO.toDTO(qna);
    }

    //qna삭제
    public QnaDTO deleteQna(Long userId, Long id) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found"));
        QnA qna = qnaRepository.findById(id).orElseThrow(() -> new QnaNotFoundException("qna not found"));
        qna.setQnaStatus(DELETED);
        return QnaDTO.toDTO(qna);
    }
}
