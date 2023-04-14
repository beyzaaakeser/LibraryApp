package com.library.controller;

import com.library.domain.dto.BookDTO;
import com.library.domain.dto.request.BookRequest;
import com.library.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {


    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<String> createBook(@Valid @RequestBody BookRequest bookRequest){
        bookService.saveBook(bookRequest);
        String str = "Book saved successfully";
        return ResponseEntity.ok(str);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long id){
        BookDTO bookDTO= bookService.getBookById(id);
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping("/name")
    public ResponseEntity<List<BookDTO>> getBookByName(@RequestParam("name") String name){

        List<BookDTO> list = bookService.findBook(name);

        return ResponseEntity.ok(list);

    }


    @GetMapping("/type")
    public ResponseEntity<List<BookDTO>> getBookByType(@RequestParam("type") String type){
        List<BookDTO> books= bookService.findBookByType(type);
        return ResponseEntity.ok(books);

    }


    @GetMapping("/summary")
    public ResponseEntity<List<Object>>getBookSummary(){
        List<Object> summary =  bookService.getBookSummary();
        return ResponseEntity.ok(summary);
    }


    @GetMapping("/all")
    public ResponseEntity<List<BookDTO>> getAllBook(){
        List<BookDTO> bookDTOList= bookService.getAllBook();
        return ResponseEntity.ok(bookDTOList);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<BookDTO>> getAllBookWithPage(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String prop,
            @RequestParam(value = "directon",required = false,defaultValue = "DESC")  Sort.Direction direction) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));

        Page<BookDTO> bookDTOPage = bookService.getAllWithPage(pageable);
        return ResponseEntity.ok(bookDTOPage);
    }



    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable("id") Long id, @Valid @RequestBody BookRequest bookRequest){

        bookService.updateBook(id,bookRequest);
        String str = "Book updated successfully";
        return ResponseEntity.ok(str);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id){
        bookService.removeBook(id);
        String str = "Book deleted successfully";
        return ResponseEntity.ok(str);

    }



}
