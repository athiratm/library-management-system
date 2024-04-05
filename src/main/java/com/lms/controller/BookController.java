package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.exceptions.BookNotFoundException;
import com.lms.exceptions.LibraryException;
import com.lms.model.Book;
import com.lms.service.impl.BookServiceImpl;

@RestController
@RequestMapping("/bookapi")
public class BookController {

	@Autowired
	private BookServiceImpl bookService;

	/**
	 * To add a book to library
	 * 
	 * @param book
	 * @return
	 */
	@PostMapping(path = { "/add" })
	public ResponseEntity<?> addBook(@RequestBody Book book) {

		return bookService.addBookToLibrary(book);

	}

	/**
	 * To retrieve a book
	 * 
	 * @param id
	 * @return
	 * @throws BookNotFoundException
	 */
	@GetMapping(path = { "/books/{id}" })
	public ResponseEntity<?> getBook(@PathVariable("id") Integer id) throws BookNotFoundException {

		return bookService.getBookFromLibrary(id);

	}

	/**
	 * To retrieve all books
	 * 
	 * @return
	 * @throws LibraryException
	 */
	@GetMapping(path = { "/books" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAll() throws LibraryException {

		return bookService.getAllBooks();

	}

	/**
	 * To delete a book from library
	 * 
	 * @param id
	 * @return
	 * @throws BookNotFoundException
	 */
	@DeleteMapping(path = { "/books/{id}" })
	public ResponseEntity<?> deleteBook(@PathVariable("id") Integer id) throws BookNotFoundException {

		return bookService.deleteBook(id);

	}

	/**
	 * To update a book
	 * 
	 * @param id
	 * @param book
	 * @return
	 * @throws BookNotFoundException
	 */
	@PutMapping(path = { "/books/{id}" })
	public ResponseEntity<?> updateBook(@PathVariable("id") Integer id, @RequestBody Book book)
			throws BookNotFoundException {

		return bookService.updateBook(id, book);

	}

}
