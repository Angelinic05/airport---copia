package com.campuslands.modules.flightconnection.application;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.flightconnection.domain.Flightconnection;
import com.campuslands.modules.flightconnection.infrastructure.FlightconnectionRepository;

public class FlightconnectionService {
    private FlightconnectionRepository flightconnectionRepository;

    public FlightconnectionService (FlightconnectionRepository flightconnectionRepository){
        this.flightconnectionRepository = flightconnectionRepository;
    }

    public void saveFlightconnection(Flightconnection flightconnection){
        flightconnectionRepository.save(flightconnection);
    }

    public void updateFlightconnection(Flightconnection flightconnection){
        flightconnectionRepository.update(flightconnection);
    }

    public Optional<Flightconnection> findByIdFlightconnection(int id){
        return  flightconnectionRepository.findById(id);
    }

    public void deleteFlightconnection(int id){
        flightconnectionRepository.delete(id);
    }

    public List<Flightconnection> findAllFlightconnection(){
        return flightconnectionRepository.findAll();
    }

}
