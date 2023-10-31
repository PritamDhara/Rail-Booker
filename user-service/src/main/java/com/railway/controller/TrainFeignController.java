package com.railway.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.railway.client.TrainClient;
import com.railway.dto.Train;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/train")
@EnableFeignClients(basePackages = "com.railway.client")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TrainFeignController {
	@Autowired
	TrainClient trainClient;
	
	@PostMapping("/addTrain")
//	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Train> addTrain(@RequestBody Train train){
		
		Train tr =  trainClient.addTrain(train).getBody();
		return new ResponseEntity<Train>(tr, HttpStatus.OK);
	}

	@PutMapping("/updateTrain")
//	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Train> updateTrain(@RequestBody Train train){
		
		Train tr= trainClient.updateTrain(train).getBody();
		
		return new ResponseEntity<Train>(tr, HttpStatus.OK);
	}

	
	@DeleteMapping("/removeTrain/{trainNo}")
//	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<String> removeTrain(@PathVariable String trainNo){
		String msg = trainClient.removeTrain(trainNo).getBody();
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}

	@GetMapping("/searchTrainByTrainNo/{trainNo}")
//	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<Train> searchTrainByTrainNo(@PathVariable String trainNo){
		
		Train tr = trainClient.searchTrainByTrainNo(trainNo).getBody();
		log.info("Train found with trainNo ",trainNo);
		return new ResponseEntity<Train>(tr,HttpStatus.OK);
	}

	@GetMapping("/viewTrainBySourceAndDestination/{source}/{destination}")
//	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<List<Train>> searchTrainBySourceAndDestination(@PathVariable String source,@PathVariable String destination){
		
		List<Train> trains = trainClient.searchTrainBySourceAndDestination(source, destination).getBody();
		
		return new ResponseEntity<List<Train>>(trains,HttpStatus.OK);
	}

	@GetMapping("/viewTrainByTrainName/{trainName}")
//	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<List<Train>> searchTrainByTrainName(@PathVariable String trainName){
		
		List<Train> trains = trainClient.searchTrainByTrainName(trainName).getBody();
		return new ResponseEntity<List<Train>>(trains,HttpStatus.OK);
	}

	@GetMapping("/viewAvailableSeatsByTrainNo/{trainNo}")
//	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<Map<String, Integer>> viewAvailableSeatsByTrainNo(@PathVariable String trainNo){
			Map<String,Integer> availableSeats = trainClient.viewAvailableSeatsByTrainNo(trainNo).getBody();
		return new ResponseEntity<Map<String,Integer>>(availableSeats,HttpStatus.OK);
	}

	@GetMapping("/viewSeatWisePriceByTrainNo/{trainNo}")
//	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<Map<String, Double>> viewSeatWisePriceByTrainNo(@PathVariable String trainNo){
		Map<String,Double> seatWisePrice = trainClient.viewSeatWisePriceByTrainNo(trainNo).getBody();
		return new ResponseEntity<Map<String,Double>>(seatWisePrice,HttpStatus.OK);
	}

	@GetMapping("/viewAllTrains")
//	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	public ResponseEntity<List<Train>> viewAllTrains(){
		List<Train> allTrains = trainClient.viewAllTrains().getBody();
		return new ResponseEntity<List<Train>>(allTrains,HttpStatus.OK);
	}

//	@GetMapping("/decreaseTrainSeats/{noOfSeats}/{cls}/{trainNo}")
//	public ResponseEntity<Train> decreseTrainSeats(@PathVariable int noOfSeats,@PathVariable String cls,@PathVariable String trainNo){
//		Train decresedTrain = trainClient.decreseTrainSeats(noOfSeats, cls, trainNo).getBody();
//		return new ResponseEntity<Train>(decresedTrain,HttpStatus.OK);
//	}
//
//	@GetMapping("/increaseTrainSeats/{noOfSeats}/{cls}/{trainNo}")
//	public ResponseEntity<Train> increseTrainSeats(@PathVariable int noOfSeats,@PathVariable String cls,@PathVariable String trainNo){
//		Train increaseTrain = trainClient.increseTrainSeats(noOfSeats, cls, trainNo).getBody();
//		return new ResponseEntity<Train>(increaseTrain,HttpStatus.OK);
//	}
	

}
