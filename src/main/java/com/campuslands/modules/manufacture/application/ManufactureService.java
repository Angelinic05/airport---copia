package com.campuslands.modules.manufacture.application;

import com.campuslands.modules.manufacture.infrastructure.ManufactureRepository;
import com.campuslands.modules.manufacture.domain.Manufacture;
import java.util.Optional;
import java.util.List;

public class ManufactureService {
    private ManufactureRepository manufactureRepository;

    public ManufactureService(ManufactureRepository manufactureRepository) {
        this.manufactureRepository = manufactureRepository;
    }

    public void saveManufacture(Manufacture manufacture){
        manufactureRepository.save(manufacture);
    }

    public void delete(int id){
        manufactureRepository.delete(id);
    }

    public void update(Manufacture manufacture){
        manufactureRepository.update(manufacture);
    }

    public Optional<Manufacture> findByIdManufacture(int id){
        return manufactureRepository.findById(id);
    }

    public List<Manufacture> findAll(){
        return manufactureRepository.findAll();
    }

    
}
