package com.library.ReadingApplication.entities;

import com.library.ReadingApplication.springJwt.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
