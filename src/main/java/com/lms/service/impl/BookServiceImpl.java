package com.lms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.exceptions.BookNotFoundException;
import com.lms.exceptions.LibraryException;
import com.lms.model.Book;
import com.lms.repository.BookRepository;
import com.lms.service.BookService;

import jakarta.transaction.Transactional;

/**
 * Implementation of BookService interface
 * 
 */
@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	/**
	 * To add a book
	 * 
	 */
	@Transactional
	@Override
	public ResponseEntity<?> addBookToLibrary(Book book) {

		Book newBook = bookRepository.save(book);
		return new ResponseEntity<Book>(newBook, HttpStatus.OK);

	}

	/**
	 * To retrieve a book
	 * 
	 */
	@Override
	public ResponseEntity<?> getBookFromLibrary(Integer id) throws BookNotFoundException {

		Optional<Book> book = findBook(id);
		if (book.isPresent()) {
			return new ResponseEntity<Book>(book.get(), HttpStatus.OK);
		} else {
			throw new BookNotFoundException("Book " + id + " Not Found");
		}

	}

	/**
	 * To retrieve all books
	 * 
	 */
	@Override
	public ResponseEntity<?> getAllBooks() throws LibraryException {

		try {
			List<Book> books = bookRepository.findAll();
			return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
		} catch (Exception e) {
			throw new LibraryException("Exception occured while fetching books: " + e.getMessage());
		}

	}

	/**
	 * To delete a book
	 * 
	 */
	@Transactional
	@Override
	public ResponseEntity<?> deleteBook(Integer id) throws BookNotFoundException {

		Optional<Book> book = findBook(id);
		if (book.isPresent()) {
			bookRepository.deleteById(id);
			return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
		} else {
			throw new BookNotFoundException("Book " + id + " Not Found");
		}

	}

	/**
	 * To update a book
	 * 
	 */
	@Transactional
	@Override
	public ResponseEntity<?> updateBook(Integer id, Book book) throws BookNotFoundException {
		Book updatedBook = null;

		Optional<Book> optionalBook = findBook(id);
		if (optionalBook.isPresent()) {
			Book existingBook = optionalBook.get();
			existingBook.setIsbn(book.getIsbn());
			existingBook.setAuthor(book.getAuthor());
			existingBook.setTitle(book.getTitle());
			existingBook.setYearOfPublication(book.getYearOfPublication());
			updatedBook = bookRepository.save(existingBook);
			return new ResponseEntity<Book>(updatedBook, HttpStatus.OK);
		} else {
			throw new BookNotFoundException("Book " + id + " Not Found");

		}

	}

	/**
	 * To find a book using it's ID
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Book> findBook(Integer id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		return optionalBook;
	}
}
