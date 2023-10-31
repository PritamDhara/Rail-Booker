package com.railway.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import com.railway.entity.User;
import com.railway.service.SequenceGeneratorService;

public class UserModelListener extends AbstractMongoEventListener<User>{
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	  @Override
	    public void onBeforeConvert(BeforeConvertEvent<User> event) {
	        if (event.getSource().getUserId() < 1) {
	            event.getSource().setUserId(sequenceGenerator.generateSequence(User.SEQUENCE_NAME));
	        }
	   }
}
