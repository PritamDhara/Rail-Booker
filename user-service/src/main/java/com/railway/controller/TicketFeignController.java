package com.railway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.railway.client.TicketClient;
import com.railway.dto.Ticket;

@RestController
@RequestMapping("/ticket")
@EnableFeignClients(basePackages = "com.railway.client")
@CrossOrigin
public class TicketFeignController {
	
	@Autowired
	TicketClient ticketClient;
	
	
	@PostMapping("/createTicket")
//	@PreAuthorize("hasAnyRole('USER')")
	public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
		
		return  new ResponseEntity<Ticket>(ticketClient.createTicket(ticket).getBody(),HttpStatus.OK);
	}

	@DeleteMapping("/cancelTicket/{pnr}")
//	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<String> deleteTicket(@PathVariable String pnr){

		String msg = ticketClient.deleteTicket(pnr).getBody();
		return new ResponseEntity<String>(msg,HttpStatus.OK) ;
	}

	@GetMapping("/viewTicketByPnr/{pnr}")
//	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<Ticket> viewTicketByPNR(@PathVariable String pnr){

		Ticket ticket = ticketClient.viewTicketByPNR(pnr).getBody();
		
		return new ResponseEntity<Ticket>(ticket,HttpStatus.OK);
	}

	@GetMapping("/viewTicketsByMail/{userEmail}")
//	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public ResponseEntity<List<Ticket>> viewTicketByUserEmail(@PathVariable String userEmail){

		List<Ticket> tickets = ticketClient.viewTicketByUserEmail(userEmail).getBody();
		return new ResponseEntity<List<Ticket>>(tickets,HttpStatus.OK);
	}
	
	@GetMapping("/viewAllTicket")
	public ResponseEntity<List<Ticket>> viewAllTicket(){
		List<Ticket> tickets = ticketClient.viewAllTicket().getBody();
		return new ResponseEntity<List<Ticket>>(tickets,HttpStatus.OK);
	}

}
