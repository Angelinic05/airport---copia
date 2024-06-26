package com.campuslands.modules.airline.application;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.airline.domain.Airline;
import com.campuslands.modules.airline.infrastructure.AirlineRepository;

public class AirlineService{
    
    private final AirlineRepository AirlineRepository;

    public AirlineService(AirlineRepository airlineRepository) {
        this.AirlineRepository = airlineRepository;
    }

    public void createAirline(Airline airline) {
        AirlineRepository.save(airline);
    }

    public void updateAirline(Airline airline) {
        AirlineRepository.update(airline);
    }

    public Optional<Airline> getAirlineById(int id) {
        return AirlineRepository.findById(id);
    }

    public void deleteAirline(int id) {
        AirlineRepository.delete(id);
    }

    public List<Airline> getAllAirlines() {
        return AirlineRepository.findAll();
    }
}