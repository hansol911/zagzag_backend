package com.jtrio.zagzag.qna;

import com.jtrio.zagzag.aop.NoLogging;
import com.jtrio.zagzag.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("qnas")
@RequiredArgsConstructor
public class QnaController {
    private final QnaService qnaService;

    @PostMapping
    public QnaDTO.CreateQna createQna(@RequestBody @Valid QnaCommand.CreateQna command, @AuthenticationPrincipal SecurityUser securityUser) {
        return qnaService.createQna(command, securityUser.getUserId());
    }

    @NoLogging
    @GetMapping
    public List<QnaDTO.ReadQna> readQna(@AuthenticationPrincipal SecurityUser securityUser, @RequestParam Long productId, Pageable pageable) {
        return qnaService.readQna(securityUser != null ? securityUser.getUserId() : null, productId, pageable);
    }

    @PutMapping(value = "/{id}")
    public QnaDTO.ReadQna updateQna(@RequestBody QnaCommand.UpdateQna command, @AuthenticationPrincipal SecurityUser securityUser, @PathVariable Long id) {
        return qnaService.updateQna(command, securityUser.getUserId(), id);
    }

    @DeleteMapping(value = "/{id}")
    public QnaDTO.DeleteQna deleteQna(@AuthenticationPrincipal SecurityUser securityUser, @PathVariable Long id) {
        return qnaService.deleteQna(securityUser.getUserId(), id);
    }
}
