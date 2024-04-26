package com.library.ReadingApplication.dataAccess;

import com.library.ReadingApplication.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RatingRepository extends JpaRepository< Rating, Long> {

    boolean existsByUserIdAndBookId(Long userId, Long bookId);
}
