package com.railway.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.railway.entity.Ticket;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, Integer> {
	
	Optional<Ticket> findByPnrNo(String pnrNo);
	
	void deleteByPnrNo(String pnrNo);
	

	List<Ticket> findByUserEmail(String userEmail);

}
