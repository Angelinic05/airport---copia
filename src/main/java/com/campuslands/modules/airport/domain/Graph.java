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


    public HashMap<Integer, List<Edge>> addEdge(Airport from, Airport to) {
        double distance = calculateDistance(from, to);
        HashMap<Integer, List<Edge>> adjacencyMap = new HashMap<>();

        adjacencyMap.putIfAbsent(from.getId(), new ArrayList<>());
        adjacencyMap.putIfAbsent(to.getId(), new ArrayList<>());

        adjacencyMap.get(from.getId()).add(new Edge(to.getId(), distance));
        adjacencyMap.get(to.getId()).add(new Edge(from.getId(), distance));

        return adjacencyMap;
    }

    public void printAdjacencyList() {
        adjacencyList.forEach((airport, edges) -> {
            System.out.print(String.format("Airport id: %d\n Edges: ", airport));
            edges.forEach(edge -> {
                System.out.print(String.format("(TO: %d, WEIGHT: %.2f\n) ", edge.toId, edge.weight));
            });
            System.out.println(); // Nueva línea después de imprimir todas las aristas del aeropuerto
        });
    }

    public void addEdgeToAirports(List<Airport> newAirports) {
        HashMap<Integer, List<Edge>> adjacencyMapTemp = new HashMap<>();

        for (int i = 0; i < newAirports.size() - 1; i++) {

            Airport from = newAirports.get(i);
            Airport to = newAirports.get((i + 1) % newAirports.size());

            HashMap<Integer, List<Edge>> edges = addEdge(from, to);
            for (Map.Entry<Integer, List<Edge>> entry : edges.entrySet()) {
                int id = entry.getKey();
                List<Edge> edgeList = entry.getValue();
                
                // Check if the id already exists in adjacencyMapTemp
                if (adjacencyMapTemp.containsKey(id)) {
                    adjacencyMapTemp.get(id).addAll(edgeList);
                } else {
                    adjacencyMapTemp.put(id, new ArrayList<>(edgeList));
                }
            }

            airports.put(from.getId(), from);
            airports.put(to.getId(), to);
        }

        for (Map.Entry<Integer, List<Edge>> entry : adjacencyMapTemp.entrySet()) {
            int id = entry.getKey();
            List<Edge> edgeList = entry.getValue();
            
            if (adjacencyList.containsKey(id)) {
                adjacencyList.get(id).addAll(edgeList);
            } else {
                adjacencyList.put(id, new ArrayList<>(edgeList));
            }
        }
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

            System.out.println("Explorando aeropuerto: " + airports.get(current).getName());
            printAdjacencyList();
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
