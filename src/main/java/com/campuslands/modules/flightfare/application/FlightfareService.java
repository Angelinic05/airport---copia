package com.campuslands.modules.flightfare.application;

import java.util.List;
import java.util.Optional;
import com.campuslands.modules.flightfare.infrastructure.FlightfareRepository;
import com.campuslands.modules.flightfare.domain.Flightfare;


public class FlightfareService {
    private FlightfareRepository flightfareRepository;

    public FlightfareService(FlightfareRepository flightfareRepository){
        this.flightfareRepository = flightfareRepository;
    }

    public void saveFlightfare(Flightfare flightfare){
        flightfareRepository.save(flightfare);
    }

    public void deleteFlightfare(int id){
        flightfareRepository.delete(id);
    }

    public void updateFlightfare(Flightfare flightfare){
        flightfareRepository.update(flightfare);
    }

    public Optional<Flightfare> findByIdFlightfare(int id){
        return flightfareRepository.findById(id);
    }

    public List<Flightfare> findAll(){
        return flightfareRepository.findAll();
    }
}
