package com.campuslands.modules.payment.application;

import com.campuslands.modules.payment.infrastructure.PaymentRepository;
import com.campuslands.modules.payment.domain.Payment;

import java.util.Optional;
import java.util.List;

public class PaymentService {
    private PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public void savePayment(Payment payment) {
        paymentRepository.savePayment(payment);
    }

    public void updatePayment(Payment payment) {
        paymentRepository.updatePayment(payment);
    }

    public Optional<Payment> findPaymentById(int id) {
        return paymentRepository.findPaymentById(id);
    }

    public void deletePayment(int id) {
        paymentRepository.deletePayment(id);
    }

    public List<Payment> findAllPayments() {
        return paymentRepository.findAllPayments();
    }
}
