package com.railway.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.railway.dto.Ticket;
import com.railway.errordecoder.CustomErrorDecoder;

@FeignClient(url = "http://localhost:9093/ticket",name = "ticket-service",configuration = CustomErrorDecoder.class)
public interface TicketClient {
	
	@PostMapping("/createTicket")
	public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket);

	@DeleteMapping("/cancelTicket/{pnr}")
	public ResponseEntity<String> deleteTicket(@PathVariable String pnr);

	@GetMapping("/viewTicketByPnr/{pnr}")
	public ResponseEntity<Ticket> viewTicketByPNR(@PathVariable String pnr);

	@GetMapping("/viewTicketsByMail/{userEmail}")
	public ResponseEntity<List<Ticket>> viewTicketByUserEmail(@PathVariable String userEmail);
	
	@GetMapping("/viewAllTicket")
	public ResponseEntity<List<Ticket>> viewAllTicket();

}
