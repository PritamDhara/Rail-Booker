package com.railway.exceptionhandler;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.railway.exceptions.NoSeatAvailableException;
import com.railway.exceptions.ResourceAlreadyExistException;
import com.railway.exceptions.ResourceNotFoundException;
import com.railway.exceptions.TicketAlreadyExistException;
import com.railway.exceptions.TicketNotFoundWithThePNRException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class TrainExceptionHandler {
	
	@ExceptionHandler(TicketAlreadyExistException.class)
	public ResponseEntity<ExceptionResponse> handleTicketAlreadyExistException(TicketAlreadyExistException ex , WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), "Ticket Already Exist", request.getDescription(false), "Already Exist");
		log.error("Ticket is Already Exist");
		return new ResponseEntity<ExceptionResponse>(exp,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(TicketNotFoundWithThePNRException.class)
	public ResponseEntity<ExceptionResponse> handleTicketNotFoundWithThePNRException(TicketNotFoundWithThePNRException ex,WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), "Ticket Not Found with same PNR", request.getDescription(false), "Not Found");
		log.error("Ticket Not Found with same PNR" + ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exp,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSeatAvailableException.class)
	public ResponseEntity<ExceptionResponse> handleNoSeatAvailableException(NoSeatAvailableException ex , WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), "No Seat Available", request.getDescription(false), "Not Found");
		log.error("No Seats are availale"+ ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exp,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex , WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false), "Not Found");
		log.error("Not Found"+ ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exp,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<ExceptionResponse> handleResourceAlreadyExistException(ResourceAlreadyExistException ex , WebRequest request)
	{
		ExceptionResponse exp = new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false), "Already Exist");
		log.error("Already Exist"+ ex.getMessage());
		return new ResponseEntity<ExceptionResponse>(exp,HttpStatus.NOT_FOUND);
	}

}
