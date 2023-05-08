package com.library.controller;

import com.library.domain.dto.AuthorDTO;
import com.library.domain.dto.request.AuthorRequest;
import com.library.exception.ResourceNotFountException;
import com.library.exception.message.ErrorMessage;
import com.library.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @PostMapping
    public ResponseEntity<String> createAuthor(@Valid @RequestBody AuthorRequest authorRequest){
        authorService.saveAuthor(authorRequest);
        String str = "Author saved successfully";
        return new ResponseEntity<>(str, HttpStatus.CREATED); // created
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable("id") Long id){
        AuthorDTO authorDTO = authorService.getAuthorById(id);
        return ResponseEntity.ok(authorDTO);
    }


    @GetMapping("/all")
    public ResponseEntity<List<AuthorDTO>> getAllAuthor(){
        List<AuthorDTO> authorDTOList= authorService.getAllAuthor();
        return ResponseEntity.ok(authorDTOList);
    }


    @GetMapping("/page")
    public ResponseEntity<Page<AuthorDTO>> getAllAuthorWithPage(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String prop,
            @RequestParam(value = "directon",required = false,defaultValue = "DESC")  Sort.Direction direction) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));

        Page<AuthorDTO> authorDTOPage = authorService.getAllWithPage(pageable);
        return ResponseEntity.ok(authorDTOPage);
    }



    @PutMapping("/{id}")
    public ResponseEntity<String> updateAuthor(@PathVariable("id") Long id, @Valid @RequestBody AuthorRequest authorRequest){

        authorService.updateAuthor(id,authorRequest);
        String str = "Author updated successfully";
        return ResponseEntity.ok(str);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id") Long id){
        try {
            authorService.removeAuthor(id);
            String str = "Author deleted successfully";
            return ResponseEntity.ok(str);
        } catch (Exception e) {
            throw new ResourceNotFountException(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION);
        }

    }


}
