package com.library.librarymanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Book {
   
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private boolean isBorrowed;
    
	public Book(String isbn, String title, String author,boolean isBorrow) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
	}




	
    
    
}
