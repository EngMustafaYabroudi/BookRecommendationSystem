package com.library.ReadingApplication.business.abstracts;

import com.library.ReadingApplication.business.requests.create.CreateBookRequest;
import com.library.ReadingApplication.business.requests.delete.DeleteBookReguest;
import com.library.ReadingApplication.business.requests.update.UpdateBookRequest;
import com.library.ReadingApplication.business.responses.create.CreateBookResponse;
import com.library.ReadingApplication.business.responses.get.Book.GetAllBookResponse;
import com.library.ReadingApplication.business.responses.get.Book.GetBookResponse;
import com.library.ReadingApplication.business.responses.update.UpdateBookResponse;
import com.library.ReadingApplication.core.utilities.results.DataResult;
import com.library.ReadingApplication.core.utilities.results.Result;

import java.util.List;


public interface BookService {
    DataResult<List<GetAllBookResponse>> getAll();
    DataResult<CreateBookResponse> add(CreateBookRequest bookRequest);
    DataResult<GetBookResponse> getById(Long id);
    DataResult<UpdateBookResponse> update(UpdateBookRequest updateBookRequest,Long id);
    Result delete(DeleteBookReguest deleteBookReguest, Long id);

    // Additional methods for filtering, searching, or other business logic specific to books
}
