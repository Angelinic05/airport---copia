package com.campuslands.modules.airportAirline.infrastructure;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.airportAirline.domain.AirportAirline;

public interface AirportAirlineRepository {

   void save(AirportAirline airportAirline);
    void update(AirportAirline airportAirline);
    Optional<AirportAirline> findById(int id);
    void delete(int id);
    List<AirportAirline> findAll();
}
