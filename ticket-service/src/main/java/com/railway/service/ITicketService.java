package com.railway.service;

import java.util.List;
import java.util.Optional;

import com.railway.entity.Ticket;
import com.railway.exceptions.NoSeatAvailableException;
import com.railway.exceptions.NoTicketsAvailableException;
import com.railway.exceptions.TicketAlreadyExistException;
import com.railway.exceptions.TicketNotFoundWithThePNRException;

public interface ITicketService {
	
	Optional<Ticket> createTicket(Ticket ticket) throws TicketAlreadyExistException, NoSeatAvailableException;
	String deleteTicket(String pnr) throws TicketNotFoundWithThePNRException;
	Optional<Ticket> viewTicketByPNR(String pnr) throws TicketNotFoundWithThePNRException;
	List<Ticket> viewTicketByUserEmail(String userEmail) throws NoTicketsAvailableException;
	List<Ticket> viewAllTicket();

}
