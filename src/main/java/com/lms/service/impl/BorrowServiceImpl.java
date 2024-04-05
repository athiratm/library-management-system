package com.lms.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.exceptions.BookNotFoundException;
import com.lms.exceptions.BorrowRecordNotFoundException;
import com.lms.exceptions.LibraryException;
import com.lms.exceptions.PatronNotFoundException;
import com.lms.model.Book;
import com.lms.model.BorrowingRecord;
import com.lms.model.Patron;
import com.lms.repository.BorrowingRecordRepository;
import com.lms.service.BorrowService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BorrowServiceImpl implements BorrowService {

	@Autowired
	private BookServiceImpl bookService;

	@Autowired
	private PatronServiceImpl patronService;

	@Autowired
	private BorrowingRecordRepository borrowingRecordRepository;

	/**
	 * To borrow book
	 * 
	 */
	@Override
	public ResponseEntity<?> borrowBook(Integer bookId, Integer patronId)
			throws BookNotFoundException, PatronNotFoundException, LibraryException {

		Optional<Book> book = bookService.findBook(bookId);
		Optional<Patron> patron = patronService.findPatron(patronId);
		if (book.isPresent() && patron.isPresent()) {
			List<BorrowingRecord> existingRecords = borrowingRecordRepository.findByBook(book);
			List<BorrowingRecord> activeBorrowRecords = existingRecords.stream()
					.filter(eachRecord -> eachRecord.getReturnDate() == null).collect(Collectors.toList());
			if (!activeBorrowRecords.isEmpty()) {
				throw new LibraryException("Book " + bookId + " already borrowed by someone and not returned");

			} else {
				BorrowingRecord borrowingRecord = new BorrowingRecord();
				borrowingRecord.setBook(book.get());
				borrowingRecord.setPatron(patron.get());
				borrowingRecord.setBorrowDate(LocalDateTime.now());
				borrowingRecordRepository.save(borrowingRecord);
				return new ResponseEntity<BorrowingRecord>(borrowingRecord, HttpStatus.OK);
			}

		} else if (book.isEmpty()) {
			throw new BookNotFoundException("Book " + bookId + " Not Found");
		} else if (patron.isEmpty()) {
			throw new PatronNotFoundException("Patron " + patronId + " Not Found");
		}
		return null;

	}

	/**
	 * To return book
	 * 
	 */
	@Override
	public ResponseEntity<?> returnBook(Integer bookId, Integer patronId)
			throws BookNotFoundException, PatronNotFoundException, BorrowRecordNotFoundException {

		Optional<Book> book = bookService.findBook(bookId);
		Optional<Patron> patron = patronService.findPatron(patronId);
		if (book.isPresent() && patron.isPresent()) {
			BorrowingRecord borrowingRecord = borrowingRecordRepository.findByBookAndPatron(book, patron);
			if (borrowingRecord != null) {
				borrowingRecord.setReturnDate(LocalDateTime.now());
			} else {
				throw new BorrowRecordNotFoundException("Borrow Record " + patronId + " Not Found");
			}
			borrowingRecordRepository.save(borrowingRecord);
			return new ResponseEntity<BorrowingRecord>(borrowingRecord, HttpStatus.OK);
		} else if (book.isEmpty()) {
			throw new BookNotFoundException("Book " + bookId + " Not Found");
		} else if (patron.isEmpty()) {
			throw new PatronNotFoundException("Patron " + patronId + " Not Found");
		}

		return null;

	}
}
