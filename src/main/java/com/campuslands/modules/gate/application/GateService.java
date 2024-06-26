package com.campuslands.modules.gate.application;

import com.campuslands.modules.gate.infrastructure.GateRepository;
import com.campuslands.modules.gate.domain.Gate;
import java.util.Optional;
import java.util.List;

public class GateService {
    private GateRepository gateRepository;

    public GateService(GateRepository gateRepository){
        this.gateRepository = gateRepository;
    }

    public void saveGate(Gate gate){
        gateRepository.save(gate);
    }

    public void deleteGate(int id){
        gateRepository.delete(id);
    }

    public void updateGate(Gate gate){
        gateRepository.update(gate);
    }

    public Optional<Gate> findByIdGate(int id){
        return gateRepository.findById(id);
    }

    public List<Gate> findAllGate(){
        return gateRepository.findAll();
    }
}
