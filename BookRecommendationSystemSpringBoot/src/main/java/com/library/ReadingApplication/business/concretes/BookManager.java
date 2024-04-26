package com.library.ReadingApplication.business.concretes;

import com.library.ReadingApplication.business.abstracts.BookService;
import com.library.ReadingApplication.business.constants.Messages;
import com.library.ReadingApplication.business.requests.create.CreateBookRequest;
import com.library.ReadingApplication.business.requests.delete.DeleteBookReguest;
import com.library.ReadingApplication.business.requests.update.UpdateBookRequest;
import com.library.ReadingApplication.business.responses.create.CreateBookResponse;
import com.library.ReadingApplication.business.responses.get.Book.GetAllBookResponse;
import com.library.ReadingApplication.business.responses.get.Book.GetBookResponse;
import com.library.ReadingApplication.business.responses.update.UpdateBookResponse;
import com.library.ReadingApplication.core.utilities.exceptions.BusinessException;
import com.library.ReadingApplication.core.utilities.mapping.ModelMapperService;
import com.library.ReadingApplication.core.utilities.results.DataResult;
import com.library.ReadingApplication.core.utilities.results.Result;
import com.library.ReadingApplication.core.utilities.results.SuccessDataResult;
import com.library.ReadingApplication.core.utilities.results.SuccessResult;
import com.library.ReadingApplication.dataAccess.BookRepository;
import com.library.ReadingApplication.entities.Book;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class BookManager implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapperService modelMapperService;
    @Override
    public DataResult<List<GetAllBookResponse>> getAll() {
        List<Book> books = this.bookRepository.findAll();
        List<GetAllBookResponse> allBookResponses = books.stream()
                .map(book -> this.modelMapperService.forResponse().map(book, GetAllBookResponse.class))
                .collect(Collectors.toList());

        return new SuccessDataResult<List<GetAllBookResponse>>(allBookResponses, Messages.BookListed);
    }
    @Override
    public DataResult<CreateBookResponse> add(CreateBookRequest bookRequest) {
        checkIfBookIsbn(bookRequest.getIsbn());
        Book book = this.modelMapperService.forRequest().map(bookRequest, Book.class);
        this.bookRepository.save(book);
        CreateBookResponse createBookResponse = this.modelMapperService.forResponse().map(book,
                CreateBookResponse.class);
        return new SuccessDataResult<CreateBookResponse>(createBookResponse, Messages.BookCreated);
    }
    @Override
    public DataResult<GetBookResponse> getById(Long id) {
        checkIfBookId(id);
        Optional<Book> book = this.bookRepository.findById(id);
        GetBookResponse bookResponse = this.modelMapperService.forResponse().map(book, GetBookResponse.class);
        return new SuccessDataResult<GetBookResponse>(bookResponse, Messages.BookListed);
    }

    @Override
    public DataResult<UpdateBookResponse> update(UpdateBookRequest updateBookRequest , Long id) {

        checkIfBookId(id);

        if(updateBookRequest.getIsbn()!=null){
            checkIfBookIsbn(updateBookRequest.getIsbn());
        }
        Book book = this.modelMapperService.forRequest().map(updateBookRequest, Book.class);
        this.bookRepository.save(book);
        UpdateBookResponse updateBrandResponse = this.modelMapperService.forResponse().map(book,
                UpdateBookResponse.class);
        return new SuccessDataResult<UpdateBookResponse>(updateBrandResponse, Messages.BookUpdated);
    }
    @Override
    public Result delete(DeleteBookReguest deleteBookReguest, Long id) {
        checkIfBookId(id);
        Book book = this.modelMapperService.forRequest().map(deleteBookReguest, Book.class);
        bookRepository.delete(book);
        return new SuccessResult(Messages.BookDeleted);

    }
    private void checkIfBookId(Long bookId) {
        Optional<Book> book = this.bookRepository.findById(bookId);
        if (book == null) {
            throw new BusinessException(Messages.BookIdNotFound);
        }
    }

    private void checkIfBookIsbn(String isbn) {
        Book book = this.bookRepository.getByIsbn(isbn);
        if (book != null) {
            throw new BusinessException(Messages.BookIsbnExists);
        }
    }


 }
