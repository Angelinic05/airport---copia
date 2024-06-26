package com.campuslands.modules.status.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.modules.status.domain.Status;
import com.campuslands.modules.status.infraestructure.StatusRepository;

public class StatusMySQLRepository implements StatusRepository{
    private final String url;
    private final String user;
    private final String password;

    public StatusMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Status status) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO status (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, status.getNombre());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Status status) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE status SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, status.getNombre());
                statement.setInt(2, status.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Status> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM status WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Status status = new Status(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                        );
                        return Optional.of(status);
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
            String query = "DELETE FROM status WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Status> findAll() {
        List<Status> statuses = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM status";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Status status = new Status(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                    );
                    statuses.add(status);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statuses;
    }
}
