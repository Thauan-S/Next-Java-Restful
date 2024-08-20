package com.tropical.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public UserAlreadyExistsException() {
		super("The user already exists, please choose another one");
	}
	public UserAlreadyExistsException(String ex,Throwable cause ) {
		super(ex,cause);
	}
	
}
