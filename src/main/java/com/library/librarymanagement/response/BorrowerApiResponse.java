package com.library.librarymanagement.response;

import com.library.librarymanagement.entity.Borrower;

import lombok.Data;

@Data
public class BorrowerApiResponse {
	Borrower data;
	Status status;

}
