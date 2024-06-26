package com.campuslands.modules.revemployee.infrastructure;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.revemployee.domain.Revemployee;

public interface RevemployeeRepository {
    void save(Revemployee revemployee);
    void update(Revemployee revemployee);
    Optional<Revemployee> findById(int id);
    void delete(int id);
    List<Revemployee> findAll();
}
