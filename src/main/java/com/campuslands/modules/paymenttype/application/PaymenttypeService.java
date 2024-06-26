package com.campuslands.modules.paymenttype.application;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.paymenttype.infrastructure.PaymenttypeRepository;
import com.campuslands.modules.paymenttype.domain.Paymenttype;

public class PaymenttypeService {
    private final PaymenttypeRepository paymenttypeRepository;

    public PaymenttypeService(PaymenttypeRepository paymenttypeRepository) {
        this.paymenttypeRepository = paymenttypeRepository;
    }

    public void savePaymenttype(Paymenttype paymenttype) {
        paymenttypeRepository.save(paymenttype);
    }

    public void updatePaymenttype(Paymenttype paymenttype) {
        paymenttypeRepository.update(paymenttype);
    }

    public Optional<Paymenttype> findPaymenttypeById(int id) {
        return paymenttypeRepository.findById(id);
    }

    public void deletePaymenttype(int id) {
        paymenttypeRepository.delete(id);
    }

    public List<Paymenttype> findAllPaymenttypees() {
        return paymenttypeRepository.findAll();
    }
    
}
