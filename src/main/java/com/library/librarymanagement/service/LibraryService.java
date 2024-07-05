package com.library.librarymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.librarymanagement.entity.Book;
import com.library.librarymanagement.entity.Borrower;
import com.library.librarymanagement.repo.BookRepository;
import com.library.librarymanagement.repo.BorrowerRepository;
import com.library.librarymanagement.request.BookRequest;
import com.library.librarymanagement.request.BorrowerRequest;
import com.library.librarymanagement.response.BookApiResponse;
import com.library.librarymanagement.response.BookListApiResponse;
import com.library.librarymanagement.response.BorrowerApiResponse;
import com.library.librarymanagement.response.Status;

@Service
public class LibraryService {
	
	
	@Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<BorrowerApiResponse> registerBorrower(BorrowerRequest request) {
    	BorrowerApiResponse response = new BorrowerApiResponse();
    	Borrower borrower = new Borrower(request.getName(),request.getEmail());
    	Borrower result =   borrowerRepository.save(borrower);
    	response.setData(result);
        response.setStatus(new Status("success","Successfully Register")); 
        return new ResponseEntity<BorrowerApiResponse>(response,HttpStatus.OK);
    }

    public ResponseEntity<BookApiResponse> registerBook(BookRequest request) {
    	 BookApiResponse response = new BookApiResponse();
    	 Book existingBook = bookRepository.findByIsbn(request.getIsbn());
         
         if (existingBook == null) {
	        	Book book = new Book(request.getIsbn(),request.getTitle(),request.getAuthor(),false);
	            Book result  = bookRepository.save(book);
	            response.setData(result);
	            response.setStatus(new Status("success","Successfully Register"));
 	            return new ResponseEntity<BookApiResponse>(response,HttpStatus.OK);
         } else {
             if (existingBook.getTitle().equals(request.getTitle()) && existingBook.getAuthor().equals(request.getAuthor())) {
	 	        	Book book = new Book(request.getIsbn(),request.getTitle(),request.getAuthor(),false);
	            	Book result  = bookRepository.save(book);
	 	            response.setData(result);
	 	            response.setStatus(new Status("success","Successfully Register"));
	 	            return new ResponseEntity<BookApiResponse>(response,HttpStatus.OK);
             } else {
	 	            response.setStatus(new Status("fail","Another book with ISBN " + request.getIsbn() + " already exists with different title or author."));
	 	            return new ResponseEntity<BookApiResponse>(response,HttpStatus.BAD_REQUEST);
             }
         }
    }

    public ResponseEntity<BookListApiResponse> getAllBooks() {
    	BookListApiResponse response = new BookListApiResponse();
    	response.setData(bookRepository.findAll());
        response.setStatus(new Status("success","Success"));
        return new ResponseEntity<BookListApiResponse>(response,HttpStatus.OK);
    }

    public ResponseEntity<BookApiResponse> borrowBook(Long borrowerId, Long bookId) {
    	BookApiResponse response = new BookApiResponse();
        Optional<Borrower> borrower = borrowerRepository.findById(borrowerId);
        Optional<Book> book = bookRepository.findById(bookId);
        if (borrower.isPresent() && book.isPresent()) {
        	book.get().setBorrowed(true);
        	bookRepository.save(book.get());
        	response.setData(book.get());
        	response.setStatus(new Status("success","Success"));
	        return new ResponseEntity<BookApiResponse>(response,HttpStatus.OK);
        }else {
        	response.setStatus(new Status("fail","can't not borrow book " + bookId));
	        return new ResponseEntity<BookApiResponse>(response,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<BookApiResponse> returnBook(Long borrowerId, Long bookId) {
    	BookApiResponse response = new BookApiResponse();
        Optional<Borrower> borrower = borrowerRepository.findById(borrowerId);
        Optional<Book> book = bookRepository.findById(bookId);
        if (borrower.isPresent() && book.isPresent()) {
          if(book.get().isBorrowed() == true) {
        	book.get().setBorrowed(false);
        	response.setData(book.get());
        	response.setStatus(new Status("success","Success"));
	        return new ResponseEntity<BookApiResponse>(response,HttpStatus.OK);
          }
        } else {
        	response.setStatus(new Status("fail","failed"));
	        return new ResponseEntity<BookApiResponse>(response,HttpStatus.BAD_REQUEST);

        }
    }
}
