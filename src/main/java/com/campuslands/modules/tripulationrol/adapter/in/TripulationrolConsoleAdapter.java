package com.campuslands.modules.tripulationrol.adapter.in;

import java.util.Optional;
import java.util.Scanner;

import com.campuslands.modules.tripulationrol.application.TripulationrolService;
import com.campuslands.modules.tripulationrol.domain.Tripulationrol;


/*
Error en el actualizar java.sql.SQLSyntaxErrorException: You have an error in your SQL syntax; check the 
manual that corresponds to your MySQL server version for the right syntax to 
use near '?, idTrip = ?, idPlane = ?, idAirport = ? WHERE id = ?' at line 1 
*/

public class TripulationrolConsoleAdapter {
    private final TripulationrolService tripulationrolService;

    public TripulationrolConsoleAdapter(TripulationrolService tripulationrolService) {
        this.tripulationrolService = tripulationrolService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int choice = menu(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el rol de tripulacion: ");
                    String createName = scanner.nextLine();

                    Tripulationrol newTripulationrol = new Tripulationrol(createName);
                    tripulationrolService.createTripulationrol(newTripulationrol);

                    break;
                case 2:
                    System.out.print("Ingrese  ID a actualizar: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el nuevo rol de tripulacion: ");
                    String updateName = scanner.nextLine();

                    Tripulationrol updatedTripulationrol = new Tripulationrol(updateId, updateName);
                    tripulationrolService.updateTripulationrol(updatedTripulationrol);
                    break;

                case 3:
                    System.out.print("Ingrese el Id del rol de tripulacion a buscar: ");
                    int findId = scanner.nextInt();
                    scanner.nextLine();

                    Optional<Tripulationrol> tripulationrol = tripulationrolService.getTripulationrolById(findId);
                        tripulationrol.ifPresentOrElse(
                        p -> System.out.println("ID: " + p.getId() + ", Nombre: " + p.getName()),
                        () -> System.out.println("Rol de tripulacion no encontrado")
                    );
                    break;

                case 4:
                    System.out.print("Ingrese el Id del rol de tripulacion a borrar: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    tripulationrolService.deleteTripulationrol(deleteId);
                    break;

                case 5:
                    tripulationrolService.getAllTripulationroles().forEach(p -> {
                        System.out.println("ID: " + p.getId() + ", Nombre: " + p.getName());
                    });
                    break;

                case 6:
                    System.out.println("Saliendo...");
                    scanner.nextLine();
                    return;
                default:
                    System.out.println("Opcion invalida, intentelo de nuevo.");
            }
        }
    }

    private int menu(Scanner scanner){
        System.out.println("1. Crear rol de tripulacion");
        System.out.println("2. Actualizar rol de tripulacion");
        System.out.println("3. Buscar rol de tripulacion por ID");
        System.out.println("4. Eliminar rol de tripulacion");
        System.out.println("5. Listar todos los roles de tripulacion");
        System.out.println("6. Salir");
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
