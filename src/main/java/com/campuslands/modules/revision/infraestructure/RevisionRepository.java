package com.campuslands.modules.revision.infraestructure;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.revision.domain.Revision;

public interface RevisionRepository {
    void save(Revision revision);
    void update(Revision revision);
    Optional<Revision> findById(int id);
    void delete(int id);
    List<Revision> findAll();
}
