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

import com.lms.exceptions.PatronNotFoundException;
import com.lms.exceptions.LibraryException;
import com.lms.model.Patron;
import com.lms.service.impl.PatronServiceImpl;

@RestController
@RequestMapping("/patronapi")
public class PatronController {

	@Autowired
	private PatronServiceImpl patronService;

	/**
	 * To add a patron
	 * 
	 * @param patron
	 * @return
	 */
	@PostMapping(path = { "/add" })
	public ResponseEntity<?> addPatron(@RequestBody Patron patron) {

		return patronService.addPatron(patron);

	}

	/**
	 * To retrieve a patron
	 * 
	 * @param id
	 * @return
	 * @throws PatronNotFoundException
	 */
	@GetMapping(path = { "/patrons/{id}" })
	public ResponseEntity<?> getPatron(@PathVariable("id") Integer id) throws PatronNotFoundException {

		return patronService.getPatron(id);

	}

	/**
	 * To retrieve all patrons
	 * 
	 * @return
	 * @throws LibraryException
	 */
	@GetMapping(path = { "/patrons" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAll() throws LibraryException {

		return patronService.getAllPatrons();

	}

	/**
	 * To delete a patron
	 * 
	 * @param id
	 * @return
	 * @throws PatronNotFoundException
	 */
	@DeleteMapping(path = { "/patrons/{id}" })
	public ResponseEntity<?> deletePatron(@PathVariable("id") Integer id) throws PatronNotFoundException {

		return patronService.deletePatron(id);

	}

	/**
	 * To update a patron
	 * 
	 * @param id
	 * @param patron
	 * @return
	 * @throws PatronNotFoundException
	 */
	@PutMapping(path = { "/patrons/{id}" })
	public ResponseEntity<?> updatePatron(@PathVariable("id") Integer id, @RequestBody Patron patron)
			throws PatronNotFoundException {

		return patronService.updatePatron(id, patron);

	}
}
