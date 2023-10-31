package com.railway.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.railway.entity.Train;
import com.railway.exceptions.TrainAlreadyExistException;
import com.railway.exceptions.TrainNotFoundException;

public interface TrainService {
	
	Optional<Train> addTrain(Train train) throws TrainAlreadyExistException;
	Optional<Train> updateTrain(Train train) throws TrainNotFoundException;
	String removeTrain(String trainNo) throws TrainNotFoundException;
	Optional<Train> searchTrainByTrainNo(String trainNo) throws TrainNotFoundException;
	List<Train> searchTrainBySourceAndDestination(String source,String destination) throws TrainNotFoundException;
	List<Train> searchTrainByTrainName(String trainName) throws TrainNotFoundException;
	Map<String,Integer> viewAvailableSeatsByTrainNo(String trainNo) throws TrainNotFoundException;
	Map<String,Double> viewSeatWisePriceByTrainNo(String trainNo) throws TrainNotFoundException;
	List<Train> viewAllTrains() throws TrainNotFoundException;
	
	Optional<Train> decreseTrainSeats(int noOfSeats,String cls,String trainNo) throws TrainNotFoundException;
	Optional<Train> increseTrainSeats(int noOfSeats,String cls,String trainNo) throws TrainNotFoundException;

}
