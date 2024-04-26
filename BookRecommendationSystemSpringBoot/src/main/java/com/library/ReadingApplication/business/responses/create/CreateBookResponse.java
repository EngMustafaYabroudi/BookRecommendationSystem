package com.library.ReadingApplication.business.responses.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookResponse {
    private Long id;
    private String title;
    private String genre;
    private String isbn;
    private String description;
    private String publisher;
}
