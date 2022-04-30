package com.ezoqc.defijava.exceptions;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L; // Adicionei
	
	public NotFoundException(String message) {
		super(message);
	}
}
