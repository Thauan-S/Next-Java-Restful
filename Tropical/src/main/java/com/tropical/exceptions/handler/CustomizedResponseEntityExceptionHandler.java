package com.tropical.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tropical.exceptions.ExceptionResponse;
import com.tropical.exceptions.ForbiddenAccesException;
import com.tropical.exceptions.InvalidJwtAuthenticationException;
import com.tropical.exceptions.RequiredObjectIsNullException;
import com.tropical.exceptions.ResourceNotFoundException;
import com.tropical.exceptions.UserAlreadyExistsException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exeptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exeptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> ResourceNotFoundException(Exception ex, WebRequest request) {
		ExceptionResponse exeptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exeptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RequiredObjectIsNullException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestException(Exception ex, WebRequest request) {
		ExceptionResponse exeptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exeptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	public final ResponseEntity<ExceptionResponse> handleInvalidJwtAuthenticationException(Exception ex,
			WebRequest request) {
		ExceptionResponse exeptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exeptionResponse, HttpStatus.FORBIDDEN);
	}
	@ExceptionHandler(UserAlreadyExistsException.class)
	public final ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(Exception ex,
			WebRequest request) {
		ExceptionResponse exeptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exeptionResponse, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ForbiddenAccesException.class)
	public final ResponseEntity<ExceptionResponse> handleForbiddenAccesException(Exception ex,
			WebRequest request) {
		ExceptionResponse exeptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(exeptionResponse, HttpStatus.FORBIDDEN);
	}

}
