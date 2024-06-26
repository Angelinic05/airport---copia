package com.campuslands.modules.manufacture.infrastructure;

import java.util.List;
import java.util.Optional;
import com.campuslands.modules.manufacture.domain.Manufacture;

public interface ManufactureRepository {
    void save(Manufacture manufacture);
    void update(Manufacture manufacture);
    Optional<Manufacture> findById(int id);
    void delete(int id);
    List<Manufacture> findAll();
    
}
