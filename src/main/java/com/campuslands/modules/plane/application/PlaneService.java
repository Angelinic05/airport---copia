package com.campuslands.modules.plane.application;

import java.util.List;
import java.util.Optional;
import com.campuslands.modules.plane.domain.Plane;
import com.campuslands.modules.plane.infrastructure.PlaneRepository;

public class PlaneService {
    private PlaneRepository planeRepository;

    public PlaneService(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    public void savePlane(Plane plane){
        planeRepository.save(plane);
    }

    public void deletePlane(int id){
        planeRepository.delete(id);
    }

    public void updatePlane(Plane plane){
        planeRepository.update(plane);
    }

    public Optional<Plane> findByIdPlane(int id){
        return planeRepository.findById(id);
    }

    public List<Plane> findAll(){
        return planeRepository.findAll();
    }
}
