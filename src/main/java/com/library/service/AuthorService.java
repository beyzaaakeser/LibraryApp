package com.library.service;

import com.library.domain.Author;
import com.library.domain.dto.AuthorDTO;
import com.library.domain.dto.request.AuthorRequest;
import com.library.exception.ResourceNotFountException;
import com.library.exception.message.ErrorMessage;
import com.library.mapper.AuthorMapper;
import com.library.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public void saveAuthor(AuthorRequest authorRequest) {
        Author author = new Author();
        author.setAuthorFullName(authorRequest.getAuthorFullName());

        authorRepository.save(author);
    }

    public AuthorDTO getAuthorById(Long id) {
        Author author = getAuthor(id);
        return authorMapper.authorToAuthorDTO(author);
    }

    public Author getAuthor(Long id){
         Author author = authorRepository.findById(id).orElseThrow(()->
                new ResourceNotFountException(String.format(ErrorMessage.RESOURCE_NOT_FOUND_EXCEPTION,id)));
        return author;
    }


    public List<AuthorDTO> getAllAuthor() {
        List<Author> authorList = authorRepository.findAll();
        return authorMapper.map(authorList);
    }


    public Page<AuthorDTO> getAllWithPage(Pageable pageable) {
        Page<Author> authorPage = authorRepository.findAll(pageable);
        return authorPage.map(
                author -> authorMapper.authorToAuthorDTO(author));
    }


    public void updateAuthor(Long id, AuthorRequest authorRequest) {
        Author author = getAuthor(id);
        author.setAuthorFullName(authorRequest.getAuthorFullName());
        authorRepository.save(author);
    }


    public void removeAuthor(Long id) {
        Author author = getAuthor(id);
        authorRepository.delete(author);
    }



}
