package com.railway.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Ticket {
	
	private int ticketId;
	private String pnrNo;
	private int noOfSeats;
	private List<Passenger> passengers;
	private double totalFare;
	private String bookingDate;
	private String quota;
	private String trainNo;
	private String source;
	private String destination;
	private String time;
	private String userEmail;
	

	
	
	

}
