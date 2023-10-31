package com.railway.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.railway.entity.Train;

@Repository
public interface TrainRepository extends MongoRepository<Train, Integer>{
	Optional<Train> findByTrainNo(String trainNo);
	
	void deleteByTrainNo(String trainNo);
	List<Train> findBySourceStationIgnoreCaseAndDestinationStationIgnoreCase(String sourceStation,String destinationStation);
	List<Train> findByTrainName(String trainName);
}
