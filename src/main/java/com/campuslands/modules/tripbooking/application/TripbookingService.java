package com.campuslands.modules.tripbooking.application;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.tripbooking.domain.Tripbooking;
import com.campuslands.modules.tripbooking.infraestructure.TripbookingRepository;

public class TripbookingService {
    private final TripbookingRepository tripbookingRepository;

    public TripbookingService(TripbookingRepository tripbookingRepository) {
        this.tripbookingRepository = tripbookingRepository;
    }

    public void createTripbooking(Tripbooking tripbooking) {
        tripbookingRepository.save(tripbooking);
    }

    public void updateTripbooking(Tripbooking tripbooking) {
        tripbookingRepository.update(tripbooking);
    }

    public Optional<Tripbooking> getTripbookingById(int id) {
        return tripbookingRepository.findById(id);
    }

    public void deleteTripbooking(int id) {
        tripbookingRepository.delete(id);
    }

    public List<Tripbooking> getAllTripbookings() {
        return tripbookingRepository.findAll();
    }
}

