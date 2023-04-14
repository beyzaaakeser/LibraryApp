package com.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tbl_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Book name can not be null")
    @Size(min = 2,max = 80,message = "Book name '${validateValue}' should be between {min} and {max}")
    private String name;

    @Column(nullable = false, length = 50)
    private String type;


    @Pattern(regexp = "^\\d{3}-\\d{2}-\\d{5}-\\d{2}-\\d{1}$")
    @NotNull(message = "Book name can not be null")
    @Column(length = 17)
    private String isbnNo;


    private Integer yearOfPublication;


    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


}
