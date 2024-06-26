package com.campuslands.modules.paymenttype.infrastructure;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.paymenttype.domain.Paymenttype;

public interface PaymenttypeRepository {
    
    void save(Paymenttype paymenttype);
    void update(Paymenttype paymenttype);
    void delete(int id);
    Optional<Paymenttype> findById(int id);
    List<Paymenttype> findAll();
}
