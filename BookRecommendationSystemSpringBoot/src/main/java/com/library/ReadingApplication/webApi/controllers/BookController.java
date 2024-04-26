package com.library.ReadingApplication.webApi.controllers;

import com.library.ReadingApplication.business.abstracts.BookService;
import com.library.ReadingApplication.business.requests.create.CreateBookRequest;
import com.library.ReadingApplication.business.requests.delete.DeleteBookReguest;
import com.library.ReadingApplication.business.requests.update.UpdateBookRequest;
import com.library.ReadingApplication.business.responses.create.CreateBookResponse;
import com.library.ReadingApplication.business.responses.get.Book.GetAllBookResponse;
import com.library.ReadingApplication.business.responses.get.Book.GetBookResponse;
import com.library.ReadingApplication.business.responses.update.UpdateBookResponse;
import com.library.ReadingApplication.core.utilities.results.DataResult;
import com.library.ReadingApplication.core.utilities.results.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/books")
@RestController
@AllArgsConstructor

public class BookController {

    private BookService bookService;

    @GetMapping()
    public ResponseEntity<?> getAll() {
        DataResult<List<GetAllBookResponse>> result = this.bookService.getAll();
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @PostMapping()
    public ResponseEntity<?> add(@RequestBody @Valid CreateBookRequest bookRequest) {
        DataResult<CreateBookResponse> result = this.bookService.add(bookRequest);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable @Valid Long id) {
        DataResult<GetBookResponse> result = this.bookService.getById(id);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateBookRequest updateBookRequest,@PathVariable @Valid Long id) {
        DataResult<UpdateBookResponse> result = this.bookService.update(updateBookRequest,id);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestBody @Valid DeleteBookReguest deleteBookRequest,@PathVariable @Valid Long id) {
        Result result = this.bookService.delete(deleteBookRequest,id);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

}
