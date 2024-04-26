package com.library.ReadingApplication.dataAccess;

import com.library.ReadingApplication.entities.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Recommendation,Long> {
}
