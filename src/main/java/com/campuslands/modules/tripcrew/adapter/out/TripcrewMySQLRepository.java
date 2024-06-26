package com.campuslands.modules.tripcrew.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.modules.tripcrew.domain.Tripcrew;
import com.campuslands.modules.tripcrew.infraestructure.TripcrewRepository;

public class TripcrewMySQLRepository implements TripcrewRepository{
    private final String url;
    private final String user;
    private final String password;

    public TripcrewMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Tripcrew tripcrew) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO tripcrew (idEmployee, idConnection) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, tripcrew.getIdEmployee());
                statement.setInt(2, tripcrew.getIdConnection());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Tripcrew tripcrew) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE tripcrew SET idEmployee = ?, idConnection = ?  WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, tripcrew.getIdEmployee());
                statement.setInt(2, tripcrew.getId());
                statement.setInt(3, tripcrew.getIdConnection());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Tripcrew> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM tripcrew WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Tripcrew tripcrew = new Tripcrew(
                            resultSet.getInt("id"),
                            resultSet.getInt("idEmployee"),
                            resultSet.getInt("idConnection")
                        );
                        return Optional.of(tripcrew);
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
            String query = "DELETE FROM tripcrew WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tripcrew> findAll() {
        List<Tripcrew> tripcrews = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM tripcrew";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Tripcrew tripcrew = new Tripcrew(
                        resultSet.getInt("id"),
                        resultSet.getInt("idEmployee"),
                        resultSet.getInt("idConnection")
                    );
                    tripcrews.add(tripcrew);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripcrews;
    }
}
