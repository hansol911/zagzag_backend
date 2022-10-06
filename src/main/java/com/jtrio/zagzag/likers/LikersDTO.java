package com.jtrio.zagzag.likers;

import com.jtrio.zagzag.model.Likers;
import lombok.Data;

@Data
public class LikersDTO {
    private Long userId;
    private Long reviewId;

    public static LikersDTO toDTO(Likers likers) {
        LikersDTO likersDTO = new LikersDTO();
        likersDTO.setUserId(likers.getUser().getId());
        likersDTO.setReviewId(likers.getReview().getId());
        return likersDTO;
    }
}
