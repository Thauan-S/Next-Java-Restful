package com.tropical.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserAlreadyUsedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public UserAlreadyUsedException() {
		super("este usuário já está sendo usado, por favor escolha outro");
	}
	public UserAlreadyUsedException(String ex,Throwable cause ) {
		super(ex,cause);
	}
	
}
