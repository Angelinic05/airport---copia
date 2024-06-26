package com.campuslands.modules.tripbookingdetail.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.modules.tripbookingdetail.domain.Tripbookingdetail;
import com.campuslands.modules.tripbookingdetail.infraestructure.TripbookingdetailRepository;

public class TripbookingdetailMySQLRepository implements TripbookingdetailRepository {
    private final String url;
    private final String user;
    private final String password;

    public TripbookingdetailMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Tripbookingdetail tripbookingdetail) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO tripbookingdetail (idTripbooking, idCustomers, idFares) VALUES (?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, tripbookingdetail.getIdTripbooking());
                statement.setInt(2, tripbookingdetail.getIdCustomers());
                statement.setInt(3, tripbookingdetail.getIdFares());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Tripbookingdetail tripbookingdetail) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE tripbookingdetail SET idTripbooking = ?, idCustomers = ?, idFares = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, tripbookingdetail.getId());
                statement.setInt(2, tripbookingdetail.getIdTripbooking());
                statement.setInt(3, tripbookingdetail.getIdCustomers());
                statement.setInt(4, tripbookingdetail.getIdFares());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Tripbookingdetail> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM tripbookingdetail WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Tripbookingdetail tripbookingdetail = new Tripbookingdetail(
                            resultSet.getInt("id"),
                            resultSet.getInt("idTripbooking"),
                            resultSet.getInt("idCustomers"),
                            resultSet.getInt("idFares")
                        );
                        return Optional.of(tripbookingdetail);
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
            String query = "DELETE FROM tripbookingdetail WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tripbookingdetail> findAll() {
        List<Tripbookingdetail> tripbookingdetails = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM tripbookingdetail";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Tripbookingdetail tripbookingdetail = new Tripbookingdetail(
                        resultSet.getInt("id"),
                        resultSet.getInt("idTripbooking"),
                        resultSet.getInt("idCustomers"),
                        resultSet.getInt("idFares")
                    );
                    tripbookingdetails.add(tripbookingdetail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tripbookingdetails;
    }
}
