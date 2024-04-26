package com.library.ReadingApplication.webApi.controllers;

import com.library.ReadingApplication.business.abstracts.RecommendationService;
import com.library.ReadingApplication.entities.Book;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/recommend/")
@RestController
@AllArgsConstructor
public class RecommendationController {

    private RecommendationService recommendationService;

    @GetMapping("{bookTitle}")
    public String recommendBooks(@PathVariable String bookTitle) {
        return this.recommendationService.getRecommendations(bookTitle);

    }
}
