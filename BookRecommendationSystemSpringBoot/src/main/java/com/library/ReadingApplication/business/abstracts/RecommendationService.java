package com.library.ReadingApplication.business.abstracts;

import com.library.ReadingApplication.entities.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RecommendationService {
    String getRecommendations(String bookTitle);
}
