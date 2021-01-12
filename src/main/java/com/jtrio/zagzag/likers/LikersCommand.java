package com.jtrio.zagzag.likers;

import com.jtrio.zagzag.model.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LikersCommand {
    @NotBlank
    private Long reviewId;

    public Likers toLikers(User user, Review review) {
        Likers likers = new Likers();
        likers.setUser(user);
        likers.setReview(review);
        return likers;
    }
}
