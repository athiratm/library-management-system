package com.lms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.exceptions.LibraryException;
import com.lms.exceptions.PatronNotFoundException;
import com.lms.model.Patron;
import com.lms.repository.PatronRepository;
import com.lms.service.PatronService;

import jakarta.transaction.Transactional;

@Service
public class PatronServiceImpl implements PatronService {

	@Autowired
	private PatronRepository patronRepository;

	/**
	 * To add a patron
	 * 
	 */
	@Override
	@Transactional
	public ResponseEntity<?> addPatron(Patron patron) {

		Patron newPatron = patronRepository.save(patron);
		return new ResponseEntity<Patron>(newPatron, HttpStatus.OK);

	}

	/**
	 * To retrieve a patron
	 * 
	 */
	@Override
	public ResponseEntity<?> getPatron(Integer id) throws PatronNotFoundException {
		Optional<Patron> patron = findPatron(id);
		if (patron.isPresent()) {
			return new ResponseEntity<Patron>(patron.get(), HttpStatus.OK);
		} else {
			throw new PatronNotFoundException("Patron " + id + " Not Found");
		}

	}

	/**
	 * To retrieve all patrons
	 * 
	 * 
	 */
	@Override
	public ResponseEntity<?> getAllPatrons() throws LibraryException {
		try {
			List<Patron> patrons = patronRepository.findAll();
			return new ResponseEntity<List<Patron>>(patrons, HttpStatus.OK);
		} catch (Exception e) {
			throw new LibraryException("Exception occured : " + e.getMessage());
		}
	}

	/**
	 * To update a patron
	 * 
	 */
	@Override
	@Transactional
	public ResponseEntity<?> updatePatron(Integer id, Patron patron) throws PatronNotFoundException {
		Patron updatedPatron = null;

		Optional<Patron> optionalPatron = findPatron(id);
		if (optionalPatron.isPresent()) {
			Patron existingPatron = optionalPatron.get();
			existingPatron.setName(patron.getName());
			existingPatron.setPhone(patron.getPhone());
			existingPatron.setEmail(patron.getEmail());

			updatedPatron = patronRepository.save(existingPatron);
			return new ResponseEntity<Patron>(updatedPatron, HttpStatus.OK);
		} else {
			throw new PatronNotFoundException("Patron " + id + " Not Found");
		}

	}

	/**
	 * To delete a patron
	 * 
	 */
	@Override
	@Transactional
	public ResponseEntity<?> deletePatron(Integer id) throws PatronNotFoundException {

		Optional<Patron> patron = findPatron(id);
		if (patron.isPresent()) {
			patronRepository.deleteById(id);
			return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
		} else {
			throw new PatronNotFoundException("Patron " + id + " Not Found");
		}

	}

	/**
	 * To find a patron using ID
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Patron> findPatron(Integer id) {
		Optional<Patron> optionalPatron = patronRepository.findById(id);
		return optionalPatron;
	}

}
