package com.jtrio.zagzag.qna;

import com.jtrio.zagzag.model.User;
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
        User user = userService.findById(securityUser.getUser().getId());
        return qnaService.createQna(command, user);
    }

    @GetMapping
    public List<QnaDTO.ReadQna> readQna(@AuthenticationPrincipal SecurityUser securityUser, @RequestParam Long productId, Pageable pageable) {
        User user = userService.findById(securityUser.getUser().getId());
        return qnaService.readQna(user, productId, pageable);
    }

    @PutMapping(value = "/{id}")
    public QnaDTO.ReadQna updateQna(@RequestBody QnaCommand.UpdateQna command, @AuthenticationPrincipal SecurityUser securityUser, @PathVariable Long id) {
        User user = userService.findById(securityUser.getUser().getId());
        return qnaService.updateQna(command, user, id);
    }

    @PutMapping(value = "/delete/{id}")
    public QnaDTO.DeleteQna deleteQna(@AuthenticationPrincipal SecurityUser securityUser, @PathVariable Long id) {
        User user = userService.findById(securityUser.getUser().getId());
        return qnaService.deleteQna(user, id);
    }
}
