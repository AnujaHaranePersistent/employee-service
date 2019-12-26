package com.employee.exceptionhandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.employee.exception.ResourceNotFound;


@ControllerAdvice
public class EmployeeExceptionHandler {
	
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<Object> resourceNotFound(ResourceNotFound exception){
		ErrorResponse e=new ErrorResponse();
		e.setMessage("Resource Not Found");
		e.setStatusCode(404);
		return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception ex, 
                HttpServletRequest request, HttpServletResponse response) {
		ErrorResponse e=new ErrorResponse();
        if (ex instanceof EmptyResultDataAccessException) {
        	
        	e.setMessage("Resource Not Found");
        	e.setStatusCode(500);
            return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
        }
        e.setMessage(ex.getMessage());
    	e.setStatusCode(404);
        return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
    }

}
