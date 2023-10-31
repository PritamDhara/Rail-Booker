package com.railway.entity;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Document(collection = "trains")
public class Train {
	
	@Transient
	public static final String SEQUENCE_NAME = "train_sequence";
	
	@Id
	private int trainId;
	private String trainNo;
	private String sourceStation;
	private String destinationStation;
	private Map<String,Double> classWisePrice;
	private Map<String,Integer> classWiseSeat;
	private String trainName;
	private List<String> weeklyDays;
//	@Temporal(TemporalType.TIME)
//	@DateTimeFormat(style = "hh:mm")
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="hh:mm")
	private String time;
	
	public Train(String trainNo, String sourceStation, String destinationStation, Map<String, Double> classWisePrice,
			Map<String, Integer> classWiseSeat, String trainName, List<String> weeklyDays, String time) {
		super();
		this.trainNo = trainNo;
		this.sourceStation = sourceStation;
		this.destinationStation = destinationStation;
		this.classWisePrice = classWisePrice;
		this.classWiseSeat = classWiseSeat;
		this.trainName = trainName;
		this.weeklyDays = weeklyDays;
		this.time = time;
	}
	
	
	
}
