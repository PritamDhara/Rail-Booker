package com.railway.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import com.railway.client.TrainClient;
import com.railway.dto.Train;
import com.railway.entity.Ticket;
import com.railway.exceptions.NoSeatAvailableException;
import com.railway.exceptions.NoTicketsAvailableException;
import com.railway.exceptions.TicketAlreadyExistException;
import com.railway.exceptions.TicketNotFoundWithThePNRException;
import com.railway.repository.TicketRepository;

@Service
@EnableFeignClients(basePackages = "com.railway.client")
public class TicketServiceImpl implements ITicketService {
	
	@Autowired
	TicketRepository ticketRepo;
	
	@Autowired
	TrainClient trainClient;
	
	@Autowired
	SequenceGeneratorService sequenceGenerator;

	@Override
	public Optional<Ticket> createTicket(Ticket ticket) throws TicketAlreadyExistException, NoSeatAvailableException {
		
		
		if(ticketRepo.existsById(ticket.getTicketId()))
		{
			throw new TicketAlreadyExistException();
		}
		
		
		ticket.setTicketId(sequenceGenerator.generateSequence(Ticket.SEQUENCE_NAME));
		
		String pnr = ticket.getTrainNo() + ticket.getQuota()+ticket.getUserEmail().substring(0, 5)+ticket.getTicketId();
		
		Map<String,Double> price = trainClient.viewSeatWisePriceByTrainNo(ticket.getTrainNo()).getBody();
		
		Train train = trainClient.searchTrainByTrainNo(ticket.getTrainNo()).getBody();
		
		Map<String,Integer> seats = train.getClassWiseSeat();
		
		if(seats.get(ticket.getQuota())<=0)
		{
			throw new NoSeatAvailableException();
		}
		
		
		double totalPrice = ticket.getNoOfSeats() * price.get(ticket.getQuota());
		
		ticket.setPnrNo(pnr);
		ticket.setTotalFare(totalPrice);
		ticket.setSource(train.getSourceStation());
		ticket.setDestination(train.getDestinationStation());
		ticket.setTime(train.getTime().toString());
		
		trainClient.decreseTrainSeats(ticket.getNoOfSeats(), ticket.getQuota(), ticket.getTrainNo());
		
		Ticket ti = ticketRepo.save(ticket);
		
		return Optional.of(ti);
	}

	@Override
	public String deleteTicket(String pnr) throws TicketNotFoundWithThePNRException {
		
		Optional<Ticket> ticket = ticketRepo.findByPnrNo(pnr);
		
		if(ticket.isEmpty())
		{
			throw new TicketNotFoundWithThePNRException();
		}
		
		Ticket t = ticket.get();
		
		trainClient.increseTrainSeats(t.getNoOfSeats(),t.getQuota(), t.getTrainNo());
		ticketRepo.deleteByPnrNo(pnr);
		
		return "Ticket Deleted Successfully";
	}

	@Override
	public Optional<Ticket> viewTicketByPNR(String pnr) throws TicketNotFoundWithThePNRException {
		
		Optional<Ticket> ticket = ticketRepo.findByPnrNo(pnr);
		
		if(ticket.isEmpty())
		{
			throw new TicketNotFoundWithThePNRException();
		}
		
		return ticket;
	}

	@Override
	public List<Ticket> viewTicketByUserEmail(String userEmail) throws NoTicketsAvailableException {
		
		List<Ticket> tickets = ticketRepo.findByUserEmail(userEmail);
		if(tickets.isEmpty())
		{
			throw new NoTicketsAvailableException();
		}
		return tickets;
	}

	@Override
	public List<Ticket> viewAllTicket() {
		return ticketRepo.findAll();
	}

}
