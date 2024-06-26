package com.campuslands.modules.revisiondetail.infraestructure;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.revisiondetail.domain.Revisiondetail;

public interface RevisiondetailRepository {
    void save(Revisiondetail revisiondetail);
    void update(Revisiondetail revisiondetail);
    Optional<Revisiondetail> findById(int id);
    void delete(int id);
    List<Revisiondetail> findAll();
}
