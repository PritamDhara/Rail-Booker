package com.railway.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.railway.entity.Train;
import com.railway.exceptions.TrainAlreadyExistException;
import com.railway.exceptions.TrainNotFoundException;
import com.railway.repository.TrainRepository;
import com.railway.service.SequenceGeneratorService;
import com.railway.service.TrainServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TrainServiceImplTest {

    @Mock
    private TrainRepository trainRepository;

    @Mock
    private SequenceGeneratorService sequenceGeneratorService;

    @InjectMocks
    private TrainServiceImpl trainService;

//    @Test
//    public void testAddTrain() throws TrainAlreadyExistException {
//        // Create a sample train
//        Train train = new Train();
//        train.setTrainNo("D109");
//
//        // Mock behavior of the repository
//        when(trainRepository.findByTrainNo("D109")).thenReturn(Optional.empty());
//        when(sequenceGeneratorService.generateSequence(anyString())).thenReturn(1);
//
//        // Perform the test
//        Optional<Train> result = trainService.addTrain(train);
//
//        // Assertions
//        assertTrue(result.isPresent());
//        assertEquals(train, result.get());
//    }

    @Test
    public void testUpdateTrain() throws TrainNotFoundException {
        // Create a sample train
        Train train = new Train();
        train.setTrainNo("D109");

        // Mock behavior of the repository
        when(trainRepository.findByTrainNo("D109")).thenReturn(Optional.of(train));
        when(trainRepository.save(train)).thenReturn(train);

        // Perform the test
        Optional<Train> result = trainService.updateTrain(train);

        // Assertions
        assertTrue(result.isPresent());
        assertEquals(train, result.get());
    }

    @Test
    public void testRemoveTrain() throws TrainNotFoundException {
        // Mock behavior of the repository
        when(trainRepository.findByTrainNo("D109")).thenReturn(Optional.of(new Train()));

        // Perform the test
        String result = trainService.removeTrain("D109");

        // Assertions
        assertEquals("Train Deleted Successfully", result);
    }

    @Test
    public void testSearchTrainByTrainNo() throws TrainNotFoundException {
        // Create a sample train
        Train train = new Train();
        train.setTrainNo("D109");

        // Mock behavior of the repository
        when(trainRepository.findByTrainNo("D109")).thenReturn(Optional.of(train));

        // Perform the test
        Optional<Train> result = trainService.searchTrainByTrainNo("D109");

        // Assertions
        assertTrue(result.isPresent());
        assertEquals(train, result.get());
    }

    @Test
    public void testSearchTrainBySourceAndDestination() throws TrainNotFoundException {
        // Create a sample train list
        List<Train> trainList = new ArrayList<>();
        Train train1 = new Train();
        train1.setTrainNo("D109");
        trainList.add(train1);

        // Mock behavior of the repository
        when(trainRepository.findBySourceStationIgnoreCaseAndDestinationStationIgnoreCase("Howrah", "Mumbai")).thenReturn(trainList);

        // Perform the test
        List<Train> result = trainService.searchTrainBySourceAndDestination("Howrah", "Mumbai");

        // Assertions
        assertFalse(result.isEmpty());
        assertEquals(trainList, result);
    }

    @Test
    public void testSearchTrainByTrainName() throws TrainNotFoundException {
        // Create a sample train list
        List<Train> trainList = new ArrayList<>();
        Train train1 = new Train();
        train1.setTrainName("Gorib Rath Kol-Del");
        trainList.add(train1);

        // Mock behavior of the repository
        when(trainRepository.findByTrainName("Gorib Rath Kol-Del")).thenReturn(trainList);

        // Perform the test
        List<Train> result = trainService.searchTrainByTrainName("Gorib Rath Kol-Del");

        // Assertions
        assertFalse(result.isEmpty());
        assertEquals(trainList, result);
    }

    @Test
    public void testViewAvailableSeatsByTrainNo() throws TrainNotFoundException {
        // Create a sample train
        Train train = new Train();
        Map<String, Integer> classWiseSeat = new HashMap<>();
        classWiseSeat.put("ThAC", 45);
        train.setClassWiseSeat(classWiseSeat);

        // Mock behavior of the repository
        when(trainRepository.findByTrainNo("D109")).thenReturn(Optional.of(train));

        // Perform the test
        Map<String, Integer> result = trainService.viewAvailableSeatsByTrainNo("D109");

        // Assertions
        assertEquals(classWiseSeat, result);
    }

    @Test
    public void testViewSeatWisePriceByTrainNo() throws TrainNotFoundException {
        // Create a sample train
        Train train = new Train();
        Map<String, Double> classWisePrice = new HashMap<>();
        classWisePrice.put("ThAC", 3000.0);
        train.setClassWisePrice(classWisePrice);

        // Mock behavior of the repository
        when(trainRepository.findByTrainNo("D109")).thenReturn(Optional.of(train));

        // Perform the test
        Map<String, Double> result = trainService.viewSeatWisePriceByTrainNo("D109");

        // Assertions
        assertEquals(classWisePrice, result);
    }

    @Test
    public void testViewAllTrains() throws TrainNotFoundException {
        // Create a sample train list
        List<Train> trainList = new ArrayList<>();
        Train train1 = new Train();
        Train train2 = new Train();
        trainList.add(train1);
        trainList.add(train2);

        // Mock behavior of the repository
        when(trainRepository.findAll()).thenReturn(trainList);

        // Perform the test
        List<Train> result = trainService.viewAllTrains();

        // Assertions
        assertFalse(result.isEmpty());
        assertEquals(trainList, result);
    }

    @Test
    public void testDecreaseTrainSeats() throws TrainNotFoundException {
        // Create a sample train
        Train train = new Train();
        Map<String, Integer> classWiseSeat = new HashMap<>();
        classWiseSeat.put("ThAC", 45);
        train.setClassWiseSeat(classWiseSeat);

        // Mock behavior of the repository
        when(trainRepository.findByTrainNo("D109")).thenReturn(Optional.of(train));
        when(trainRepository.save(train)).thenReturn(train);

        // Perform the test
        Optional<Train> result = trainService.decreseTrainSeats(5, "ThAC", "D109");

        // Assertions
        assertTrue(result.isPresent());
        assertEquals(40, result.get().getClassWiseSeat().get("ThAC"));
    }

    @Test
    public void testIncreaseTrainSeats() throws TrainNotFoundException {
        // Create a sample train
        Train train = new Train();
        Map<String, Integer> classWiseSeat = new HashMap<>();
        classWiseSeat.put("ThAC", 45);
        train.setClassWiseSeat(classWiseSeat);

        // Mock behavior of the repository
        when(trainRepository.findByTrainNo("D109")).thenReturn(Optional.of(train));
        when(trainRepository.save(train)).thenReturn(train);

        // Perform the test
        Optional<Train> result = trainService.increseTrainSeats(5, "ThAC", "D109");

        // Assertions
        assertTrue(result.isPresent());
        assertEquals(50, result.get().getClassWiseSeat().get("ThAC"));
    }
}
