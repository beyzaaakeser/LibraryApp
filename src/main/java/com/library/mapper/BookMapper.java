package com.library.mapper;
import com.library.domain.Book;
import com.library.domain.dto.BookDTO;
import com.library.domain.dto.request.BookRequest;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO bookToBookDTO(Book book);
    List<BookDTO> map(List<Book> books);

    Book bookRequestToBook(BookRequest bookRequest);
}
