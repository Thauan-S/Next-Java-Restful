package com.tropical.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenAccesException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ForbiddenAccesException() {
		super("O usuário não possui permissão para realizar essa operação");
	}
	public ForbiddenAccesException(String ex) {
		super(ex);
	}
	public ForbiddenAccesException(String ex,Throwable cause) {
		super(ex,cause);
	}
}
