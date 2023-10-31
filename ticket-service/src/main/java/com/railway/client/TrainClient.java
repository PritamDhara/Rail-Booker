package com.railway.client;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.railway.dto.Train;
import com.railway.errorDecoder.CustomErrorDecoder;

@FeignClient(url = "http://localhost:9091/train",name = "train-service",configuration = CustomErrorDecoder.class)
public interface TrainClient {

	@GetMapping("/viewSeatWisePriceByTrainNo/{trainNo}")
	public ResponseEntity<Map<String, Double>> viewSeatWisePriceByTrainNo(@PathVariable String trainNo);
	
	@GetMapping("/decreaseTrainSeats/{noOfSeats}/{cls}/{trainNo}")
	public ResponseEntity<Train> decreseTrainSeats(@PathVariable int noOfSeats,@PathVariable String cls,@PathVariable String trainNo);
	
	@GetMapping("/increaseTrainSeats/{noOfSeats}/{cls}/{trainNo}")
	public ResponseEntity<Train> increseTrainSeats(@PathVariable int noOfSeats,@PathVariable String cls,@PathVariable String trainNo);

	@GetMapping("/searchTrainByTrainNo/{trainNo}")
	public ResponseEntity<Train> searchTrainByTrainNo(@PathVariable String trainNo);
}
