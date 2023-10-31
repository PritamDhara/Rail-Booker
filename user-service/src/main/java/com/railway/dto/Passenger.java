package com.railway.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Passenger {
	
	private String name;
	private String mobileNo;
	private int age;
	private String aadhar;

}
