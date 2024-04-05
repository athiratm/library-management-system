package com.lms.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lms.exceptions.BookNotFoundException;
import com.lms.model.Book;
import com.lms.service.impl.BookServiceImpl;

/**
 * Test class for Book Controller
 * 
 */
@SpringBootTest
public class BookControllerTest {

	@Mock
	private BookServiceImpl bookService;

	@InjectMocks
	private BookController bookController;

	/**
	 * To test addition of a book to library
	 * 
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testAddBook() {
		Book book = new Book(1, "story book", "Abc", "4334", "2000");
		ResponseEntity serviceResponse = new ResponseEntity<Book>(book, HttpStatus.OK);
		when(bookService.addBookToLibrary(book)).thenReturn(serviceResponse);
		ResponseEntity<?> response = bookController.addBook(book);
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
		when(bookService.getBookFromLibrary(1)).thenReturn(serviceResponse);
		ResponseEntity<?> response = bookController.getBook(1);
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
		BookNotFoundException bookNotFoundException = new BookNotFoundException("Book 1 Not Found");
		when(bookService.getBookFromLibrary(1)).thenThrow(bookNotFoundException);
		Assertions.assertThrows(BookNotFoundException.class, () -> bookController.getBook(1));

	}

	/**
	 * To test update book
	 * 
	 * @throws BookNotFoundException
	 */
	@Test
	public void testUpdateBook() throws BookNotFoundException {
		Book newBook = new Book(1, "story book bbb", "Abc", "4334", "2000");
		ResponseEntity serviceResponse = new ResponseEntity<Book>(newBook, HttpStatus.OK);
		when(bookService.updateBook(1, newBook)).thenReturn(serviceResponse);
		ResponseEntity<?> response = bookController.updateBook(1, newBook);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(response.getStatusCodeValue(), 200);

	}

	/**
	 * To test delete book
	 * 
	 * @throws BookNotFoundException
	 */
	@Test
	public void testDeleteBook() throws BookNotFoundException {
		ResponseEntity serviceResponse = new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
		when(bookService.deleteBook(1)).thenReturn(serviceResponse);
		ResponseEntity<?> response = bookController.deleteBook(1);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(response.getBody(), "Deleted Successfully");

	}
}
