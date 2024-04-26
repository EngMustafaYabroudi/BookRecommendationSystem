package com.library.ReadingApplication.business.requests.check;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingCheckRequest {

    private Long userId;
    private Long bookId;
}
