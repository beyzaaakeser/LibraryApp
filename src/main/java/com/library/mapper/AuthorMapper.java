package com.library.mapper;
import com.library.domain.Author;
import com.library.domain.dto.AuthorDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorDTO authorToAuthorDTO(Author author);
    List<AuthorDTO> map(List<Author> authors);

    Author authorDTOToAuthor(AuthorDTO authorDTO);
}
