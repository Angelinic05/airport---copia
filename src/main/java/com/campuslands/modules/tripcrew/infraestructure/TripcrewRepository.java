package com.campuslands.modules.tripcrew.infraestructure;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.tripcrew.domain.Tripcrew;

public interface TripcrewRepository {
    void save(Tripcrew tripcrew);
    void update(Tripcrew tripcrew);
    Optional<Tripcrew> findById(int id);
    void delete(int id);
    List<Tripcrew> findAll();
}
