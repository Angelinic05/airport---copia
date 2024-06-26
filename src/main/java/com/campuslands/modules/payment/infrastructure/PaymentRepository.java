package com.campuslands.modules.payment.infrastructure;

import com.campuslands.modules.payment.domain.Payment;

import java.util.Optional;
import java.util.List;

public interface PaymentRepository {
    
    public void savePayment(Payment payment);
    public void updatePayment(Payment payment);
    public void deletePayment(int id);
    public Optional<Payment> findPaymentById(int id);
    public List<Payment> findAllPayments();

}
