package com.library.ReadingApplication.business.responses.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookResponse {
    private Long id;
    private String title;
    private String genre;
    private String isbn;
    private String description;
    private String publisher;

}
