package com.library.ReadingApplication.business.abstracts;



import com.library.ReadingApplication.business.requests.create.CreateRatingRequest;
import com.library.ReadingApplication.business.responses.create.CreateRatingResponse;
import com.library.ReadingApplication.business.responses.get.Rating.GetAllRatingResponse;
import com.library.ReadingApplication.business.responses.get.Rating.GetRatingResponse;
import com.library.ReadingApplication.core.utilities.results.DataResult;

import java.util.List;

public interface  RatingService {
    DataResult<List<GetAllRatingResponse>> getAll();
    DataResult<CreateRatingResponse> add(CreateRatingRequest ratingRequest);
    DataResult<GetRatingResponse> getById(Long id);

    boolean existsByUserIdAndBookId(Long userId, Long bookId);
}
