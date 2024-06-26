package com.campuslands.modules.airport.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Graph {
    private Map<Integer, Airport> airports = new HashMap<>();
    private Map<Integer, List<Edge>> adjacencyList = new HashMap<>();

    public void addAirport(Airport airport) {
        airports.put(airport.getId(), airport);
        adjacencyList.putIfAbsent(airport.getId(), new ArrayList<>());
    }

    public void addEdge(int fromId, int toId) {
        Airport from = airports.get(fromId);
        Airport to = airports.get(toId);
        double distance = calculateDistance(from, to);
        adjacencyList.get(fromId).add(new Edge(toId, distance));
        adjacencyList.get(toId).add(new Edge(fromId, distance)); // Assuming undirected graph
    }

    public List<Airport> findShortestPath(int startId, int endId) {
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingDouble(edge -> edge.weight));
        Map<Integer, Double> distances = new HashMap<>();
        Map<Integer, Integer> previous = new HashMap<>();

        for (int id : airports.keySet()) {
            distances.put(id, Double.MAX_VALUE);
        }
        distances.put(startId, 0.0);
        queue.add(new Edge(startId, 0.0));

        while (!queue.isEmpty()) {
            int current = queue.poll().toId;
            if (current == endId) break;

            // System.out.println("Explorando aeropuerto: " + airports.get(current).getName());
            System.out.println(adjacencyList);
            for (Edge edge : adjacencyList.get(current) ) {
                double newDist = distances.get(current) + edge.weight;
                if (newDist < distances.get(edge.toId)) {
                    distances.put(edge.toId, newDist);
                    previous.put(edge.toId, current);
                    queue.add(new Edge(edge.toId, newDist));
                    System.out.println("  - Actualizando distancia a " + airports.get(edge.toId).getName() + ": " + newDist);
                } else {
                    System.out.println("  - No se actualiza la distancia a " + airports.get(edge.toId).getName() + " (distancia actual: " + distances.get(edge.toId) + ")");
                }
            }
        }

        List<Airport> path = new ArrayList<>();
        for (Integer at = endId; at != null; at = previous.get(at)) {
            path.add(airports.get(at));
        }
        Collections.reverse(path);

        return path;
    }

    private double calculateDistance(Airport from, Airport to) {
        // Implementación de distancia basada en xPosition y yPosition
        double dx = from.getxPosition() - to.getxPosition();
        double dy = from.getyPosition() - to.getyPosition();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public void printGraph() {
        System.out.println("Aeropuertos en el grafo:");
        for (Airport airport : airports.values()) {
            System.out.println(airport);
            System.out.println("  Conexiones:");
            for (Edge edge : adjacencyList.get(airport.getId())) {
                Airport neighbor = airports.get(edge.toId);
                System.out.println("    -> " + neighbor.getName() + " (Distancia: " + edge.weight + ")");
            }
        }
    }

    private static class Edge {
        int toId;
        double weight;

        public Edge(int toId, double weight) {
            this.toId = toId;
            this.weight = weight;
        }
    }

    
    
}
