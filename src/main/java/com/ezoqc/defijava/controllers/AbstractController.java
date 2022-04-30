package com.ezoqc.defijava.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractController {    
	protected <T> ResponseEntity<T> makeResponse(T data, HttpStatus httpStatus) {
		return new ResponseEntity<T>(data, httpStatus);
	}
	
	protected <T> ResponseEntity<T> ok(T data) {		
		return makeResponse(data,  HttpStatus.OK);
	}
	
	protected <T> ResponseEntity<T> badRequest(T data) {
		return makeResponse(data,  HttpStatus.BAD_REQUEST);
	}
	
	protected <T> ResponseEntity<T> notFound(T data) {
		return makeResponse(data,  HttpStatus.NOT_FOUND);
	}
	
	protected <T> ResponseEntity<T> serverError(T data) {
		return makeResponse(data,  HttpStatus.INTERNAL_SERVER_ERROR);
	}	
}
