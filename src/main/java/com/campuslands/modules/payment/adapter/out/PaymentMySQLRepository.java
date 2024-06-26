package com.campuslands.modules.payment.adapter.out;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.campuslands.modules.payment.domain.Payment;
import com.campuslands.modules.payment.infrastructure.PaymentRepository;

public class PaymentMySQLRepository implements PaymentRepository {
    
    private final String url;
    private final String user;
    private final String password;

    public PaymentMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void savePayment(Payment payment){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO payment (idTripbooking, idPaymenttype, cardNumber, date) VALUES (?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, payment.getIdTripBooking());
                statement.setInt(2, payment.getIdPaymentType());
                statement.setInt(3, payment.getCardNumber());
                statement.setDate(4, payment.getDate());
                statement.executeUpdate();
            } catch (Exception e) {
                e.getStackTrace();
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void updatePayment(Payment payment){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE payment SET idTripbooking = ?, idPaymenttype = ?, cardNumber = ?, date = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, payment.getIdTripBooking());
                statement.setInt(2, payment.getIdPaymentType());
                statement.setInt(3, payment.getCardNumber());
                statement.setDate(4, payment.getDate());
                statement.setInt(5, payment.getId());
                statement.executeUpdate();                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Optional<Payment> findPaymentById(int id){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, idTripbooking, idPaymenttype, cardNumber, date FROM payment WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if(resultSet.next()){
                        Payment payment = new Payment(
                            resultSet.getInt("id"),
                            resultSet.getInt("idTripbooking"),
                            resultSet.getInt("idPaymenttype"),
                            resultSet.getInt("cardNumber"),
                            resultSet.getDate("date")
                        );
                        return Optional.of(payment);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    @Override
    public void deletePayment(int id){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM payment WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }  
        } catch (SQLException e) {
            e.printStackTrace();;
        }
    }
    
    @Override
    public List<Payment> findAllPayments(){
        List<Payment> payment = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, idTripbooking, idPaymenttype, cardNumber, date FROM payment";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while(resultSet.next()){
                        Payment payment2 = new Payment(
                            resultSet.getInt("id"),
                            resultSet.getInt("idTripbooking"),
                            resultSet.getInt("idPaymenttype"),
                            resultSet.getInt("cardNumber"),
                            resultSet.getDate("date")
                        );
                        payment.add(payment2);
                    }   
                } 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payment;
    }
}
