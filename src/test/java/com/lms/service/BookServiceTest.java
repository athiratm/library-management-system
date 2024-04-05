package com.lms.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lms.exceptions.BookNotFoundException;
import com.lms.model.Book;
import com.lms.repository.BookRepository;
import com.lms.service.impl.BookServiceImpl;
/**
 * Test class for Book service
 * 
 */
@SpringBootTest
public class BookServiceTest {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookServiceImpl bookService;

	/**
	 * To test addition of a book to library
	 * 
	 */
	@Test
	public void testAddBook() {
		Book book = new Book(1, "story book", "Abc", "4334", "2000");
		when(bookRepository.save(book)).thenReturn(book);

		ResponseEntity<?> response = bookService.addBookToLibrary(book);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(response.getStatusCodeValue(), 200);
	}

	/**
	 * To test get book
	 * 
	 * @throws BookNotFoundException
	 */
	@Test
	public void testGetBook() throws BookNotFoundException {
		Book book = new Book(1, "story book", "Abc", "4334", "2000");
		ResponseEntity serviceResponse = new ResponseEntity<Book>(book, HttpStatus.OK);
		when(bookService.findBook(1)).thenReturn(Optional.of(book));
		ResponseEntity<?> response = bookService.getBookFromLibrary(1);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(response.getStatusCodeValue(), 200);

	}

	/**
	 * To test scanrio of book with given id not found
	 * 
	 * @throws BookNotFoundException
	 */
	@Test
	public void testGetBook_NotFound() throws BookNotFoundException {
		
		when(bookService.findBook(1)).thenReturn(Optional.empty());
		BookNotFoundException bookNotFoundException = new BookNotFoundException("Book 1 Not Found");
		Assertions.assertThrows(BookNotFoundException.class, () -> bookService.getBookFromLibrary(1));

	}

	/**
	 * To test update book
	 * 
	 * @throws BookNotFoundException
	 */
	@Test
	public void testUpdateBook() throws BookNotFoundException {
		Book existingBook = new Book(1, "story book bbb", "Abc", "4334", "2000");
		when(bookService.findBook(1)).thenReturn(Optional.of(existingBook));
		Book newBook = new Book(1, "story book ccc", "Abc", "4334", "2000");
		when(bookRepository.save(newBook)).thenReturn(newBook);
		ResponseEntity serviceResponse = new ResponseEntity<Book>(newBook, HttpStatus.OK);
		ResponseEntity<?> response = bookService.updateBook(1, newBook);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(response.getStatusCodeValue(), 200);

	}

	
}
