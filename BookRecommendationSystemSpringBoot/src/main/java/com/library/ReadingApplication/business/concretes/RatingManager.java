package com.library.ReadingApplication.business.concretes;

import com.library.ReadingApplication.business.abstracts.RatingService;
import com.library.ReadingApplication.business.constants.Messages;
import com.library.ReadingApplication.business.requests.create.CreateBookRequest;
import com.library.ReadingApplication.business.requests.create.CreateRatingRequest;
import com.library.ReadingApplication.business.responses.create.CreateBookResponse;
import com.library.ReadingApplication.business.responses.create.CreateRatingResponse;
import com.library.ReadingApplication.business.responses.get.Book.GetAllBookResponse;
import com.library.ReadingApplication.business.responses.get.Book.GetBookResponse;
import com.library.ReadingApplication.business.responses.get.Rating.GetAllRatingResponse;
import com.library.ReadingApplication.business.responses.get.Rating.GetRatingResponse;
import com.library.ReadingApplication.core.utilities.exceptions.BusinessException;
import com.library.ReadingApplication.core.utilities.mapping.ModelMapperService;
import com.library.ReadingApplication.core.utilities.results.DataResult;
import com.library.ReadingApplication.core.utilities.results.ErrorDataResult;
import com.library.ReadingApplication.core.utilities.results.SuccessDataResult;
import com.library.ReadingApplication.dataAccess.BookRepository;
import com.library.ReadingApplication.dataAccess.RatingRepository;
import com.library.ReadingApplication.entities.Book;
import com.library.ReadingApplication.entities.Rating;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RatingManager implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public DataResult<List<GetAllRatingResponse>> getAll() {
        List<Rating> ratings = this.ratingRepository.findAll();
        List<GetAllRatingResponse> allRatingResponses = ratings.stream()
                .map(rating -> this.modelMapperService.forResponse().map(rating, GetAllRatingResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<GetAllRatingResponse>>(allRatingResponses, Messages.RatingListed);
    }
    @Override
    public DataResult<CreateRatingResponse> add(CreateRatingRequest ratingRequest) {

        // Extract user ID and book ID from the request
        Long userId = ratingRequest.getUserId();
        Long bookId = ratingRequest.getBookId();

        // Check if a rating already exists for the user and book
        boolean ratingExists = ratingRepository.existsByUserIdAndBookId(userId, bookId);

        if (ratingExists) {
            // User has already rated the book, return appropriate response
            return new ErrorDataResult<>(Messages.RatingAlreadyExists);
        }

        // No existing rating found, proceed with saving the new rating
        Rating rating = this.modelMapperService.forRequest().map(ratingRequest, Rating.class);
        this.ratingRepository.save(rating);
        CreateRatingResponse createRatingResponse = this.modelMapperService.forResponse().map(rating,
                CreateRatingResponse.class);
        return new SuccessDataResult<CreateRatingResponse>(createRatingResponse, Messages.RatingCreated);
    }
    @Override
    public DataResult<GetRatingResponse> getById(Long id) {
        checkIfRatingId(id);
        Optional<Rating> rating = this.ratingRepository.findById(id);
        GetRatingResponse ratingResponse = this.modelMapperService.forResponse().map(rating, GetRatingResponse.class);
        return new SuccessDataResult<GetRatingResponse>(ratingResponse, Messages.RatingListed);
    }

    @Override // Explicitly mark this method as overriding from RatingService
    public boolean existsByUserIdAndBookId(Long userId, Long bookId) {
        return ratingRepository.existsByUserIdAndBookId(userId, bookId);
    }

    private void checkIfRatingId(Long bookId) {
        Optional<Rating> rating = this.ratingRepository.findById(bookId);
        if (rating == null) {
            throw new BusinessException(Messages.RatingIdNotFound);
        }
    }


}
