package com.campuslands.modules.plane.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.modules.plane.domain.Plane;
import com.campuslands.modules.plane.infrastructure.PlaneRepository;

public class PlaneMySQLRepository implements PlaneRepository{
    private final String url;
    private final String user;
    private final String password;

    public PlaneMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Plane plane){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO plane (capacity, fabricationDate, idStatus, idModel) VALUES (?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, plane.getCapacity());
                statement.setDate(2, plane.getFabricationDate());
                statement.setInt(3, plane.getIdStatus());
                statement.setInt(4, plane.getIdModel());
                statement.executeUpdate();
            } catch (Exception e) {
                e.getStackTrace();
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void update(Plane plane){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE plane SET capacity = ?, fabricationDate = ?, idStatus = ?, idModel = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, plane.getCapacity());
                statement.setDate(2, plane.getFabricationDate());
                statement.setInt(3, plane.getIdStatus());
                statement.setInt(4, plane.getIdModel());
                statement.setInt(5, plane.getId());
                statement.executeUpdate();                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Optional<Plane> findById(int id){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, capacity, fabricationDate, idStatus, idModel FROM plane WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if(resultSet.next()){
                        Plane plane = new Plane(
                            resultSet.getInt("id"),
                            resultSet.getInt("capacity"),
                            resultSet.getDate("fabricationDate"),
                            resultSet.getInt("idStatus"),
                            resultSet.getInt("idModel")
                        );
                        return Optional.of(plane);
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
            String query = "DELETE FROM plane WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }  
        } catch (SQLException e) {
            e.printStackTrace();;
        }
    }
    
    @Override
    public List<Plane> findAll(){
        List<Plane> plane = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, capacity, fabricationDate, idStatus, idModel FROM plane";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while(resultSet.next()){
                        Plane plane2 = new Plane(
                            resultSet.getInt("id"),
                            resultSet.getInt("capacity"),
                            resultSet.getDate("fabricationDate"),
                            resultSet.getInt("idStatus"),
                            resultSet.getInt("idModel")
                        );
                        plane.add(plane2);
                    }   
                } 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plane;
    }
}
