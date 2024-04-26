package com.library.ReadingApplication.webApi.controllers;

import com.library.ReadingApplication.business.abstracts.BookService;
import com.library.ReadingApplication.business.abstracts.RatingService;
import com.library.ReadingApplication.business.requests.check.RatingCheckRequest;
import com.library.ReadingApplication.business.requests.create.CreateBookRequest;
import com.library.ReadingApplication.business.requests.create.CreateRatingRequest;
import com.library.ReadingApplication.business.responses.create.CreateBookResponse;
import com.library.ReadingApplication.business.responses.create.CreateRatingResponse;
import com.library.ReadingApplication.business.responses.get.Book.GetAllBookResponse;
import com.library.ReadingApplication.business.responses.get.Rating.GetAllRatingResponse;
import com.library.ReadingApplication.core.utilities.results.DataResult;
import com.library.ReadingApplication.springJwt.model.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/rating")
@RestController
@AllArgsConstructor
public class RatingController {
    private RatingService ratingService;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        DataResult<List<GetAllRatingResponse>> result = this.ratingService.getAll();
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @PostMapping()
    public ResponseEntity<?> add(@RequestBody @Valid CreateRatingRequest ratingRequest) {
        DataResult<CreateRatingResponse> result = this.ratingService.add(ratingRequest);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @PostMapping("/exists")
    public ResponseEntity<Boolean> checkRatingExists(@RequestBody RatingCheckRequest request) {
        boolean ratingExists = this.ratingService.existsByUserIdAndBookId(request.getUserId(), request.getBookId());
        return ResponseEntity.ok(ratingExists);
    }


}
