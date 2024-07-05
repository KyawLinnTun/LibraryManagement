package com.library.librarymanagement.request;

import lombok.Data;

@Data
public class BookRequest {
	
	private String isbn;
	private String title;
	private String author;

}
