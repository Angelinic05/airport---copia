package com.campuslands.modules.revisiondetail.application;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.revisiondetail.domain.Revisiondetail;
import com.campuslands.modules.revisiondetail.infraestructure.RevisiondetailRepository;

public class RevisiondetailService {
    private final RevisiondetailRepository revisiondetailRepository;

    public RevisiondetailService(RevisiondetailRepository revisiondetailRepository) {
        this.revisiondetailRepository = revisiondetailRepository;
    }

    public void createRevisiondetail(Revisiondetail revisiondetail) {
        revisiondetailRepository.save(revisiondetail);
    }

    public void updateRevisiondetail(Revisiondetail revisiondetail) {
        revisiondetailRepository.update(revisiondetail);
    }

    public Optional<Revisiondetail> findRevisiondetailById(int id) {
        return revisiondetailRepository.findById(id);
    }

    public void deleteRevisiondetail(int id) {
        revisiondetailRepository.delete(id);
    }

    public List<Revisiondetail> getAllRevisions() {
        return revisiondetailRepository.findAll();
    }
}
