package com.employee.exceptionhandler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.employee.controller.EmployeeController;
import com.employee.exception.ResourceNotFound;




@ControllerAdvice
public class EmployeeExceptionHandler {
	private static final Logger logger = LogManager.getLogger(EmployeeController.class);

	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<Object> resourceNotFound(ResourceNotFound exception){
		ErrorResponse e=new ErrorResponse();
		e.setMessage("Resource Not Found");
		e.setStatusCode(404);
		logger.error("Resource Not Found Exception Raised");

		return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception ex, 
                HttpServletRequest request, HttpServletResponse response) {
		ErrorResponse e=new ErrorResponse();
        if (ex instanceof EmptyResultDataAccessException) {
        	
        	e.setMessage("Resource Not Found");
        	e.setStatusCode(500);
        	logger.error("Resource Not Found Exception Raised");
            return new ResponseEntity<>(e,HttpStatus.NOT_FOUND);
        }
        e.setMessage(ex.getMessage());
    	e.setStatusCode(404);
    	logger.error(ex.getMessage());
        return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
    }
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolationException(ConstraintViolationException ex) throws IOException {
	 String msg = "";
      for (ConstraintViolation<?> cv : ex.getConstraintViolations()) {
          msg+=cv.getPropertyPath()+" "+cv.getMessage() ;
      }
     
	  ErrorResponse e=new ErrorResponse();
		e.setMessage(msg);
    	logger.error(msg);

		return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
        
    }
}
