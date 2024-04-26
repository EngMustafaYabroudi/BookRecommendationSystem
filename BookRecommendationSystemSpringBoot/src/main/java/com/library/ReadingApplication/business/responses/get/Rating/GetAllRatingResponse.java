package com.library.ReadingApplication.business.responses.get.Rating;

import com.library.ReadingApplication.entities.Book;
import com.library.ReadingApplication.springJwt.model.User;


public class GetAllRatingResponse {
    private Long id;
    private User user;
    private Book book;
    private int rating;
}
