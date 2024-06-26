package com.campuslands.modules.revision.application;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.revision.domain.Revision;
import com.campuslands.modules.revision.infraestructure.RevisionRepository;

public class RevisionService {
    private final RevisionRepository revisionRepository;

    public RevisionService(RevisionRepository revisionRepository) {
        this.revisionRepository = revisionRepository;
    }

    public void saveRevision(Revision revision) {
        revisionRepository.save(revision);
    }

    public void updateRevision(Revision revision) {
        revisionRepository.update(revision);
    }

    public Optional<Revision> findRevisionById(int id) {
        return revisionRepository.findById(id);
    }

    public void deleteRevision(int id) {
        revisionRepository.delete(id);
    }

    public List<Revision> getAllRevisions() {
        return revisionRepository.findAll();
    }
}

