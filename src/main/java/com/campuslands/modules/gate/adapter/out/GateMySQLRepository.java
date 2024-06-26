package com.campuslands.modules.gate.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.modules.flightfare.domain.Flightfare;
import com.campuslands.modules.gate.domain.Gate;
import com.campuslands.modules.gate.infrastructure.GateRepository;


public class GateMySQLRepository implements GateRepository{
    private final String url;
    private final String user;
    private final String password;

    public GateMySQLRepository (String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override

    public void save(Gate gate){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO gate (gateNumber, idAirport) values (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, gate.getGateNumber());
                statement.setInt(2, gate.getIdAirport());
                statement.executeUpdate();
            } catch (Exception e) {
                e.getStackTrace();
            }  
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void update(Gate gate){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE gate SET gateNumber = ?, idAirport = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, gate.getGateNumber());
                statement.setInt(2, gate.getIdAirport());
                statement.setInt(3, gate.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Optional<Gate> findById(int id){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, gateNumber, idAirport FROM gate WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if(resultSet.next()){
                        Gate gate = new Gate(
                            resultSet.getInt("id"),
                            resultSet.getString("gateNumber"),
                            resultSet.getInt("idAirport")
                        );
                        return Optional.of(gate);
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
            String query = "DELETE FROM gate WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Gate> findAll(){
        List<Gate> gate = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, gateNumber, idAirport FROM gate";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while(resultSet.next()){
                        Gate gate2 = new Gate(
                            resultSet.getInt("id"),
                            resultSet.getString("gateNumber"),
                            resultSet.getInt("idAirport")
                        );
                        gate.add(gate2);
                    }
                } 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gate;
    }


    
}
