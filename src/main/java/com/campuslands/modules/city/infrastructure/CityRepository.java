package com.campuslands.modules.city.infrastructure;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.city.domain.City;


public interface CityRepository {
   void save(City city);
    void update(City city);
    Optional<City> findById(int id);
    void delete(int id);
    List<City> findAll();
}
