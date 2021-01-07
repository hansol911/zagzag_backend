package com.jtrio.zagzag.qna;

import com.jtrio.zagzag.security.SecurityUser;
import com.jtrio.zagzag.user.UserService;
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
    private final UserService userService;

    @PostMapping
    public QnaDTO.CreateQna createQna(@RequestBody @Valid QnaCommand.CreateQna command, @AuthenticationPrincipal SecurityUser securityUser) {
        Long userId = userService.findByEmail(securityUser.getUsername()).getId();
        return qnaService.createQna(command, userId);
    }

    @GetMapping
    public List<QnaDTO.CreateQna> readQna(@AuthenticationPrincipal SecurityUser securityUser, @RequestParam Long productId, Pageable pageable) {
        Long userId = userService.findByEmail(securityUser.getUsername()).getId();
        return qnaService.readQna(userId, productId, pageable);
    }

    @PutMapping(value = "/{id}")
    public QnaDTO.CreateQna updateQna(@RequestBody QnaCommand.UpdateQna command, @AuthenticationPrincipal SecurityUser securityUser, @PathVariable Long id) {
        Long userId = userService.findByEmail(securityUser.getUsername()).getId();
        return qnaService.updateQna(command, userId, id);
    }

    @PutMapping(value = "/delete/{id}")
    public QnaDTO.DeleteQna deleteQna(@AuthenticationPrincipal SecurityUser securityUser, @PathVariable Long id) {
        Long userId = userService.findByEmail(securityUser.getUsername()).getId();
        return qnaService.deleteQna(userId, id);
    }
}
