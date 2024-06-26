package com.campuslands.modules.flightfare.adapter.in;

import java.sql.Date;
import java.util.Optional;
import java.util.Scanner;

import com.campuslands.modules.flightfare.domain.Flightfare;
import com.campuslands.modules.trip.domain.Trip;
import com.campuslands.modules.flightfare.application.FlightfareService;

public class FlightfareConsoleAdapter {
    private FlightfareService flightfareService;

    public FlightfareConsoleAdapter(FlightfareService flightfareService){
        this.flightfareService = flightfareService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Boolean flag = true;
        while (flag) {
            int choice = menu(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Ingrese la descripcion de la Tarifa de vuelo: ");
                    String createDescription = scanner.nextLine();

                    System.out.print("Ingrese los detalles del de la Tarifa de vuelo: ");
                    String createDetails = scanner.nextLine();
                    scanner.nextLine();

                    System.out.print("Ingrese el valor de la Tarifa de vuelo: ");
                    double createValue = scanner.nextDouble();

                    Flightfare flightfare = new Flightfare(createDescription, createDetails, createValue);
                    flightfareService.saveFlightfare(flightfare);
                    break;

                case 2:
                    System.out.print("Ingrese  ID a actualizar: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    Optional<Flightfare> optionalUpdatedFlightfare = flightfareService.findByIdFlightfare(updateId);
                    optionalUpdatedFlightfare.ifPresentOrElse(updatedFlightfare -> {
                        int optSubMenu = -1;
                        String submenu = "¿Qué desea actualizar?\n1. description\n2. details\n3. value\n0. Salir\n";
                
                        while (optSubMenu != 0) {
                            System.out.println(submenu);
                            optSubMenu = Integer.parseInt(scanner.nextLine());
                
                            switch (optSubMenu) {
                                case 1:
                                    System.out.print("Ingrese la nueva descripcion de la tarifa de vuelo: ");
                                    String descriptionUupdate = scanner.nextLine();
                                    updatedFlightfare.setDescription(descriptionUupdate);
                                    break;
                                case 2:
                                    System.out.print("Ingrese los detalles nuevos de la tarifa de vuelo: ");
                                    String detailsUpdated = scanner.nextLine();
                                    updatedFlightfare.setDetails(detailsUpdated);
                                    break;
                                case 3:
                                    System.out.print("Ingrese el valor nuevo de la tarifa de vuelo: ");
                                    Double valueUpdated = Double.parseDouble(scanner.nextLine());
                                    updatedFlightfare.setValue(valueUpdated);
                                    break;
                            }
                        }
                        flightfareService.updateFlightfare(updatedFlightfare);
                    }, () -> System.out.println("No se encontró el aeropuerto con ID: " + updateId));
                    break;


                case 3:
                    System.out.print("Ingrese el Id de la tarifa de vuelo a buscar: ");
                    int findId = scanner.nextInt();
                    scanner.nextLine();

                    Optional<Flightfare> flightfare1 = flightfareService.findByIdFlightfare(findId);
                    flightfare1.ifPresentOrElse( 
                        p -> System.out.println("ID:" + p.getId() + ", descripcción: " + p.getDescription() + ", detalles: " + p.getDetails() + ", valor: " + p.getValue()), 
                        () -> System.out.println("Tarifa de vuelo no encontrada")); 
                    break;

                case 4:
                    System.out.print("Ingrese el Id de la tarifa de vuelo a borrar: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    flightfareService.deleteFlightfare(deleteId);
                    break;

                case 5:
                    flightfareService.findAll().forEach(p -> {
                        System.out.println("ID:" + p.getId() + ", descripcción: " + p.getDescription() + ", detalles: " + p.getDetails() + ", valor: " + p.getValue());
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
        System.out.println("1. Crear una tarifa de vuelo");
        System.out.println("2. Actualizar una tarifa de vuelo");
        System.out.println("3. Buscar una tarifa de vuelo por ID");
        System.out.println("4. Eliminar una tarifa de vuelo");
        System.out.println("5. Listar todas las tarifas de vuelo");
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
