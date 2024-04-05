package com.lms.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.exceptions.BookNotFoundException;
import com.lms.exceptions.BorrowRecordNotFoundException;
import com.lms.exceptions.LibraryException;
import com.lms.exceptions.PatronNotFoundException;

@Service
public interface BorrowService {

	public ResponseEntity<?> borrowBook(Integer bookId, Integer patronId)
			throws LibraryException, BookNotFoundException, PatronNotFoundException;

	public ResponseEntity<?> returnBook(Integer bookId, Integer patronId)
			throws BookNotFoundException, PatronNotFoundException, BorrowRecordNotFoundException;
}
