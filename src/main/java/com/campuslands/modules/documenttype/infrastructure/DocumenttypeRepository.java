package com.campuslands.modules.documenttype.infrastructure;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.documenttype.domain.Documenttype;

public interface DocumenttypeRepository {
    void save(Documenttype documenttype);
    void update(Documenttype documenttype);
    Optional<Documenttype> findById(int id);
    void delete(int id);
    List<Documenttype> findAll();
}
