package com.campuslands.modules.revision.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.modules.revision.domain.Revision;
import com.campuslands.modules.revision.infraestructure.RevisionRepository;

public class RevisionMySQLRepository implements RevisionRepository{
    private final String url;
    private final String user;
    private final String password;

    public RevisionMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Revision revision) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO revision (revisionDate, idPlane) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDate(1, revision.getRevisionDate());
                statement.setInt(2, revision.getIdPlane());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Revision revision) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE revision SET revisionDate = ?, idPlane = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setDate(1, revision.getRevisionDate());
                statement.setInt(2, revision.getIdPlane());
                statement.setInt(3, revision.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Revision> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM revision WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Revision revision = new Revision(
                            resultSet.getInt("id"),
                            resultSet.getDate("revisionDate"),
                            resultSet.getInt("idplane")
                        );
                        return Optional.of(revision);
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
            String query = "DELETE FROM revision WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Revision> findAll() {
        List<Revision> revisions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM revision";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Revision revision = new Revision(
                            resultSet.getInt("id"),
                            resultSet.getDate("revisionDate"),
                            resultSet.getInt("idPlane")
                    );
                    revisions.add(revision);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revisions;
    }
}
