package com.campuslands.modules.revemployee.application;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.revemployee.domain.Revemployee;
import com.campuslands.modules.revemployee.infrastructure.RevemployeeRepository;

public class RevemployeeService {
    private RevemployeeRepository revemployeeRepository;

    public RevemployeeService(RevemployeeRepository revemployeeRepository) {
        this.revemployeeRepository = revemployeeRepository;
    }

    public void saveRevemployee(Revemployee revemployee){
        revemployeeRepository.save(revemployee);
    }

    public void deleteRevemployee(int id){
        revemployeeRepository.delete(id);
    }

    public void updateRevemployee(Revemployee revemployee){
        revemployeeRepository.update(revemployee);
    }

    public Optional<Revemployee> findByIdRevemployee(int id){
        return revemployeeRepository.findById(id);
    }

    public List<Revemployee> findAllRevemployee(){
        return revemployeeRepository.findAll();
    }
}
