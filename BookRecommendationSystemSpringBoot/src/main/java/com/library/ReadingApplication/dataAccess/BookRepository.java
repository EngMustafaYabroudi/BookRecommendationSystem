package com.library.ReadingApplication.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.ReadingApplication.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book getByIsbn(String bookIsbn);
}
