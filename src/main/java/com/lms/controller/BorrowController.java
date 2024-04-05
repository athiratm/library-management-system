package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.exceptions.BookNotFoundException;
import com.lms.exceptions.BorrowRecordNotFoundException;
import com.lms.exceptions.LibraryException;
import com.lms.exceptions.PatronNotFoundException;
import com.lms.service.impl.BorrowServiceImpl;

@RestController
@RequestMapping("/borrowapi")
public class BorrowController {

	@Autowired
	private BorrowServiceImpl borrowService;

	/**
	 * To borrow a book
	 * 
	 * @param bookId
	 * @param patronId
	 * @return
	 * @throws LibraryException
	 * @throws BookNotFoundException
	 * @throws PatronNotFoundException
	 */
	@PostMapping(path = { "/borrow/{bookId}/patron/{patronId}" })
	public ResponseEntity<?> borrowBookFromLibrary(@PathVariable("bookId") Integer bookId,
			@PathVariable("patronId") Integer patronId)
			throws LibraryException, BookNotFoundException, PatronNotFoundException {

		return borrowService.borrowBook(bookId, patronId);

	}

	/**
	 * To return a book
	 * 
	 * @param bookId
	 * @param patronId
	 * @return
	 * @throws BookNotFoundException
	 * @throws PatronNotFoundException
	 * @throws BorrowRecordNotFoundException
	 */
	@PutMapping(path = { "/return/{bookId}/patron/{patronId}" })
	public ResponseEntity<?> returnBookToLibrary(@PathVariable("bookId") Integer bookId,
			@PathVariable("patronId") Integer patronId)
			throws BookNotFoundException, PatronNotFoundException, BorrowRecordNotFoundException {

		return borrowService.returnBook(bookId, patronId);

	}
}
