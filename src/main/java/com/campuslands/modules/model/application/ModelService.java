package com.campuslands.modules.model.application;

import java.util.Optional;
import java.util.List;
import com.campuslands.modules.model.domain.Model;
import com.campuslands.modules.model.infrastructure.ModelRepository;

public class ModelService {
    private ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public void saveModel(Model model){
        modelRepository.save(model);
    }

    public void deleteModel(int id){
        modelRepository.delete(id);
    }

    public void updateModel(Model model){
        modelRepository.update(model);
    }

    public Optional<Model> findByIdModel(int id){
        return modelRepository.findById(id);
    }

    public List<Model> findAllModel(){
        return modelRepository.findAll();
    }
    
}
