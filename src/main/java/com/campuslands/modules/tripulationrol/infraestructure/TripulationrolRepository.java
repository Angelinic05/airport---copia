package com.campuslands.modules.tripulationrol.infraestructure;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.tripulationrol.domain.Tripulationrol;

public interface TripulationrolRepository {
    void save(Tripulationrol tripulationrol);
    void update(Tripulationrol tripulationrol);
    Optional<Tripulationrol> findById(int id);
    void delete(int id);
    List<Tripulationrol> findAll();
}
