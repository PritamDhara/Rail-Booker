package com.railway.exceptionhandler;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.railway.exceptions.NoUserFoundException;
import com.railway.exceptions.ResourceAlreadyExistException;
import com.railway.exceptions.ResourceNotFoundException;
import com.railway.exceptions.UserAlreadyExistException;
import com.railway.exceptions.UserIsNotRegisteredException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class UserExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(NoUserFoundException.class)
	public ResponseEntity<ExceptionResponse> handleNoUserFound(NoUserFoundException ex, WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), "No User Found", request.getDescription(false), "Not Found");
		log.error("No User Found");
		return new ResponseEntity<ExceptionResponse>(exp, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<ExceptionResponse> handleUserAlreadyExistException(UserAlreadyExistException ex,WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), "User Already Exist", request.getDescription(false), "Not Acceptable");
		log.error("User Already Exist");
		return new ResponseEntity<ExceptionResponse>(exp, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(UserIsNotRegisteredException.class)
	public ResponseEntity<ExceptionResponse> handleUserIsNotRegisteredException(UserIsNotRegisteredException ex,WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), "User Is Not Registered", request.getDescription(false), "Not Found");
		log.error("User is not registered");
		return new ResponseEntity<ExceptionResponse>(exp, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex,WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false), "Not Found");
		log.error("Resource is not found");
		return new ResponseEntity<ExceptionResponse>(exp, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<ExceptionResponse> handleResourceAlreadyExistException(ResourceAlreadyExistException ex,WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false), "Already Exist");
		log.error("Resource is Already exist");
		return new ResponseEntity<ExceptionResponse>(exp, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		StringBuilder details = new StringBuilder();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			details.append(error.getField()).append(": ").append(error.getDefaultMessage()).append(". ");
		}
		ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDate.now(), "Validation fails",
				details.toString(), "Bad Request");
		log.error("Validation fails:", ex);
//        log.error(ex.getMessage());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
