package com.campuslands.modules.tripbookingdetail.application;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.tripbookingdetail.domain.Tripbookingdetail;
import com.campuslands.modules.tripbookingdetail.infraestructure.TripbookingdetailRepository;

public class TripbookingdetailService {
    private final TripbookingdetailRepository tripbookingdetailRepository;

    public TripbookingdetailService(TripbookingdetailRepository tripbookingdetailRepository) {
        this.tripbookingdetailRepository = tripbookingdetailRepository;
    }

    public void createTripbookingdetail(Tripbookingdetail tripbookingdetail) {
        tripbookingdetailRepository.save(tripbookingdetail);
    }

    public void updateTripbookingdetail(Tripbookingdetail tripbookingdetail) {
        tripbookingdetailRepository.update(tripbookingdetail);
    }

    public Optional<Tripbookingdetail> getTripbookingdetailById(int id) {
        return tripbookingdetailRepository.findById(id);
    }

    public void deleteTripbookingdetail(int id) {
        tripbookingdetailRepository.delete(id);
    }

    public List<Tripbookingdetail> getAllTripbookingdetails() {
        return tripbookingdetailRepository.findAll();
    }
}
