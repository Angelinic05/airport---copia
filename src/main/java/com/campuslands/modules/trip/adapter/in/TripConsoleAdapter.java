package com.campuslands.modules.trip.adapter.in;

import java.sql.Date;
import java.util.Optional;
import java.util.Scanner;

import com.campuslands.modules.trip.application.TripService;
import com.campuslands.modules.trip.domain.Trip;


public class TripConsoleAdapter {
    private final TripService tripService;

    public TripConsoleAdapter(TripService tripService) {
        this.tripService = tripService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Boolean flag = true;
        while (flag){
            int choice = menu(scanner);
            Date tripDate;
            Double priceTrip;
            int idAirportOrigen;
            int idAirportDest;
            switch (choice) {
                case 1:
                    System.out.print("Ingrese la fecha del viaje: ");
                    tripDate = Date.valueOf(scanner.nextLine());

                    System.out.print("Ingrese el precio del viaje: ");
                    priceTrip = scanner.nextDouble();

                    System.out.print("Ingrese la id ciudad origen: ");
                    idAirportOrigen = scanner.nextInt();

                    System.out.print("Ingrese la id ciudad destino: ");
                    idAirportDest = scanner.nextInt();

                    Trip newTrip = new Trip(tripDate, priceTrip, idAirportOrigen, idAirportDest);
                    
                    tripService.createTrip(newTrip);
                    break;

                case 2:
                    System.out.print("Ingrese  ID a actualizar: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    Optional<Trip> optionalUpdatedTrip = tripService.getTripById(updateId);
                    optionalUpdatedTrip.ifPresentOrElse(updatedTrip -> {
                        int optSubMenu = -1;
                        String submenu = "¿Qué desea actualizar?\n1. tripDate\n2. priceTripe\n3. idAirportOrigen\n4. idAirportDest\n0. Salir\n";
                
                        while (optSubMenu != 0) {
                            System.out.println(submenu);
                            optSubMenu = Integer.parseInt(scanner.nextLine());
                
                            switch (optSubMenu) {
                                case 1:
                                    System.out.print("Ingrese la nueva fecha del viaje: ");
                                    Date tripDateupdate = Date.valueOf(scanner.nextLine());
                                    updatedTrip.setTripDate(tripDateupdate);
                                    break;
                                case 2:
                                    System.out.print("Ingrese el nuevo precio del viaje: ");
                                    Double idPriceTripUpdated = Double.parseDouble(scanner.nextLine());
                                    updatedTrip.setPriceTrip(idPriceTripUpdated);
                                    break;
                                case 3:
                                    System.out.print("Ingrese la nueva coordenada x: ");
                                    int idAirportOrigenUpdated = Integer.parseInt(scanner.nextLine());
                                    updatedTrip.setIdAirportOrigen(idAirportOrigenUpdated);
                                    break;
                                case 4:
                                    System.out.print("Ingrese la nueva coordenada y: ");
                                    int idAirportDestintUpdated = Integer.parseInt(scanner.nextLine());
                                    updatedTrip.setIdAirportDestint(idAirportDestintUpdated);
                                    break;
                            }
                        }
                        tripService.updateTrip(updatedTrip);
                    }, () -> System.out.println("No se encontró el aeropuerto con ID: " + updateId));
                    break;


                case 3:
                    System.out.print("Ingrese el Id del viaje a buscar: ");
                    int findId = scanner.nextInt();
                    scanner.nextLine();

                    Optional<Trip> trip = tripService.getTripById(findId);
                    trip.ifPresentOrElse(
                        p -> System.out.println("ID: " + p.getId() + ", viaje Date: " + p.getTripDate() + ", Precio del viaje: " + p.getPriceTrip() + ", Id Ciudad Origen: "+ p.getIdAirportOrigen() + ", IdCiudad Destino: "+ p.getIdAirportDestint()),
                        () -> System.out.println("Viaje no encontrado")
                    );
                    break;

                case 4:
                    System.out.print("Ingrese el Id del viaje a borrar: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    tripService.deleteTrip(deleteId);
                    break;

                case 5:
                    tripService.getAllTrips().forEach(p -> {
                        System.out.println("ID: " + p.getId() + ", viaje Date: " + p.getTripDate() + ", Precio del viaje: " + p.getPriceTrip() + ", Id Ciudad Origen: "+ p.getIdAirportOrigen() + ", IdCiudad Destino: "+ p.getIdAirportDestint());
                    });
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
    private int menu(Scanner scanner){
        System.out.println("1. Crear Viaje");
        System.out.println("2. Actualizar Viaje");
        System.out.println("3. Buscar Viaje por ID");
        System.out.println("4. Eliminar Viaje");
        System.out.println("5. Listar todos los viajes");
        System.out.println("0. Salir");
        System.out.println("");
        System.out.print("Ingrese la opcion: ");
        int choice = -1;
        while (choice < 0 || choice > 5) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice > 6) {                    
                    System.out.println("Ingrese una opcion valida (1 - 5).");
                }
            } catch (Exception e) {
                System.out.println("Ingrese una opcion valida (1 - 5).");
            }
        }
        return choice;
    }
    
}
