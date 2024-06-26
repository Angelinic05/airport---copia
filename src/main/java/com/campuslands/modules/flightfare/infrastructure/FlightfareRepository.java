package com.campuslands.modules.flightfare.infrastructure;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.flightfare.domain.Flightfare;

public interface FlightfareRepository {
    void save(Flightfare flightfare);
    void update(Flightfare flightfare);
    Optional<Flightfare> findById(int id);
    void delete(int id);
    List<Flightfare> findAll();
    
}
