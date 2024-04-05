package com.lms.config;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.lms.exceptions.BookNotFoundException;
import com.lms.exceptions.BorrowRecordNotFoundException;
import com.lms.exceptions.LibraryException;
import com.lms.exceptions.PatronNotFoundException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ControllerAdvice
public class LibraryResponseEntityExceptionHandler {

	@ExceptionHandler(BookNotFoundException.class)
	public final ResponseEntity<LMSError> handleBookNotFoundException(BookNotFoundException ex, WebRequest request) {
		LMSError errorDetails = new LMSError(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(PatronNotFoundException.class)
	public final ResponseEntity<LMSError> handlePatronNotFoundException(PatronNotFoundException ex,
			WebRequest request) {
		LMSError errorDetails = new LMSError(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(BorrowRecordNotFoundException.class)
	public final ResponseEntity<LMSError> handleBorrowRecordNotFoundException(BorrowRecordNotFoundException ex,
			WebRequest request) {
		LMSError errorDetails = new LMSError(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(LibraryException.class)
	public final ResponseEntity<LMSError> handleLibraryException(LibraryException ex, WebRequest request) {
		LMSError errorDetails = new LMSError(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(ConnectException.class)
	public final ResponseEntity<LMSError> handleConnectException(ConnectException ex, WebRequest request) {
		LMSError errorDetails = new LMSError(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<LMSError> handleConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {
		Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
		List<String> messages = new ArrayList<>();
		for (ConstraintViolation violation : violations) {
			messages.add(violation.getPropertyPath() + " " + violation.getMessage());
		}

		LMSError errorDetails = new LMSError(new Date(), messages.toString(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<LMSError> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request) {

		LMSError errorDetails = new LMSError(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}

@AllArgsConstructor
@Getter
@Setter
class LMSError {
	private Date timestamp;
	private String message;
	private String details;
}