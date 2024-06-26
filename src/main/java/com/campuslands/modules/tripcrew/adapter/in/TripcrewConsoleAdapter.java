package com.campuslands.modules.tripcrew.adapter.in;

import java.sql.Date;
import java.util.Optional;
import java.util.Scanner;

import com.campuslands.modules.trip.domain.Trip;
import com.campuslands.modules.tripcrew.application.TripcrewService;
import com.campuslands.modules.tripcrew.domain.Tripcrew;

public class TripcrewConsoleAdapter {
    private final TripcrewService tripcrewService;

    public TripcrewConsoleAdapter(TripcrewService tripcrewService) {
        this.tripcrewService = tripcrewService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Boolean flag = true;
        while (flag) {
            int choice = menu(scanner);
            int idEmployee;
            int idConnection;
            switch (choice) {
                case 1:
                    int createIdEmployee = tripcrewService.selectEmployee();
                    if(createIdEmployee == -1){return;}
                    int createIdConnection = tripcrewService.selectFlightconnection();
                    if(createIdConnection == -1){return;}

                    Tripcrew newTripcrew = new Tripcrew(createIdEmployee, createIdConnection);
                    tripcrewService.createTripcrew(newTripcrew);

                    break;
                case 2:
                    System.out.print("Ingrese  ID a actualizar: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    Optional<Tripcrew> optionalUpdatedTripcrew = tripcrewService.getTripcrewById(updateId);
                    optionalUpdatedTripcrew.ifPresentOrElse(updatedTripcrew -> {
                        int optSubMenu = -1;
                        String submenu = "¿Qué desea actualizar?\n1. idEmployee\n2. idConnection\n0. Salir\n";
                
                        while (optSubMenu != 0) {
                            System.out.println(submenu);
                            optSubMenu = Integer.parseInt(scanner.nextLine());
                
                            switch (optSubMenu) {
                                case 1:
                                    System.out.print("Ingrese la nueva fecha del viaje: ");
                                    int idEmployeeupdate = Integer.parseInt(scanner.nextLine());
                                    updatedTripcrew.setIdEmployee(idEmployeeupdate);
                                    break;
                                case 2:
                                    System.out.print("Ingrese el nuevo precio del viaje: ");
                                    int idConnectionUpdated = Integer.parseInt(scanner.nextLine());
                                    updatedTripcrew.setIdConnection(idConnectionUpdated);
                                    break;
                            }
                        }
                        tripcrewService.updateTripcrew(updatedTripcrew);
                    }, () -> System.out.println("No se encontró la tripulacion con ID: " + updateId));
                    break;

                case 3:
                    System.out.print("Ingrese el Id de la tripulacion de viaje a buscar: ");
                    int findId = scanner.nextInt();
                    scanner.nextLine();

                    Optional<Tripcrew> tripcrew = tripcrewService.getTripcrewById(findId);
                        tripcrew.ifPresentOrElse(
                        p -> System.out.println("ID: " + p.getId() + ", Id empleado: " + p.getIdEmployee() + ", Id coneccion: " + p.getIdConnection()),
                        () -> System.out.println("Tripulacion de viaje no encontrado")
                    );
                    break;

                case 4:
                    System.out.print("Ingrese el Id de la tripulacion de viaje a borrar: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    tripcrewService.deleteTripcrew(deleteId);
                    break;

                case 5:
                    tripcrewService.getAllTripcrews().forEach(p -> {
                        System.out.println("ID: " + p.getId() + ", Id empelado: " + p.getIdEmployee() + ", Id coneccion: " + p.getIdConnection());
                    });
                    break;

                case 0:
                    System.out.println("Saliendo... ");
                    scanner.nextLine();
                    return;
                default:
                    System.out.println("Opcion invalida, intentelo de nuevo.");
            }
        }
    }

    private int menu(Scanner scanner){
        System.out.println("1. Crear tripulacion de viaje");
        System.out.println("2. Actualizar tripulacion de viaje");
        System.out.println("3. Buscar tripulacion de viaje por ID");
        System.out.println("4. Eliminar tripulacion de viaje");
        System.out.println("5. Listar todas las tripulaciones de viaje");
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
