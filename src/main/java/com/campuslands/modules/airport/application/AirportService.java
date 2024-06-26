package com.campuslands.modules.airport.application;

import com.campuslands.modules.airport.infrastructure.AirportRepository;
import com.campuslands.modules.flightconnection.application.FlightconnectionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.campuslands.modules.airport.domain.Airport;
import com.campuslands.modules.airport.infrastructure.AirportRepository;
import com.campuslands.modules.airport.domain.Graph;

public class AirportService {
    private AirportRepository airportRepository;
    private Graph graph;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
        this.graph = new Graph();
        initializeGraph();
    }

    private void initializeGraph() {
        List<Airport> airports = airportRepository.findAll();
        for (Airport airport : airports) {
            graph.addAirport(airport);
            System.out.println("Añadido aeropuerto al grafo: " + airport);
        }
    }

    public void saveAirport(Airport airport) {
        airportRepository.save(airport);
        graph.addAirport(airport);
    }

    public void updateAirport(Airport airport) {
        airportRepository.update(airport);
    }

    public Optional<Airport> findAirportById(int id) {
        return airportRepository.findById(id);
    }

    public void deleteAirport(int id) {
        airportRepository.delete(id);
    }

    public List<Airport> findAllAirports() {
        return airportRepository.findAll();
    }

    public List<Airport> findShortestPath(int startId, int endId) {
        return graph.findShortestPath(startId, endId);
    }

    public Graph getGraph() {
        return graph;
    }

    public String getAirportNameById(int airportId) {
        Optional<Airport> airport = airportRepository.findById(airportId);
        return airport.isPresent() ? airport.get().getName() : "Nombre no encontrado";
    }

    public HashMap<Integer, List<Integer>> getAirportsByAirline() {
        return airportRepository.getAirportsByAirline();
    }
}