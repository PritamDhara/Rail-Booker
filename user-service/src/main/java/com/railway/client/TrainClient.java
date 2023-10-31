package com.railway.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.railway.dto.Train;
import com.railway.errordecoder.CustomErrorDecoder;

@FeignClient(url = "http://localhost:9091/train",name = "train-service",configuration = CustomErrorDecoder.class)
public interface TrainClient {
	
	
	@PostMapping("/addTrain")
	public ResponseEntity<Train> addTrain(@RequestBody Train train);

	@PutMapping("/updateTrain")
	public ResponseEntity<Train> updateTrain(@RequestBody Train train);

	@DeleteMapping("/removeTrain/{trainNo}")
	public ResponseEntity<String> removeTrain(@PathVariable String trainNo);

	@GetMapping("/searchTrainByTrainNo/{trainNo}")
	public ResponseEntity<Train> searchTrainByTrainNo(@PathVariable String trainNo);

	@GetMapping("/viewTrainBySourceAndDestination/{source}/{destination}")
	public ResponseEntity<List<Train>> searchTrainBySourceAndDestination(@PathVariable String source,@PathVariable String destination);
	@GetMapping("/viewTrainByTrainName/{trainName}")
	public ResponseEntity<List<Train>> searchTrainByTrainName(@PathVariable String trainName);

	@GetMapping("/viewAvailableSeatsByTrainNo/{trainNo}")
	public ResponseEntity<Map<String, Integer>> viewAvailableSeatsByTrainNo(@PathVariable String trainNo);

	@GetMapping("/viewSeatWisePriceByTrainNo/{trainNo}")
	public ResponseEntity<Map<String, Double>> viewSeatWisePriceByTrainNo(@PathVariable String trainNo);
	@GetMapping("/viewAllTrains")
	public ResponseEntity<List<Train>> viewAllTrains() ;

	@GetMapping("/decreaseTrainSeats/{noOfSeats}/{cls}/{trainNo}")
	public ResponseEntity<Train> decreseTrainSeats(@PathVariable int noOfSeats,@PathVariable String cls,@PathVariable String trainNo);

	@GetMapping("/increaseTrainSeats/{noOfSeats}/{cls}/{trainNo}")
	public ResponseEntity<Train> increseTrainSeats(@PathVariable int noOfSeats,@PathVariable String cls,@PathVariable String trainNo);
}
