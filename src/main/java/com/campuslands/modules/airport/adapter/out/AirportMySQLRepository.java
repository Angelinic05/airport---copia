package com.campuslands.modules.airport.adapter.out;

import com.campuslands.modules.airport.domain.Airport;
import com.campuslands.modules.flightconnection.domain.Flightconnection;
import com.campuslands.modules.flightconnection.application.FlightconnectionService;
import com.campuslands.modules.airport.infrastructure.AirportRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class AirportMySQLRepository implements AirportRepository {

    private final String url;
    private final String user;
    private final String password;

    public AirportMySQLRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void save(Airport airport) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "INSERT INTO airport (name, idCity, xPosition, yPosition) VALUES (?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, airport.getName());
                statement.setInt(2, airport.getIdCity());
                statement.setDouble(3, airport.getxPosition());
                statement.setDouble(4, airport.getyPosition());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Airport airport) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "UPDATE airport SET name =?, idCity =?, xPosition =?, yPosition=? WHERE id=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, airport.getName());
                statement.setInt(2, airport.getIdCity());
                statement.setDouble(3, airport.getxPosition());
                statement.setDouble(4, airport.getyPosition());
                statement.setInt(5, airport.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Airport> findById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM airport WHERE id =?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(new Airport(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("idCity"),
                            resultSet.getDouble("xPosition"),
                            resultSet.getDouble("yPosition")
                        ));
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
            String query = "DELETE FROM airport WHERE id =?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Airport> findAll() {
        List<Airport> airports = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT * FROM airport";
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    airports.add(new Airport(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("idCity"),
                        resultSet.getDouble("xPosition"),
                        resultSet.getDouble("yPosition")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airports;
    }

    @Override
    public HashMap<Integer, List<Integer>> getAirportsByAirline(){
        HashMap<Integer, List<Integer>> airportsByAirline = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT DISTINCT idAirline FROM airportairline";
            String query2 = "SELECT idAirport FROM airportairline where idAirline =?";
            try (PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    int idAirline = resultSet.getInt("idAirline");
                    try (PreparedStatement statement2 = connection.prepareStatement(query2)) {

                        statement2.setInt(1, idAirline);
                        ResultSet resultSet2 = statement2.executeQuery();

                        List<Integer> airportList = new ArrayList<>();
                        
                        while (resultSet2.next()) {
                            int idAirport = resultSet2.getInt("idAirport");
                            airportList.add(idAirport);
                        }

                        airportsByAirline.put(idAirline, airportList);
                        }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airportsByAirline;
    }

}