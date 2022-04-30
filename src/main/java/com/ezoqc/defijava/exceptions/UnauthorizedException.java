package com.ezoqc.defijava.exceptions;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 1L; // Adicionei
	
	public UnauthorizedException(String message) {
		super(message);
	}
	
	public UnauthorizedException(Throwable cause) {
		super(cause);
	}
}
