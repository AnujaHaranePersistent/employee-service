package com.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND,reason="Employee Not Found")
public class ResourceNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFound() {
		super();
	}
	public ResourceNotFound(String message) {
		super(message);
	}
	

}
