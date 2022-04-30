package com.ezoqc.defijava.helpers.erros.structure;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiError {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String debugMessage;
	private List<ApiMoreError> moreErrors;
	private HttpStatus status;

	private ApiError() {
		timestamp = LocalDateTime.now();
	}

	public ApiError(HttpStatus status) {
		this();
		this.status = status;
	}

	public ApiError(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}

	public ApiError(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	public List<ApiMoreError> getMoreErrors() {
		return moreErrors;
	}

	public void setMoreErrors(List<ApiMoreError> moreErrors) {
		this.moreErrors = moreErrors;
	}

	private void addMoreError(ApiMoreError subError) {
		if (moreErrors == null) {
			moreErrors = new ArrayList<>();
		}
		moreErrors.add(subError);
	}

	private void addError(String object, String field, Object rejectedValue, String message) {
		addMoreError(new ApiValidation(object, field, rejectedValue, message));
	}

	private void addError(String object, String message) {
		addMoreError(new ApiValidation(object, message));
	}

	private void addError(FieldError fieldError) {
		this.addError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(),
				fieldError.getDefaultMessage());
	}

	public void addErrors(List<FieldError> fieldErrors) {
		fieldErrors.forEach(this::addError);
	}

	private void addError(ObjectError objectError) {
		this.addError(objectError.getObjectName(), objectError.getDefaultMessage());
	}

	public void addError(List<ObjectError> globalErrors) {
		globalErrors.forEach(this::addError);
	}

	private void addErrors(ConstraintViolation<?> cv) {
		this.addError(cv.getRootBeanClass().getSimpleName(),
				((PathImpl) cv.getPropertyPath()).getLeafNode().asString(), cv.getInvalidValue(), cv.getMessage());
	}

	public void addErrors(Set<ConstraintViolation<?>> constraintViolations) {
		constraintViolations.forEach(this::addErrors);
	}

}
