package com.campuslands.modules.trip.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.modules.trip.domain.Trip;
import com.campuslands.modules.trip.infraestructure.TripRepository;

public class TripMySQLRepository implements TripRepository {
    private final String url;
    private final String user;
    private final String password;

    public TripMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Trip trip) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO trip (tripDate, priceTrip, idAirportOrigen, idAirportDest) VALUES (?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDate(1, trip.getTripDate());
                statement.setDouble(2, trip.getPriceTrip());
                statement.setInt(3, trip.getIdAirportOrigen());
                statement.setInt(4, trip.getIdAirportDestint());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Metodo que devuelve el id del trip creaedo
    @Override
    public int saveAndReturnId(Trip trip) {
        int generatedId = -1; // Default value if ID is not generated
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO trip (tripDate, priceTrip, idAirportOrigen, idAirportDest) VALUES (?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setDate(1, trip.getTripDate());
                statement.setDouble(2, trip.getPriceTrip());
                statement.setInt(3, trip.getIdAirportOrigen());
                statement.setInt(4, trip.getIdAirportDestint());
                statement.executeUpdate();

                // Retrieve the generated keys
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return generatedId;
}

    @Override
    public void update(Trip trip) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE trip SET tripDate = ? , priceTrip = ?, idAirportOrigen = ?, idAirportDest = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDate(1, trip.getTripDate());
                statement.setDouble(2, trip.getPriceTrip());
                statement.setInt(3, trip.getIdAirportOrigen());
                statement.setInt(4, trip.getIdAirportDestint());
                statement.setInt(5, trip.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Trip> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trip WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Trip trip = new Trip(
                            resultSet.getInt("id"),
                            resultSet.getDate("tripDate"),
                            resultSet.getDouble("priceTrip"),
                            resultSet.getInt("idAirportOrigen"),
                            resultSet.getInt("idAirportDest")
                        );
                        return Optional.of(trip);
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
            String query = "DELETE FROM trip WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Trip> findAll() {
        List<Trip> trips = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM trip";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Trip trip = new Trip(
                        resultSet.getInt("id"),
                        resultSet.getDate("tripDate"),
                        resultSet.getDouble("priceTrip"),
                        resultSet.getInt("idAirportOrigen"),
                        resultSet.getInt("idAirportDest")
                    );
                    trips.add(trip);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }
}

