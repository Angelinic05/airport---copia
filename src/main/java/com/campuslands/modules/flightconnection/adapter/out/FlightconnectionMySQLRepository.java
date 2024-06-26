package com.campuslands.modules.flightconnection.adapter.out;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.campuslands.modules.flightconnection.domain.Flightconnection;
import com.campuslands.modules.flightconnection.infrastructure.FlightconnectionRepository;

public class FlightconnectionMySQLRepository implements FlightconnectionRepository{

    private final String url;
    private final String user;
    private final String password;

    public FlightconnectionMySQLRepository(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override

    public void save(Flightconnection flightconnection){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO flightconnection (connectionNumber, idTrip, idPlane, idAirport) VALUES (?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, flightconnection.getConnectionNumber());
                statement.setInt(2, flightconnection.getIdTrip());
                statement.setInt(3, flightconnection.getIdPlane());
                statement.setInt(4, flightconnection.getIdAirport());
                statement.executeUpdate(); //PREGUNTAR BIEN QUE ES ESTO
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Flightconnection flightconnection){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE flightconnection SET connectionNumber = ?, idTrip = ?, idPlane = ?, idAirport = ? WHERE id = ?";  
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, flightconnection.getConnectionNumber());
                statement.setInt(2, flightconnection.getIdTrip());
                statement.setInt(3, flightconnection.getIdPlane());
                statement.setInt(4, flightconnection.getIdAirport());
                statement.setInt(5, flightconnection.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }
    
    @Override
    public Optional<Flightconnection> findById(int id){
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
             String query = "SELECT id, connectionNumber, idTrip, idPlane, idAirport FROM flightconnection WHERE id = ?";
             try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if(resultSet.next()){
                        Flightconnection flightconnection = new Flightconnection(
                            resultSet.getInt("id"),
                            resultSet.getString("connectionNumber"),
                            resultSet.getInt("idTrip"),
                            resultSet.getInt("idPlane"),
                            resultSet.getInt("idAirport")
                            );
                            return Optional.of(flightconnection);
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
            String query = "DELETE FROM flightconnection WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Flightconnection> findAll(){
        List<Flightconnection> flightconnection = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT id, connectionNumber, idTrip, idPlane, idAirport FROM flightconnection";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Flightconnection flightconnection2 = new Flightconnection(
                            resultSet.getInt("id"),
                            resultSet.getString("connectionNumber"),
                            resultSet.getInt("idTrip"),
                            resultSet.getInt("idPlane"),
                            resultSet.getInt("idAirport")
                        );
                        flightconnection.add(flightconnection2);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightconnection;
    }

    @Override
    public List<Flightconnection> avaliableFlights() {
        List<Flightconnection> flightconnection = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT fc.id, fc.connectionNumber, fc.idTrip, fc.idPlane, fc.idAirport FROM flightconnection fc LEFT JOIN tripcrew tc ON fc.id = tc.idConnection WHERE tc.idConnection IS NULL ";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Flightconnection flightconnection2 = new Flightconnection(
                            resultSet.getInt("id"),
                            resultSet.getString("connectionNumber"),
                            resultSet.getInt("idTrip"),
                            resultSet.getInt("idPlane"),
                            resultSet.getInt("idAirport")
                        );
                        flightconnection.add(flightconnection2);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightconnection;
    } 
}
