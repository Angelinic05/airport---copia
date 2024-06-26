package com.campuslands.modules.customer.adapter.out;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.modules.customer.domain.Customer;
import com.campuslands.modules.customer.infrastructure.CustomerRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class CustomerMySQLRepository implements CustomerRepository {

    private final String url;
    private final String user;
    private final String password;

    public CustomerMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public Optional<Customer> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM customer WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int idCustomer = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        int age = resultSet.getInt("age");
                        int idDocument = resultSet.getInt("idDocument");
                        return Optional.of(new Customer(idCustomer, name, age, idDocument));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Customer customer) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO customer (id, name, age, idDocument) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, customer.getId());
                statement.setString(2, customer.getName());
                statement.setInt(3, customer.getAge());
                statement.setInt(4, customer.getIdDocument());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE customer SET name = ?, age = ?, idDocument = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, customer.getName());
                statement.setInt(2, customer.getAge());
                statement.setInt(3, customer.getIdDocument());
                statement.setInt(4, customer.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM customer WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT id, name, age, idDocument FROM customer";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int idCustomer = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        int age = resultSet.getInt("age");
                        int idDocument = resultSet.getInt("idDocument");
                        customers.add(new Customer(idCustomer, name, age, idDocument));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
