package com.campuslands.modules.paymenttype.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.SQLException;

import com.campuslands.modules.paymenttype.domain.Paymenttype;
import com.campuslands.modules.paymenttype.infrastructure.PaymenttypeRepository;

public class PaymenttypeMySQLRepository implements PaymenttypeRepository  {
    private final String url;
    private final String user;
    private final String password;

    private PaymenttypeMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Paymenttype paymenttype) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO paymenttype (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, paymenttype.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Paymenttype paymenttype) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE paymenttype SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, paymenttype.getName());
                statement.setInt(2, paymenttype.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Paymenttype> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM paymenttype WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Paymenttype paymenttype = new Paymenttype(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                        );
                        return Optional.of(paymenttype);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM paymenttype WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Paymenttype> findAll() {
        List<Paymenttype> paymenttypees = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM paymenttype";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Paymenttype paymenttype = new Paymenttype(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                    );
                    paymenttypees.add(paymenttype);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paymenttypees;
    }
}
