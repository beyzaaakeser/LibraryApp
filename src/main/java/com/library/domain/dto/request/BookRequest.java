package com.library.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.library.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {


    @NotNull(message = "Book name can not be null")
    @Size(min = 2,max = 80,message = "Book name '${validateValue}' should be between {min} and {max}")
    private String name;

    @NotBlank
    @NotNull(message = "Book type can not be null")
    private String type;

    @NotBlank
    @NotNull(message = "Book isbnNo can not be null")
    private String isbnNo;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    private Integer yearOfPublication;


    @NotNull(message = "AuthorId can not be null")
    private Long authorId;


}
