package com.railway.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.railway.entity.Ticket;
import com.railway.exceptions.NoSeatAvailableException;
import com.railway.exceptions.NoTicketsAvailableException;
import com.railway.exceptions.TicketAlreadyExistException;
import com.railway.exceptions.TicketNotFoundWithThePNRException;
import com.railway.service.ITicketService;

@RestController
@RequestMapping("/ticket")
@CrossOrigin
public class TicketController{
	
	@Autowired
	ITicketService ticketService;

	
	@PostMapping("/createTicket")
	public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) throws TicketAlreadyExistException, NoSeatAvailableException {
		
		return  new ResponseEntity<Ticket>(ticketService.createTicket(ticket).get(),HttpStatus.OK);
	}

	@DeleteMapping("/cancelTicket/{pnr}")
	public ResponseEntity<String> deleteTicket(@PathVariable String pnr) throws TicketNotFoundWithThePNRException {

		String msg = ticketService.deleteTicket(pnr);
		return new ResponseEntity<String>(msg,HttpStatus.OK) ;
	}

	@GetMapping("/viewTicketByPnr/{pnr}")
	public ResponseEntity<Ticket> viewTicketByPNR(@PathVariable String pnr) throws TicketNotFoundWithThePNRException {

		Ticket ticket = ticketService.viewTicketByPNR(pnr).get();
		
		return new ResponseEntity<Ticket>(ticket,HttpStatus.OK);
	}

	@GetMapping("/viewTicketsByMail/{userEmail}")
	public ResponseEntity<List<Ticket>> viewTicketByUserEmail(@PathVariable String userEmail) throws NoTicketsAvailableException {

		List<Ticket> tickets = ticketService.viewTicketByUserEmail(userEmail);
		return new ResponseEntity<List<Ticket>>(tickets,HttpStatus.OK);
	}
	
	@GetMapping("/viewAllTicket")
	public ResponseEntity<List<Ticket>> viewAllTicket()
	{
		return new ResponseEntity<List<Ticket>>(ticketService.viewAllTicket(), HttpStatus.OK);
	}

}
