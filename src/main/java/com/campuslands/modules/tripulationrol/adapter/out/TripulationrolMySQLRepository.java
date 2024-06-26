package com.campuslands.modules.tripulationrol.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.modules.tripulationrol.domain.Tripulationrol;
import com.campuslands.modules.tripulationrol.infraestructure.TripulationrolRepository;

public class TripulationrolMySQLRepository implements TripulationrolRepository  {
    private final String url;
    private final String user;
    private final String password;

    public TripulationrolMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Tripulationrol tripulationrol) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO tripulationrol (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripulationrol.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Tripulationrol tripulationrol) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE tripulationrol SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, tripulationrol.getName());
                statement.setInt(2, tripulationrol.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Tripulationrol> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM tripulationrol WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Tripulationrol tripulationrol = new Tripulationrol(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                        );
                        return Optional.of(tripulationrol);
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
            String query = "DELETE FROM tripulationrol WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tripulationrol> findAll() {
        List<Tripulationrol> tripulationroles = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM tripulationrol";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Tripulationrol tripulationrol = new Tripulationrol(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                    );
                    tripulationroles.add(tripulationrol);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripulationroles;
    }
}
