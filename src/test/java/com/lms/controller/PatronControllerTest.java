package com.lms.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.lms.exceptions.PatronNotFoundException;
import com.lms.model.Patron;
import com.lms.service.impl.PatronServiceImpl;

/**
 * Test class for PatronController
 * 
 */
@SpringBootTest
public class PatronControllerTest {
	@Mock
	private PatronServiceImpl patronService;

	@InjectMocks
	private PatronController patronController;

	/**
	 * To test addition of a patron to library
	 * 
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testAddPatron() {
		Patron patron = new Patron(1, "abc", "374573545", "fds@dff.com");
		ResponseEntity serviceResponse = new ResponseEntity<Patron>(patron, HttpStatus.OK);
		when(patronService.addPatron(patron)).thenReturn(serviceResponse);
		ResponseEntity<?> response = patronController.addPatron(patron);
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
		when(patronService.getPatron(1)).thenReturn(serviceResponse);
		ResponseEntity<?> response = patronController.getPatron(1);
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
		PatronNotFoundException patronNotFoundException = new PatronNotFoundException("Patron 1 Not Found");
		when(patronService.getPatron(1)).thenThrow(patronNotFoundException);
		Assertions.assertThrows(PatronNotFoundException.class, () -> patronController.getPatron(1));

	}

	/**
	 * To test update patron
	 * 
	 * @throws PatronNotFoundException
	 */
	@Test
	public void testUpdatePatron() throws PatronNotFoundException {
		Patron newPatron = new Patron(1, "abc", "374573545", "fds@dff.com");
		ResponseEntity serviceResponse = new ResponseEntity<Patron>(newPatron, HttpStatus.OK);
		when(patronService.updatePatron(1, newPatron)).thenReturn(serviceResponse);
		ResponseEntity<?> response = patronController.updatePatron(1, newPatron);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(response.getStatusCodeValue(), 200);

	}

	/**
	 * To test delete patron
	 * 
	 * @throws PatronNotFoundException
	 */
	@Test
	public void testDeletePatron() throws PatronNotFoundException {
		ResponseEntity serviceResponse = new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
		when(patronService.deletePatron(1)).thenReturn(serviceResponse);
		ResponseEntity<?> response = patronController.deletePatron(1);
		Assertions.assertNotNull(response);
		Assertions.assertEquals(response.getBody(), "Deleted Successfully");

	}
}
