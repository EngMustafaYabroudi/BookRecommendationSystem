package com.library.ReadingApplication.business.requests.create;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBookRequest {

    @NotNull
    @NotEmpty
    private String isbn;
    private String title;
    private String genre;
    private String description;
    private String publisher;
    private String image;
}
