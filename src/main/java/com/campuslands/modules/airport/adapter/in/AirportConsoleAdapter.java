package com.campuslands.modules.airport.adapter.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.campuslands.modules.airport.application.AirportService;
import com.campuslands.modules.airport.domain.Airport;

public class AirportConsoleAdapter {
    
    private AirportService airportService;
    private Scanner scanner;
    
    public AirportConsoleAdapter(AirportService airportService) {
        this.airportService = airportService;
        this.scanner = new Scanner(System.in);
    }
    public void start() {

        Scanner scanner = new Scanner(System.in);
        Boolean flag = true;

        while (flag) {
            int choice = menu();
            String name;
            Double xPosition;
            Double yPosition;
            int idCity;
            switch (choice) {
                case 1:
                    System.out.print("Ingrese el nombre del aeropuerto: ");
                    name = scanner.nextLine();
                    System.out.print("Ingrese el id de la ciudad: ");
                    idCity = Integer.parseInt(scanner.nextLine());
                    System.out.print("Ingrese la coordenada x del aeropuerto: ");
                    xPosition = Double.parseDouble(scanner.nextLine());
                    System.out.print("Ingrese la coordenada y del aeropuerto: ");
                    yPosition = Double.parseDouble(scanner.nextLine());

                    Airport newAirport = new Airport(name, idCity, xPosition, yPosition);
                    airportService.saveAirport(newAirport);

                    break;
                case 2:
                    System.out.print("Ingrese ID a actualizar: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    Optional<Airport> optionalUpdatedAirport = airportService.findAirportById(updateId);
                    optionalUpdatedAirport.ifPresentOrElse(updatedAirport -> {
                        int optSubMenu = -1;
                        String submenu = "¿Qué desea actualizar?\n1. name\n2. idCity\n3. xPosition\n4. yPosition\n0. Salir\n";

                        while (optSubMenu != 0) {
                            System.out.println(submenu);
                            optSubMenu = Integer.parseInt(scanner.nextLine());

                            switch (optSubMenu) {
                                case 1:
                                    System.out.print("Ingrese el nuevo nombre: ");
                                    updatedAirport.setName(scanner.nextLine());
                                    break;
                                case 2:
                                    System.out.print("Ingrese el nuevo id de la ciudad: ");
                                    int idCityUpdated = Integer.parseInt(scanner.nextLine());
                                    updatedAirport.setIdCity(idCityUpdated);
                                    break;
                                case 3:
                                    System.out.print("Ingrese la nueva coordenada x: ");
                                    Double xPositionUpdated = Double.parseDouble(scanner.nextLine());
                                    updatedAirport.setxPosition(xPositionUpdated);
                                    break;
                                case 4:
                                    System.out.print("Ingrese la nueva coordenada y: ");
                                    Double yPositionUpdated = Double.parseDouble(scanner.nextLine());
                                    updatedAirport.setyPosition(yPositionUpdated);
                                    break;
                            }
                        }
                        airportService.updateAirport(updatedAirport);
                    }, () -> System.out.println("No se encontró el aeropuerto con ID: " + updateId));
                    break;

                case 3:
                    System.out.print("Ingrese el Id del aeropuerto a buscar: ");
                    int findId = scanner.nextInt();
                    scanner.nextLine();

                    Optional<Airport> status = airportService.findAirportById(findId);
                    status.ifPresentOrElse(
                            p -> System.out.println(p),
                            () -> System.out.println("Aeropuerto no encontrado")
                    );

                    break;

                case 4:
                    System.out.print("Ingrese el Id del Aeropuerto a borrar: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    airportService.deleteAirport(deleteId);
                    break;

                case 5:
                    airportService.findAllAirports().forEach(p -> {
                        System.out.println(p);
                    });
                    break;

                case 6:
                    System.out.println(airportService.getAirportsByAirline().toString());
                    
                    System.out.print("Ingrese el Id del aeropuerto de origen: ");
                    int startId = scanner.nextInt();
                    scanner.nextLine(); // Consume el salto de línea pendiente
                    System.out.print("Ingrese el Id del aeropuerto de destino: ");
                    int endId = scanner.nextInt();
                    scanner.nextLine(); // Consume el salto de línea pendiente
                
                    List<Airport> path = airportService.findShortestPath(startId, endId);
                
                    if (path.isEmpty()) {
                        System.out.println("No se encontró una ruta.");
                    } else {
                        System.out.print("Ruta más corta: ");
                        for (int i = 0; i < path.size(); i++) {
                            Airport airport = path.get(i);
                            System.out.print("[" + airport.getName() + "]");
                            if (i < path.size() - 1) {
                                System.out.print(" -> ");
                            }
                        }
                        System.out.println();
                        
                        // Mostrar escalas (aeropuertos intermedios)
                        if (path.size() > 2) {
                            System.out.print("Escalas: ");
                            for (int i = 1; i < path.size() - 1; i++) {
                                Airport airport = path.get(i);
                                System.out.print("[" + airport.getName() + "]");
                                if (i < path.size() - 2) {
                                    System.out.print(" -> ");
                                }
                            }
                            System.out.println();
                        }
                    }
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    scanner.nextLine();
                    return;

                default:
                    System.out.println("Opcion invalida, intentelo de nuevo.");
            }
        }
    }

    private int menu() {
        System.out.println("1. Crear Aeropuerto");
        System.out.println("2. Actualizar Aeropuerto");
        System.out.println("3. Buscar Aeropuerto por ID");
        System.out.println("4. Eliminar Aeropuerto");
        System.out.println("5. Listar todos los Aeropuertos");
        System.out.println("6. Encontrar ruta óptima entre aeropuertos");
        System.out.println("0. Salir");
        System.out.print("Ingrese la opción: ");

        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Ingrese una opción válida (0 - 6).");
        }
        return choice;
    }

    private void createAirport() {
        System.out.print("Ingrese el nombre del aeropuerto: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese el id de la ciudad: ");
        int idCity = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese la coordenada x del aeropuerto: ");
        double xPosition = Double.parseDouble(scanner.nextLine());
        System.out.print("Ingrese la coordenada y del aeropuerto: ");
        double yPosition = Double.parseDouble(scanner.nextLine());

        Airport newAirport = new Airport(name, idCity, xPosition, yPosition);
        airportService.saveAirport(newAirport);
    }

    private void updateAirport() {
        System.out.print("Ingrese ID a actualizar: ");
        int updateId = Integer.parseInt(scanner.nextLine());
        airportService.findAirportById(updateId).ifPresentOrElse(updatedAirport -> {
            int optSubMenu = -1;
            String submenu = "¿Qué desea actualizar?\n1. name\n2. idCity\n3. xPosition\n4. yPosition\n0. Salir\n";

            while (optSubMenu != 0) {
                System.out.println(submenu);
                optSubMenu = Integer.parseInt(scanner.nextLine());

                switch (optSubMenu) {
                    case 1:
                        System.out.print("Ingrese el nuevo nombre: ");
                        updatedAirport.setName(scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Ingrese el nuevo id de la ciudad: ");
                        int idCityUpdated = Integer.parseInt(scanner.nextLine());
                        updatedAirport.setIdCity(idCityUpdated);
                        break;
                    case 3:
                        System.out.print("Ingrese la nueva coordenada x: ");
                        double xPositionUpdated = Double.parseDouble(scanner.nextLine());
                        updatedAirport.setxPosition(xPositionUpdated);
                        break;
                    case 4:
                        System.out.print("Ingrese la nueva coordenada y: ");
                        double yPositionUpdated = Double.parseDouble(scanner.nextLine());
                        updatedAirport.setyPosition(yPositionUpdated);
                        break;
                }
            }
            airportService.updateAirport(updatedAirport);
        }, () -> System.out.println("No se encontró el aeropuerto con ID: " + updateId));
    }

    private void findAirportById() {
        System.out.print("Ingrese el Id del aeropuerto a buscar: ");
        int findId = Integer.parseInt(scanner.nextLine());

        airportService.findAirportById(findId).ifPresentOrElse(
                airport -> System.out.println(airport),
                () -> System.out.println("Aeropuerto no encontrado")
        );
    }

    private void deleteAirport() {
        System.out.print("Ingrese el Id del Aeropuerto a borrar: ");
        int deleteId = Integer.parseInt(scanner.nextLine());
        airportService.deleteAirport(deleteId);
    }

    private void listAllAirports() {
        airportService.findAllAirports().forEach(System.out::println);
    }

    private void findShortestPath() {
        System.out.print("Ingrese el Id del aeropuerto de origen: ");
        int startId = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el Id del aeropuerto de destino: ");
        int endId = Integer.parseInt(scanner.nextLine());

        List<Airport> path = airportService.findShortestPath(startId, endId);

        if (path.isEmpty()) {
            System.out.println("No se encontró una ruta.");
        } else {
            System.out.print("Ruta más corta: ");
            for (int i = 0; i < path.size(); i++) {
                Airport airport = path.get(i);
                System.out.print("[" + airport.getName() + "]");
                if (i < path.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }
}