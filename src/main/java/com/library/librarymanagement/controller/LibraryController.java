package com.library.librarymanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.librarymanagement.entity.Borrower;
import com.library.librarymanagement.request.BookRequest;
import com.library.librarymanagement.request.BorrowerRequest;
import com.library.librarymanagement.response.BookApiResponse;
import com.library.librarymanagement.response.BookListApiResponse;
import com.library.librarymanagement.response.BorrowerApiResponse;
import com.library.librarymanagement.service.LibraryService;

@RestController
@RequestMapping("/api/v1.0/")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping("/borrowers")
    public ResponseEntity<BorrowerApiResponse> registerBorrower(@RequestBody BorrowerRequest borrowerRequest) {
        return libraryService.registerBorrower(borrowerRequest);
    }

    @PostMapping("/books")
    public ResponseEntity<BookApiResponse> registerBook(@RequestBody BookRequest request) {
        return libraryService.registerBook(request);
    }

    @GetMapping("/books")
    public ResponseEntity<BookListApiResponse> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @GetMapping("/borrowers/{borrowerId}/borrow/{bookId}")
    public ResponseEntity<BookApiResponse> borrowBook(@PathVariable Long borrowerId, @PathVariable Long bookId) {
        return libraryService.borrowBook(borrowerId, bookId);
    }

    @GetMapping("/borrowers/{borrowerId}/return/{bookId}")
    public ResponseEntity<BookApiResponse> returnBook(@PathVariable Long borrowerId, @PathVariable Long bookId) {
        return libraryService.returnBook(borrowerId, bookId);
    }
}