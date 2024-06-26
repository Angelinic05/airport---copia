package com.campuslands.modules.airline.infrastructure;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.airline.domain.Airline;

public interface AirlineRepository{
    void save(Airline airline);
    void update(Airline airline);
    Optional<Airline> findById(int id);
    void delete(int id);
    List<Airline> findAll();
}