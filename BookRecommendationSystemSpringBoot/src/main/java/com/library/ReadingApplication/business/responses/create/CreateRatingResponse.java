package com.library.ReadingApplication.business.responses.create;

import com.library.ReadingApplication.entities.Book;
import com.library.ReadingApplication.springJwt.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRatingResponse {
    private Long userId;
    private Long bookId;
    private int rating;
}
