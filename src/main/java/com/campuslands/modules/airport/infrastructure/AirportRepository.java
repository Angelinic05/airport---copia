package com.campuslands.modules.airport.infrastructure;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.campuslands.modules.airport.domain.Airport;


public interface AirportRepository {
    void save(Airport airport);
    void update(Airport airport);
    Optional<Airport> findById(int id);
    void delete(int id);
    List<Airport> findAll();
    HashMap<Integer, List<Integer>> getAirportsByAirline();
}
