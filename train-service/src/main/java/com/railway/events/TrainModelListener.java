package com.railway.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import com.railway.entity.Train;
import com.railway.service.SequenceGeneratorService;

public class TrainModelListener extends AbstractMongoEventListener<Train>{
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	  @Override
	    public void onBeforeConvert(BeforeConvertEvent<Train> event) {
	        if (event.getSource().getTrainId() < 1) {
	            event.getSource().setTrainId(sequenceGenerator.generateSequence(Train.SEQUENCE_NAME));
	        }
	    }
}
