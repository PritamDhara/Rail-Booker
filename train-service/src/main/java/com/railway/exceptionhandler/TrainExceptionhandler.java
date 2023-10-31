package com.railway.exceptionhandler;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.railway.exceptions.TrainAlreadyExistException;
import com.railway.exceptions.TrainNotFoundException;

@RestControllerAdvice
public class TrainExceptionhandler {
	
	@ExceptionHandler(TrainAlreadyExistException.class)
	public ResponseEntity<ExceptionResponse> handleTrainAlreadyExistException(TrainAlreadyExistException ex, WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), "Train is already Exist", request.getDescription(false), "Already Exist");
		return new ResponseEntity<ExceptionResponse>(exp, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(TrainNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleTrainNotFoundException(TrainNotFoundException ex , WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), "Train is not found", request.getDescription(false), "Not Found");
		return new ResponseEntity<ExceptionResponse>(exp, HttpStatus.NOT_FOUND);
	}

}
