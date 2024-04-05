package com.lms.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.lms.exceptions.BookNotFoundException;
import com.lms.exceptions.LibraryException;
import com.lms.model.Book;

@Service
public interface BookService {

	public ResponseEntity<?> addBookToLibrary(Book book);

	public ResponseEntity<?> getBookFromLibrary(Integer id) throws BookNotFoundException;

	public ResponseEntity<?> getAllBooks() throws LibraryException;

	public ResponseEntity<?> updateBook(Integer id, Book book) throws BookNotFoundException;

	public ResponseEntity<?> deleteBook(Integer id) throws BookNotFoundException;

}
