package com.campuslands.modules.manufacture.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.modules.manufacture.domain.Manufacture;
import com.campuslands.modules.manufacture.infrastructure.ManufactureRepository;

public class ManufactureMySQLRepository implements ManufactureRepository{
    private final String url;
    private final String user;
    private final String password;

    public ManufactureMySQLRepository(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override

    public void save(Manufacture manufacture){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO manufacture (name) VALUES (?)";

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setNString(1, manufacture.getName());
                statement.executeUpdate();
            } catch (Exception e) {
                e.getStackTrace();
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void update(Manufacture manufacture){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE manufacture SET name = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, manufacture.getName());
                statement.setInt(2, manufacture.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Optional<Manufacture> findById(int id){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, name FROM manufacture WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if(resultSet.next()){
                        Manufacture manufacture = new Manufacture(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                        );
                        return Optional.of(manufacture);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    @Override
    public void delete(int id){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "DELETE FROM manufacture WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Manufacture> findAll(){
        List<Manufacture> manufacture = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT  id, name FROM manufacture";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Manufacture manufacture2 = new Manufacture(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                        );
                        manufacture.add(manufacture2);
                    }                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manufacture;
    }
}
