package com.lms.controller;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lms.exceptions.BookNotFoundException;
import com.lms.exceptions.BorrowRecordNotFoundException;
import com.lms.exceptions.LibraryException;
import com.lms.exceptions.PatronNotFoundException;
import com.lms.model.Book;
import com.lms.model.BorrowingRecord;
import com.lms.model.Patron;
import com.lms.service.impl.BorrowServiceImpl;
/**
 * Borrow controller test class
 * 
 */
@SpringBootTest
public class BorrowControllerTest {
	
	@InjectMocks
	private BorrowController borrowController;
	
	@Mock
	private BorrowServiceImpl borrowService;
	
	/**
	 * To test borrow book
	 * @throws PatronNotFoundException 
	 * @throws BookNotFoundException 
	 * @throws LibraryException 
	 * 
	 */
	@Test
	public void testBorrowBook() throws LibraryException, BookNotFoundException, PatronNotFoundException {
		Book book = new Book(1, "story book", "Abc", "4334", "2000");
		Patron patron = new Patron(1, "abc", "374573545", "fds@dff.com");		
		BorrowingRecord borrowingRecord = new BorrowingRecord(1, LocalDateTime.now(), null, book, patron);
		ResponseEntity serviceResponse = new ResponseEntity<BorrowingRecord>(borrowingRecord, HttpStatus.OK);
		when(borrowService.borrowBook(1,1)).thenReturn(serviceResponse);
		ResponseEntity<?> response = borrowController.borrowBookFromLibrary(1,1);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(response.getStatusCodeValue(), 200);
	}
	
	/**
	 * To test return book
	 * 
	 * @throws BookNotFoundException
	 * @throws PatronNotFoundException
	 * @throws BorrowRecordNotFoundException
	 */
	public void testBook() throws BookNotFoundException, PatronNotFoundException, BorrowRecordNotFoundException {
		Book book = new Book(1, "story book", "Abc", "4334", "2000");
		Patron patron = new Patron(1, "abc", "374573545", "fds@dff.com");		
		BorrowingRecord borrowingRecord = new BorrowingRecord(1, LocalDateTime.now(), LocalDateTime.now(), book, patron);
		ResponseEntity serviceResponse = new ResponseEntity<BorrowingRecord>(borrowingRecord, HttpStatus.OK);
		when(borrowService.returnBook(1,1)).thenReturn(serviceResponse);
		ResponseEntity<?> response = borrowController.returnBookToLibrary(1,1);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(response.getStatusCodeValue(), 200);

	}

}
