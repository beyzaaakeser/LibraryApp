package com.library.service;

import com.library.domain.Author;
import com.library.domain.Book;
import com.library.domain.dto.BookDTO;
import com.library.domain.dto.request.BookRequest;
import com.library.exception.ResourceNotFountException;
import com.library.exception.message.ErrorMessage;
import com.library.mapper.BookMapper;
import com.library.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, AuthorService authorService, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.bookMapper = bookMapper;
    }

    public void saveBook(BookRequest bookRequest) {
        Book book = bookMapper.bookRequestToBook(bookRequest);
        Author author = authorService.getAuthor(bookRequest.getAuthorId());
        book.setAuthor(author);
        bookRepository.save(book);
    }

    public BookDTO getBookById(Long id) {
        Book book = getBook(id);
        return bookMapper.bookToBookDTO(book);
    }

    public List<BookDTO> findBook(String name) {
        List<Book> bookList= bookRepository.findByName(name);
        return bookMapper.map(bookList);
    }

    public List<BookDTO> findBookByType(String type) {
        List<Book> bookList= bookRepository.findByType(type);
        return bookMapper.map(bookList);
    }


    public Book getBook(Long id){
        Book book = bookRepository.findById(id).orElseThrow(()->
                new ResourceNotFountException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION,id)));
        return book;
    }

    public List<BookDTO> getAllBook() {
        List<Book> bookList = bookRepository.findAll();
        return bookMapper.map(bookList);
    }

    public Page<BookDTO> getAllWithPage(Pageable pageable) {
        Page<Book> bookPage = bookRepository.findAll(pageable);
        return bookPage.map(
                book -> bookMapper.bookToBookDTO(book));
    }

    public void updateBook(Long id, BookRequest bookRequest) {
        Author author = authorService.getAuthor(bookRequest.getAuthorId());
        Book book = getBook(id);

        book.setAuthor(author);
        book.setName(bookRequest.getName());
        book.setIsbnNo(bookRequest.getIsbnNo());
        book.setYearOfPublication(bookRequest.getYearOfPublication());
        bookRepository.save(book);
    }

    public void removeBook(Long id) {
        Book book = getBook(id);
        bookRepository.delete(book);
    }


    public List<Object> getBookSummary() {
        List<Object> summary = bookRepository.bookSummary();
        return summary;
    }
}
