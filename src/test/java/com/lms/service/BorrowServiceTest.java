package com.lms.service;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

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
import com.lms.repository.BorrowingRecordRepository;
import com.lms.service.impl.BookServiceImpl;
import com.lms.service.impl.BorrowServiceImpl;
import com.lms.service.impl.PatronServiceImpl;

/**
 * Test class for Borrow service
 * 
 */
@SpringBootTest
public class BorrowServiceTest {

	@InjectMocks
	private BorrowServiceImpl borrowService;

	@Mock
	private BorrowingRecordRepository borrowingRecordRepository;
	
	@Mock
	private BookServiceImpl bookService;
	
	@Mock
	private PatronServiceImpl patronService;
	
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
		when(bookService.findBook(1)).thenReturn(Optional.of(book));
		when(patronService.findPatron(1)).thenReturn(Optional.of(patron));
		when(borrowingRecordRepository.save(borrowingRecord)).thenReturn(borrowingRecord);
		ResponseEntity<?> response = borrowService.borrowBook(1, 1);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(response.getStatusCodeValue(), 200);
	}
	
	/**
	 * To test return book
	 * @throws PatronNotFoundException 
	 * @throws BookNotFoundException 
	 * @throws LibraryException 
	 * @throws BorrowRecordNotFoundException 
	 * 
	 */
	@Test
	public void testReturnBook() throws LibraryException, BookNotFoundException, PatronNotFoundException, BorrowRecordNotFoundException {
		Book book = new Book(1, "story book", "Abc", "4334", "2000");
		Patron patron = new Patron(1, "abc", "374573545", "fds@dff.com");		
		BorrowingRecord borrowingRecord = new BorrowingRecord(1, LocalDateTime.now(), LocalDateTime.now(), book, patron);
		ResponseEntity serviceResponse = new ResponseEntity<BorrowingRecord>(borrowingRecord, HttpStatus.OK);
		when(bookService.findBook(1)).thenReturn(Optional.of(book));
		when(patronService.findPatron(1)).thenReturn(Optional.of(patron));
		when(borrowingRecordRepository.findByBookAndPatron(Optional.of(book), Optional.of(patron))).thenReturn(borrowingRecord);
		when(borrowingRecordRepository.save(borrowingRecord)).thenReturn(borrowingRecord);
		ResponseEntity<?> response = borrowService.returnBook(1, 1);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(response.getStatusCodeValue(), 200);
	}
	
	
	/**
	 * To testBorrowRecordNotFound
	 * @throws PatronNotFoundException 
	 * @throws BookNotFoundException 
	 * @throws LibraryException 
	 * @throws BorrowRecordNotFoundException 
	 * 
	 */
	@Test
	public void testBorrowRecordNotFound() throws LibraryException, BookNotFoundException, PatronNotFoundException, BorrowRecordNotFoundException {
		Book book = new Book(1, "story book", "Abc", "4334", "2000");
		Patron patron = new Patron(1, "abc", "374573545", "fds@dff.com");		
		BorrowingRecord borrowingRecord = new BorrowingRecord(1, LocalDateTime.now(), LocalDateTime.now(), book, patron);
		ResponseEntity serviceResponse = new ResponseEntity<BorrowingRecord>(borrowingRecord, HttpStatus.OK);
		when(bookService.findBook(1)).thenReturn(Optional.of(book));
		when(patronService.findPatron(1)).thenReturn(Optional.of(patron));
		when(borrowingRecordRepository.findByBookAndPatron(Optional.of(book), Optional.of(patron))).thenReturn(null);
		BorrowRecordNotFoundException borrowRecordNotFoundException = new BorrowRecordNotFoundException("Borrow Record Not Found");
		Assertions.assertThrows(BorrowRecordNotFoundException.class, () -> borrowService.returnBook(1,1));

	}
}
