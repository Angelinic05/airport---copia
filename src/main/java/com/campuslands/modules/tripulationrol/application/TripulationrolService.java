package com.campuslands.modules.tripulationrol.application;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.tripulationrol.domain.Tripulationrol;
import com.campuslands.modules.tripulationrol.infraestructure.TripulationrolRepository;

public class TripulationrolService {
    private final TripulationrolRepository tripulationrolRepository;

    public TripulationrolService(TripulationrolRepository tripulationrolRepository) {
        this.tripulationrolRepository = tripulationrolRepository;
    }

    public void createTripulationrol(Tripulationrol tripulationrol) {
        tripulationrolRepository.save(tripulationrol);
    }

    public void updateTripulationrol(Tripulationrol tripulationrol) {
        tripulationrolRepository.update(tripulationrol);
    }

    public Optional<Tripulationrol> getTripulationrolById(int id) {
        return tripulationrolRepository.findById(id);
    }

    public void deleteTripulationrol(int id) {
        tripulationrolRepository.delete(id);
    }

    public List<Tripulationrol> getAllTripulationroles() {
        return tripulationrolRepository.findAll();
    }
}
