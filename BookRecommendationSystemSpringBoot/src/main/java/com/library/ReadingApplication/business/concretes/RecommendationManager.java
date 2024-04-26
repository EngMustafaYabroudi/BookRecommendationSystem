package com.library.ReadingApplication.business.concretes;

import com.library.ReadingApplication.business.abstracts.RecommendationService;
import com.library.ReadingApplication.entities.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class RecommendationManager implements RecommendationService {
    @Override
    public String  getRecommendations(String bookTitle) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://127.0.0.1:5000/recommendations/" + bookTitle;
        String response = restTemplate.getForObject(url, String.class);

        // Process JSON response using a JSON library (e.g., Jackson)
        return response;

    }
}
