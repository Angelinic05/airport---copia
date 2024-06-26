package com.campuslands.modules.tripbookingdetail.infraestructure;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.tripbookingdetail.domain.Tripbookingdetail;

public interface TripbookingdetailRepository {
    void save(Tripbookingdetail tripbookingdetail);
    void update(Tripbookingdetail tripbookingdetail);
    Optional<Tripbookingdetail> findById(int id);
    void delete(int id);
    List<Tripbookingdetail> findAll();
}
