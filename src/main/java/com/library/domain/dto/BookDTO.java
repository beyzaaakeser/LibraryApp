package com.library.domain.dto;

import com.library.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private String name;
    private String type;
    private String isbnNo;
    private Integer yearOfPublication;
    private Author author;
}
