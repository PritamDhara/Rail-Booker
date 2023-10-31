package com.railway.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.railway.entity.Train;
import com.railway.exceptions.TrainAlreadyExistException;
import com.railway.exceptions.TrainNotFoundException;
import com.railway.service.TrainService;

@RestController
@RequestMapping("/train")
@CrossOrigin
public class TrainController {

	@Autowired
	TrainService trainService;
	
	@PostMapping("/addTrain")
	public ResponseEntity<Train> addTrain(@RequestBody Train train) throws TrainAlreadyExistException {
		
		Optional<Train> tr =  trainService.addTrain(train);
		return new ResponseEntity<Train>(tr.get(), HttpStatus.OK);
	}

	@PutMapping("/updateTrain")
	public ResponseEntity<Train> updateTrain(@RequestBody Train train) throws TrainNotFoundException {
		
		Optional<Train> tr = trainService.updateTrain(train);
		
		return new ResponseEntity<Train>(tr.get(), HttpStatus.OK);
	}

	
	@DeleteMapping("/removeTrain/{trainNo}")
	public ResponseEntity<String> removeTrain(@PathVariable String trainNo) throws TrainNotFoundException {
		String msg = trainService.removeTrain(trainNo);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}

	@GetMapping("/searchTrainByTrainNo/{trainNo}")
	public ResponseEntity<Train> searchTrainByTrainNo(@PathVariable String trainNo) throws TrainNotFoundException {
		
		Optional<Train> tr = trainService.searchTrainByTrainNo(trainNo);
		return new ResponseEntity<Train>(tr.get(),HttpStatus.OK);
	}

	@GetMapping("/viewTrainBySourceAndDestination/{source}/{destination}")
	public ResponseEntity<List<Train>> searchTrainBySourceAndDestination(@PathVariable String source,@PathVariable String destination) throws TrainNotFoundException {
		
		List<Train> trains = trainService.searchTrainBySourceAndDestination(source, destination);
		
		return new ResponseEntity<List<Train>>(trains,HttpStatus.OK);
	}

	@GetMapping("/viewTrainByTrainName/{trainName}")
	public ResponseEntity<List<Train>> searchTrainByTrainName(@PathVariable String trainName) throws TrainNotFoundException {
		
		List<Train> trains = trainService.searchTrainByTrainName(trainName);
		return new ResponseEntity<List<Train>>(trains,HttpStatus.OK);
	}

	@GetMapping("/viewAvailableSeatsByTrainNo/{trainNo}")
	public ResponseEntity<Map<String, Integer>> viewAvailableSeatsByTrainNo(@PathVariable String trainNo) throws TrainNotFoundException {
			Map<String,Integer> availableSeats = trainService.viewAvailableSeatsByTrainNo(trainNo);
		return new ResponseEntity<Map<String,Integer>>(availableSeats,HttpStatus.OK);
	}

	@GetMapping("/viewSeatWisePriceByTrainNo/{trainNo}")
	public ResponseEntity<Map<String, Double>> viewSeatWisePriceByTrainNo(@PathVariable String trainNo) throws TrainNotFoundException {
		Map<String,Double> seatWisePrice = trainService.viewSeatWisePriceByTrainNo(trainNo);
		return new ResponseEntity<Map<String,Double>>(seatWisePrice,HttpStatus.OK);
	}

	@GetMapping("/viewAllTrains")
	public ResponseEntity<List<Train>> viewAllTrains() throws TrainNotFoundException {
		List<Train> allTrains = trainService.viewAllTrains();
		return new ResponseEntity<List<Train>>(allTrains,HttpStatus.OK);
	}

	@GetMapping("/decreaseTrainSeats/{noOfSeats}/{cls}/{trainNo}")
	public ResponseEntity<Train> decreseTrainSeats(@PathVariable int noOfSeats,@PathVariable String cls,@PathVariable String trainNo) throws TrainNotFoundException {
		Optional<Train> decresedTrain = trainService.decreseTrainSeats(noOfSeats, cls, trainNo);
		return new ResponseEntity<Train>(decresedTrain.get(),HttpStatus.OK);
	}

	@GetMapping("/increaseTrainSeats/{noOfSeats}/{cls}/{trainNo}")
	public ResponseEntity<Train> increseTrainSeats(@PathVariable int noOfSeats,@PathVariable String cls,@PathVariable String trainNo) throws TrainNotFoundException {
		Optional<Train> increaseTrain = trainService.increseTrainSeats(noOfSeats, cls, trainNo);
		return new ResponseEntity<Train>(increaseTrain.get(),HttpStatus.OK);
	}

}
