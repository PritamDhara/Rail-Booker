package com.railway.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import com.railway.entity.Ticket;
import com.railway.service.SequenceGeneratorService;

public class TicketModelListener extends AbstractMongoEventListener<Ticket>{
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	  @Override
	    public void onBeforeConvert(BeforeConvertEvent<Ticket> event) {
	        if (event.getSource().getTicketId() < 1) {
	            event.getSource().setTicketId(sequenceGenerator.generateSequence(Ticket.SEQUENCE_NAME));
	        }
	    }
}
