package com.campuslands.modules.revisiondetail.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.modules.revisiondetail.domain.Revisiondetail;
import com.campuslands.modules.revisiondetail.infraestructure.RevisiondetailRepository;

public class RevisiondetailMySQLRepository implements RevisiondetailRepository{
    private final String url;
    private final String user;
    private final String password;

    public RevisiondetailMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Revisiondetail revisiondetail) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO revisiondetail (description, idEmployee) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, revisiondetail.getDescription());
                statement.setInt(2, revisiondetail.getIdEmployee());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Revisiondetail revisiondetail) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE revisiondetail SET description = ?, idEmployee = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, revisiondetail.getDescription());
                statement.setInt(2, revisiondetail.getIdEmployee());
                statement.setInt(3, revisiondetail.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Revisiondetail> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM revisiondetail WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Revisiondetail revisiondetail = new Revisiondetail(
                            resultSet.getInt("id"),
                            resultSet.getString("description"),
                            resultSet.getInt("idEmployee")
                        );
                        return Optional.of(revisiondetail);
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
            String query = "DELETE FROM revisiondetail WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Revisiondetail> findAll() {
        List<Revisiondetail> revisions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM revisiondetail";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Revisiondetail revisiondetail = new Revisiondetail(
                            resultSet.getInt("id"),
                            resultSet.getString("description"),
                            resultSet.getInt("idEmployee")
                    );
                    revisions.add(revisiondetail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revisions;
    }
}
