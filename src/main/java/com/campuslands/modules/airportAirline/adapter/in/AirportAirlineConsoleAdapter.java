package com.campuslands.modules.airportAirline.adapter.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.campuslands.modules.airportAirline.application.AirportAirlineService;
import com.campuslands.modules.airportAirline.domain.AirportAirline;
public class AirportAirlineConsoleAdapter {
    private AirportAirlineService airportAirlineService;

    public AirportAirlineConsoleAdapter(AirportAirlineService airportAirlineService) {
        this.airportAirlineService = airportAirlineService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int id;
        int airlineId;
        int airportId;
        while (true) {
            int choice = menu(scanner);
            
            switch (choice) {
                case 1:
                    List<AirportAirline> airportAirlines = airportAirlineService.getAllAirportAirlines();
                    System.out.println("Listado de Airport-Airline:");
                    airportAirlines.forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("Agregar Airport-Airline:");
                    System.out.print("Airline ID: ");
                    airlineId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Airport ID: ");
                    airportId = scanner.nextInt();
                    scanner.nextLine();
                    
                    AirportAirline airportAirline = new AirportAirline(airlineId, airportId);
                    airportAirlineService.createAirportAirline(airportAirline);
                    break;
                case 3:
                    System.out.println("Actualizar Airport-Airline:");
                    System.out.print("Ingrese el Id a actualizar: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    Optional<AirportAirline>  optionalAirportAirline= airportAirlineService.getAirportAirlineById(idToUpdate);
                    optionalAirportAirline.ifPresentOrElse(updatedAirportAirline -> {
                        int optSubMenu = -1;
                        String submenu = "¿Qué desea actualizar?\n1. id de la aerolinea\n2. id del aeropuerto\n0.Salir\n";
                
                        while (optSubMenu != 0) {
                            System.out.println(submenu);
                            optSubMenu = Integer.parseInt(scanner.nextLine());
                
                            switch (optSubMenu) {
                                case 1:
                                    System.out.print("Ingrese el id de la aerolinea: ");
                                    int setIdAirline = Integer.parseInt(scanner.nextLine());
                                    updatedAirportAirline.setIdAirline(setIdAirline);
                                    break;
                                case 2:
                                    System.out.print("Ingrese el id del aeropuerto: ");
                                    int setIdAirport = Integer.parseInt(scanner.nextLine());
                                    updatedAirportAirline.setIdAirport(setIdAirport);
                                    break;
                            }
                        }
                        airportAirlineService.updateAirportAirline(updatedAirportAirline);
                    }
                        , () -> System.out.println("No se encontro el id " + idToUpdate));
                    break;
                case 4:
                    System.out.println("Borrar Airport-Airline:");
                    System.out.print("Id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    
                    airportAirlineService.deleteAirportAirline(id);
                    break;
                case 5:
                    System.out.println("Seleccionar por id Airport-Airline:");
                    System.out.print("Id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    airportAirlineService.getAirportAirlineById(id).ifPresent(System.out::println);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    scanner.nextLine();
                    return;
                default:
                    System.out.println("Ingrese una opcion valida (1 - 5).");
            }
        }
    }

    private int menu(Scanner scanner) {
        System.out.println("Gestor de Aerolinea - Aeropuerto:");
        System.out.println("1. Listar Aerolinea - Aeropuerto");
        System.out.println("2. Agregar Aerolinea - Aeropuerto");
        System.out.println("3. Actualizar Aerolinea - Aeropuerto");
        System.out.println("4. Borrar Aerolinea - Aeropuerto");
        System.out.println("5. Seleccionar por id Aerolinea - Aeropuerto");
        System.out.println("0. Salir");
        System.out.println("");
        System.out.print("Ingrese la opcion: ");
        int choice = -1;
        while (choice < 0 || choice > 5) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice > 6) {                    
                    System.out.println("Ingrese una opcion valida (1 - 4).");
                }
            } catch (Exception e) {
                System.out.println("Ingrese una opcion valida (1 - 4).");
            }
        }
        return choice;
    }
}
