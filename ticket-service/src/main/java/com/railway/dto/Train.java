package com.railway.dto;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data


public class Train {

	private int trainId;
	private String trainNo;
	private String sourceStation;
	private String destinationStation;
	private Map<String,Double> classWisePrice;
	private Map<String,Integer> classWiseSeat;
	private String trainName;
	private List<String> weeklyDays;
	private LocalTime time;
}
