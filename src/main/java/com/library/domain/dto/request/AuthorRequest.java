package com.library.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {


    @NotNull(message = "Author full name can not be null")
    @Size(min = 4,max = 70,message = "Author full name '${validateValue}' should be between {min} and {max}")
    private String authorFullName;


}
