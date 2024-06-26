package com.campuslands.modules.status.application;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.status.domain.Status;
import com.campuslands.modules.status.infraestructure.StatusRepository;

public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public void createStatus(Status status) {
        statusRepository.save(status);
    }

    public void updateStatus(Status status) {
        statusRepository.update(status);
    }

    public Optional<Status> getStatusById(int id) {
        return statusRepository.findById(id);
    }

    public void deleteStatus(int id) {
        statusRepository.delete(id);
    }

    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }
}
