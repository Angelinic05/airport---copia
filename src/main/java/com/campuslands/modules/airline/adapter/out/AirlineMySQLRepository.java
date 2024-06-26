package com.campuslands.modules.airline.adapter.out;

import com.campuslands.modules.airline.infrastructure.AirlineRepository;
import com.campuslands.modules.airline.domain.Airline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class AirlineMySQLRepository implements AirlineRepository {

    private final String url;
    private final String user;
    private final String password;

    public AirlineMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    
    @Override
    public void save(Airline airline) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO airline (name) VALUES (?)");) {
                statement.setString(1, airline.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Airline airline) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE airline SET name =? WHERE id =?");) {
                statement.setString(1, airline.getName());
                statement.setInt(2,airline.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Airline> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM airline WHERE id =?");) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return Optional.of(new Airline(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    ));
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
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM airline WHERE id =?");) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Airline> findAll() {
        List<Airline> airlines = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM airline");) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    airlines.add(new Airline(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airlines;
    }


}
