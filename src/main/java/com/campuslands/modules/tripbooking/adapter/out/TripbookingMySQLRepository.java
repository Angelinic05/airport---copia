package com.campuslands.modules.tripbooking.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.modules.tripbooking.domain.Tripbooking;
import com.campuslands.modules.tripbooking.infraestructure.TripbookingRepository;

public class TripbookingMySQLRepository implements TripbookingRepository{
    private final String url;
    private final String user;
    private final String password;

    public TripbookingMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Tripbooking tripbooking) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO tripbooking (date, idTrip) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDate(1, tripbooking.getDate());
                statement.setInt(2, tripbooking.getIdTrip());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Tripbooking tripbooking) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE tripbooking SET date = ?, idTrip = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, tripbooking.getId());
                statement.setDate(2, tripbooking.getDate());
                statement.setInt(3, tripbooking.getIdTrip());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Tripbooking> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM tripbooking WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Tripbooking tripbooking = new Tripbooking(
                            resultSet.getInt("id"),
                            resultSet.getDate("date"),
                            resultSet.getInt("idTrip")
                        );
                        return Optional.of(tripbooking);
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
            String query = "DELETE FROM tripbooking WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tripbooking> findAll() {
        List<Tripbooking> tripbookings = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM tripbooking";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Tripbooking tripbooking = new Tripbooking(
                        resultSet.getInt("id"),
                        resultSet.getDate("date"),
                        resultSet.getInt("idTrip")
                    );
                    tripbookings.add(tripbooking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripbookings;
    }
}
