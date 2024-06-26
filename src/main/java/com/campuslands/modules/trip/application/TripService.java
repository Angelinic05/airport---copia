package com.campuslands.modules.trip.application;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.trip.domain.Trip;
import com.campuslands.modules.trip.infraestructure.TripRepository;

public class TripService {
    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }
    public void createTrip(Trip trip) {
        tripRepository.save(trip);
    }

    public void updateTrip(Trip trip) {
        tripRepository.update(trip);
    }

    public Optional<Trip> getTripById(int id) {
        return tripRepository.findById(id);
    }

    public void deleteTrip(int id) {
        tripRepository.delete(id);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

}
