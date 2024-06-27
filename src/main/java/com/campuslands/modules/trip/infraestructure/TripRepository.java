package com.campuslands.modules.trip.infraestructure;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.trip.domain.Trip;

public interface TripRepository {
    void save(Trip trip);
    void update(Trip trip);
    Optional<Trip> findById(int id);
    void delete(int id);
    List<Trip> findAll();
    int saveAndReturnId(Trip trip);
}
