package com.railway.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railway.entity.Train;
import com.railway.exceptions.TrainAlreadyExistException;
import com.railway.exceptions.TrainNotFoundException;
import com.railway.repository.TrainRepository;

@Service
public class TrainServiceImpl implements TrainService{

	@Autowired
	TrainRepository tRepo;
	
	@Autowired
	SequenceGeneratorService sequenceGenerator;
	
	@Override
	public Optional<Train> addTrain(Train train) throws TrainAlreadyExistException {
		Optional<Train> tr = tRepo.findByTrainNo(train.getTrainNo());
		if(tr.isPresent())
		{
			throw new TrainAlreadyExistException();
		}
		
		train.setTrainId(sequenceGenerator.generateSequence(Train.SEQUENCE_NAME));
		
		Train t = tRepo.insert(train);
		return Optional.of(t);
	}

	@Override
	public Optional<Train> updateTrain(Train train) throws TrainNotFoundException {
		
		if(tRepo.findByTrainNo(train.getTrainNo()).isEmpty())
		{
			throw new TrainNotFoundException();
		}
		
		Train t = tRepo.save(train);
		return Optional.of(t);
	}

	@Override
	public String removeTrain(String trainNo) throws TrainNotFoundException {
		
		if(tRepo.findByTrainNo(trainNo).isEmpty())
		{
			throw new TrainNotFoundException();
		}
		
		tRepo.deleteByTrainNo(trainNo);
		
		return "Train Deleted Successfully";
	}

	@Override
	public Optional<Train> searchTrainByTrainNo(String trainNo) throws TrainNotFoundException {
		
		if(tRepo.findByTrainNo(trainNo).isEmpty())
		{
			throw new TrainNotFoundException();
		}
		
		return tRepo.findByTrainNo(trainNo);
	}

	@Override
	public List<Train> searchTrainBySourceAndDestination(String source, String destination) throws TrainNotFoundException {
		List<Train> trains = tRepo.findBySourceStationIgnoreCaseAndDestinationStationIgnoreCase(source, destination);
		
		if(trains.isEmpty())
		{
			throw new TrainNotFoundException();
		}
		return trains;
	}

	@Override
	public List<Train> searchTrainByTrainName(String trainName) throws TrainNotFoundException {
		List<Train> trains = tRepo.findByTrainName(trainName);
		if(trains.isEmpty()){
			
			throw new TrainNotFoundException();
		}
		
		return trains;
	}

	@Override
	public Map<String, Integer> viewAvailableSeatsByTrainNo(String trainNo) throws TrainNotFoundException {
		
		Optional<Train> train = tRepo.findByTrainNo(trainNo);
		
		if(train.isEmpty())
		{
			throw new TrainNotFoundException();
		}
		
		Map<String,Integer> seatsAvailable = train.get().getClassWiseSeat();
		
		return seatsAvailable;
	}

	@Override
	public Map<String, Double> viewSeatWisePriceByTrainNo(String trainNo) throws TrainNotFoundException {
		
		Optional<Train> train = tRepo.findByTrainNo(trainNo);
		
		if(train.isEmpty())
		{
			throw new TrainNotFoundException();
		}
		
		Map<String, Double> priceList = train.get().getClassWisePrice();
		
		return priceList;
	}

	@Override
	public List<Train> viewAllTrains() throws TrainNotFoundException {
		
		List<Train> trains = tRepo.findAll();
		
		if(trains.isEmpty())
		{
			throw new TrainNotFoundException();
		}
		return trains;
	}

	@Override
	public Optional<Train> decreseTrainSeats(int noOfSeats, String cls, String trainNo) throws TrainNotFoundException {
		
		Optional<Train> opTrain = tRepo.findByTrainNo(trainNo);
		
		if(opTrain.isEmpty())
		{
			throw new TrainNotFoundException();
		}
		Train train = opTrain.get();
		Map<String,Integer> map = train.getClassWiseSeat();
		
		map.put(cls, map.get(cls)-noOfSeats);
		
		train.setClassWiseSeat(map);
		
		return Optional.of(tRepo.save(train));
	}

	@Override
	public Optional<Train> increseTrainSeats(int noOfSeats, String cls, String trainNo) throws TrainNotFoundException {
		Optional<Train> train = tRepo.findByTrainNo(trainNo);
		
		if(train.isEmpty())
		{
			throw new TrainNotFoundException();
		}
		
		Map<String,Integer> map = train.get().getClassWiseSeat();
		
		map.put(cls, map.get(cls)+noOfSeats);
		
		train.get().setClassWiseSeat(map);
		
		 Train t = tRepo.save(train.get());
		
		return Optional.of(t);
	}

}
