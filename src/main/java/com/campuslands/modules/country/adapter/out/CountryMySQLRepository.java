package com.campuslands.modules.country.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.modules.country.infrastructure.CountryRepository;
import com.campuslands.modules.country.domain.Country;

public class CountryMySQLRepository implements CountryRepository {

    private final String url;
    private final String user;
    private final String password;

    public CountryMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public Optional<Country> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM country WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int idCountry = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        return Optional.of(new Country(idCountry, name));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(Country country) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "INSERT INTO country (name) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, country.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Country country) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "UPDATE country SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, country.getName());
                statement.setInt(2, country.getId());
                statement.executeUpdate();
               
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "DELETE FROM country WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                statement.executeUpdate();
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Country> findAll() {
        List<Country> countries = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT id, name FROM country";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int idCountry = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        countries.add(new Country(idCountry, name));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }
}

