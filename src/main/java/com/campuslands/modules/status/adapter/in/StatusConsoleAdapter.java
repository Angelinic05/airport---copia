package com.campuslands.modules.status.adapter.in;

import java.util.Scanner;

import com.campuslands.modules.status.application.StatusService;
import com.campuslands.modules.status.domain.Status;

import java.util.Optional;

public class StatusConsoleAdapter {
    private final StatusService statusService;

    public StatusConsoleAdapter(StatusService statusService) {
        this.statusService = statusService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int choice = menu(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el nombre del estado: ");
                    String createName = scanner.nextLine();

                    Status newStatus = new Status(createName);
                    statusService.createStatus(newStatus);

                    break;
                case 2:
                    System.out.print("Ingrese  ID a actualizar: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el nuevo nombre del estado: ");
                    String updateName = scanner.nextLine();

                    Status updatedStatus = new Status(updateId, updateName);
                    statusService.updateStatus(updatedStatus);
                    break;

                case 3:
                    System.out.print("Ingrese el Id del estado a buscar: ");
                    int findId = scanner.nextInt();
                    scanner.nextLine();

                    Optional<Status> status = statusService.getStatusById(findId);
                        status.ifPresentOrElse(
                        p -> System.out.println("ID: " + p.getId() + ", Nombre: " + p.getNombre()),
                        () -> System.out.println("Estado no encontrado")
                    );
                    break;

                case 4:
                    System.out.print("Ingrese el Id del estado a borrar: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    statusService.deleteStatus(deleteId);
                    break;

                case 5:
                    statusService.getAllStatuses().forEach(p -> {
                        System.out.println("ID: " + p.getId() + ", Nombre: " + p.getNombre());
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
        System.out.println("1. Crear estado");
        System.out.println("2. Actualizar estado");
        System.out.println("3. Buscar estado por ID");
        System.out.println("4. Eliminar estado");
        System.out.println("5. Listar todos los estados");
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

