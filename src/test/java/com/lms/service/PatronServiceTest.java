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

import com.lms.exceptions.PatronNotFoundException;
import com.lms.model.Patron;
import com.lms.repository.PatronRepository;
import com.lms.service.impl.PatronServiceImpl;
/**
 * Test class for Patron service
 * 
 */
@SpringBootTest
public class PatronServiceTest {

	@Mock
	private PatronRepository patronRepository;

	@InjectMocks
	private PatronServiceImpl patronService;

	/**
	 * To test addition of a patron to library
	 * 
	 */
	@Test
	public void testAddPatron() {
		Patron patron = new Patron(1, "abc", "374573545", "fds@dff.com");
		when(patronRepository.save(patron)).thenReturn(patron);

		ResponseEntity<?> response = patronService.addPatron(patron);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(response.getStatusCodeValue(), 200);
	}

	/**
	 * To test get patron
	 * 
	 * @throws PatronNotFoundException
	 */
	@Test
	public void testGetPatron() throws PatronNotFoundException {
		Patron patron = new Patron(1, "abc", "374573545", "fds@dff.com");
		ResponseEntity serviceResponse = new ResponseEntity<Patron>(patron, HttpStatus.OK);
		when(patronService.findPatron(1)).thenReturn(Optional.of(patron));
		ResponseEntity<?> response = patronService.getPatron(1);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(response.getStatusCodeValue(), 200);

	}

	/**
	 * To test scanrio of patron with given id not found
	 * 
	 * @throws PatronNotFoundException
	 */
	@Test
	public void testGetPatron_NotFound() throws PatronNotFoundException {
		
		when(patronService.findPatron(1)).thenReturn(Optional.empty());
		PatronNotFoundException patronNotFoundException = new PatronNotFoundException("Patron 1 Not Found");
		Assertions.assertThrows(PatronNotFoundException.class, () -> patronService.getPatron(1));

	}

	/**
	 * To test update patron
	 * 
	 * @throws PatronNotFoundException
	 */
	@Test
	public void testUpdatePatron() throws PatronNotFoundException {
		Patron existingPatron = new Patron(1, "abc", "374573545", "fds@dff.com");
		when(patronService.findPatron(1)).thenReturn(Optional.of(existingPatron));
		Patron newPatron = new Patron(1, "abc", "3432424333", "fds@dff.com");
		when(patronRepository.save(newPatron)).thenReturn(newPatron);
		ResponseEntity serviceResponse = new ResponseEntity<Patron>(newPatron, HttpStatus.OK);
		ResponseEntity<?> response = patronService.updatePatron(1, newPatron);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(response.getStatusCodeValue(), 200);

	}

	
}
