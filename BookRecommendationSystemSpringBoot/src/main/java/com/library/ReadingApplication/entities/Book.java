package com.library.ReadingApplication.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = true)
    private String image;



    // No need to write getters, setters, equals(), hashCode(), toString()
}
