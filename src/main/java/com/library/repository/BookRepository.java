package com.library.repository;

import com.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByName(String name);

    List<Book> findByType(String type);

    @Query("SELECT b.name, b.author.authorFullName, b.type FROM Book b")
    List<Object> bookSummary();
}
