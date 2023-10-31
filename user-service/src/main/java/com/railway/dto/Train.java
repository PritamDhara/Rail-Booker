package com.railway.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Train {
	
	private int trainId;
	private String trainNo;
	private String sourceStation;
	private String destinationStation;
	private Map<String,Double> classWisePrice;
	private Map<String,Integer> classWiseSeat;
	private String trainName;
	private List<String> weeklyDays;
	private String time;

}
