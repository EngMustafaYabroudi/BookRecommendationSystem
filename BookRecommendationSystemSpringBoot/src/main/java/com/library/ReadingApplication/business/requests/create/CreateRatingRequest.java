package com.library.ReadingApplication.business.requests.create;

import com.library.ReadingApplication.entities.Book;
import com.library.ReadingApplication.springJwt.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRatingRequest {

    private int rating;
    private Long userId;
    private Long bookId;
}
