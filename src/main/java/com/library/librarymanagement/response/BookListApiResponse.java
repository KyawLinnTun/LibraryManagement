package com.library.librarymanagement.response;

import java.util.List;

import com.library.librarymanagement.entity.Book;

import lombok.Data;

@Data
public class BookListApiResponse {
	
	List<Book> data;
	Status status;

}
