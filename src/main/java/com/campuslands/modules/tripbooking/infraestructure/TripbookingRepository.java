package com.campuslands.modules.tripbooking.infraestructure;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.tripbooking.domain.Tripbooking;

public interface TripbookingRepository {
    void save(Tripbooking tripbooking);
    void update(Tripbooking tripbooking);
    Optional<Tripbooking> findById(int id);
    void delete(int id);
    List<Tripbooking> findAll();
}
