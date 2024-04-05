package com.lms.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.exceptions.LibraryException;
import com.lms.exceptions.PatronNotFoundException;
import com.lms.model.Patron;

@Service
public interface PatronService {

	public ResponseEntity<?> addPatron(Patron patron);

	public ResponseEntity<?> getPatron(Integer id) throws PatronNotFoundException;

	public ResponseEntity<?> getAllPatrons() throws LibraryException;

	public ResponseEntity<?> updatePatron(Integer id, Patron patron) throws PatronNotFoundException;

	public ResponseEntity<?> deletePatron(Integer id) throws PatronNotFoundException;

}
