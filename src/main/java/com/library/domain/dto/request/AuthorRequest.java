package com.library.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {


    @NotNull(message = "Author full name can not be null")
    @Size(min = 4,max = 70,message = "Author full name '${validateValue}' should be between {min} and {max}")
    private String authorFullName;


}
