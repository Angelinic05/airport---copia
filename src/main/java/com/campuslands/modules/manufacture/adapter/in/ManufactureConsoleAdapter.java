package com.campuslands.modules.manufacture.adapter.in;

import java.util.Optional;
import java.util.Scanner;
import com.campuslands.modules.manufacture.application.ManufactureService;
import com.campuslands.modules.manufacture.domain.Manufacture;

public class ManufactureConsoleAdapter {
    private final ManufactureService manufactureService;

    public ManufactureConsoleAdapter(ManufactureService manufactureService) {
        this.manufactureService = manufactureService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int choice = menu(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el nombre del fabricante: ");
                    String createName = scanner.nextLine();

                    Manufacture manufacture = new Manufacture(createName);
                    manufactureService.saveManufacture(manufacture);
                    break;
                
                case 2:
                    System.out.print("Ingrese ID del fabricante a actualizar: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.print("Ingrese el nuevo nombre: ");
                    String updateName = scanner.nextLine();

                    Manufacture updatedManufacture = new Manufacture(updateId, updateName);
                    manufactureService.update(updatedManufacture);
                    break;

                case 3:
                    System.out.print("Ingrese el Id del fabricante a buscar: ");
                    int findId = scanner.nextInt();
                    Optional<Manufacture> manufacture1 = manufactureService.findByIdManufacture(findId);
                        manufacture1.ifPresentOrElse(
                        p -> System.out.println("ID: " + p.getId() + ", Nombre: " + p.getName()),
                        () -> System.out.println("fabricante no encontrado")
                    );
                    break;

                case 4:
                    System.out.print("Ingrese el Id del fabricante a borrar: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    manufactureService.delete(deleteId);;
                    break;

                case 5:
                    manufactureService.findAll().forEach(p -> {
                        System.out.println("ID: " + p.getId() + ", Nombre: " + p.getName());
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
        System.out.println("1. Crear un fabricante");
        System.out.println("2. Actualizar un fabricante");
        System.out.println("3. Buscar un fabricante por ID");
        System.out.println("4. Eliminar un fabricante");
        System.out.println("5. Listar todos los fabricantes");
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
