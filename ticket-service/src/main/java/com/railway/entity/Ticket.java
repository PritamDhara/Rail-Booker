package com.railway.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "tickets")
public class Ticket {
	

	@Transient
    public static final String SEQUENCE_NAME = "ticket_sequence";
	
	@Id
	private int ticketId;
	private String pnrNo;
	private int noOfSeats;
	private List<Passenger> passengers;
	private double totalFare;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date bookingDate;
	private String quota;
	private String trainNo;
	private String source;
	private String destination;
	private String time;
	private String userEmail;
	
	public Ticket(String pnrNo, int noOfSeats, double totalFare, Date bookingDate, String quota, String trainNo,
			String source, String destination, String time, String userEmail) {
		super();
		this.pnrNo = pnrNo;
		this.noOfSeats = noOfSeats;
		this.totalFare = totalFare;
		this.bookingDate = bookingDate;
		this.quota = quota;
		this.trainNo = trainNo;
		this.source = source;
		this.destination = destination;
		this.time = time;
		this.userEmail = userEmail;
	}

	public Ticket(int ticketId, int noOfSeats, List<Passenger> passengers, Date bookingDate, String quota,
			String trainNo, String userEmail) {
		super();
		this.ticketId = ticketId;
		this.noOfSeats = noOfSeats;
		this.passengers = passengers;
		this.bookingDate = bookingDate;
		this.quota = quota;
		this.trainNo = trainNo;
		this.userEmail = userEmail;
	}
	
	
	
	
	
	

}
