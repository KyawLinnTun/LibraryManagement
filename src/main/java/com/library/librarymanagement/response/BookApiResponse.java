package com.library.librarymanagement.response;

import com.library.librarymanagement.entity.Book;

import lombok.Data;

@Data
public class BookApiResponse {
	
	Book data;
	Status status;
	

}
