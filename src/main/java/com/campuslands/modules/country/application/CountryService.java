package com.campuslands.modules.country.application;


import java.util.List;
import com.campuslands.modules.country.domain.Country;
import com.campuslands.modules.country.infrastructure.CountryRepository;
import java.util.Optional;

public class CountryService {
    
    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void saveCountry(Country country) {
        countryRepository.save(country);
    }
    public void updateCountry(Country country) {
        countryRepository.update(country);
    }
    public Optional<Country> findCountryById(int id) {
        return countryRepository.findById(id);
    }
    public void deleteCountry(int id) {
        countryRepository.delete(id);
    }
    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }

}
