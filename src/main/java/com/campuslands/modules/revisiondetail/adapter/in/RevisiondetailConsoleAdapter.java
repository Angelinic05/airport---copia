package com.campuslands.modules.revisiondetail.adapter.in;


import java.util.Optional;
import java.util.Scanner;

import com.campuslands.modules.revisiondetail.application.RevisiondetailService;
import com.campuslands.modules.revisiondetail.domain.Revisiondetail;



public class RevisiondetailConsoleAdapter {
    private final RevisiondetailService revisiondetailService;

    public RevisiondetailConsoleAdapter(RevisiondetailService revisiondetailService) {
        this.revisiondetailService = revisiondetailService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Boolean flag = true;
        while (flag) {
            int choice = menu(scanner);
            String description;
            int idEmployee;
            switch (choice) {
                case 1:
                    System.out.print("Ingrese la descripcion: ");
                    description = scanner.nextLine();

                    System.out.print("Ingrese la id del empleado: ");
                    idEmployee = scanner.nextInt();

                    Revisiondetail newStatus = new Revisiondetail(description, idEmployee);
                    revisiondetailService.createRevisiondetail(newStatus);

                    break;
                case 2:

                    System.out.print("Ingrese  ID a actualizar: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    Optional<Revisiondetail> optionalUpdatedRevisiondetail = revisiondetailService.findRevisiondetailById(updateId);
                    optionalUpdatedRevisiondetail.ifPresentOrElse(updatedRevisiondetail -> {
                        int optSubMenu = -1;
                        String submenu = "¿Qué desea actualizar?\n1. description\n2. idEmployee\n0. Salir\n";
                
                        while (optSubMenu != 0) {
                            System.out.println(submenu);
                            optSubMenu = Integer.parseInt(scanner.nextLine());
                
                            switch (optSubMenu) {
                                case 1:
                                    System.out.print("Ingrese la nueva descripcion: ");
                                    updatedRevisiondetail.setDescription(scanner.nextLine());
                                    break;
                                case 2:
                                    System.out.print("Ingrese el nuevo id de la ciudad: ");
                                    int idEmployeeUpdated = Integer.parseInt(scanner.nextLine());
                                    updatedRevisiondetail.setIdEmployee(idEmployeeUpdated);
                                    break;
                            }
                        }
                        revisiondetailService.updateRevisiondetail(updatedRevisiondetail);
                    }, () -> System.out.println("No se encontró el detalle de la revision con ID: " + updateId));
                    break;

                case 3:
                    System.out.print("Ingrese el Id del detalle de la revision a buscar: ");
                    int findId = scanner.nextInt();
                    scanner.nextLine();

                    Optional<Revisiondetail> status = revisiondetailService.findRevisiondetailById(findId);
                        status.ifPresentOrElse(
                        p -> System.out.println("ID: " + p.getId() + ", Descripcion: " + p.getDescription() + " , Id empleado: " + p.getIdEmployee()),
                        () -> System.out.println("detalle de la revision no encontrado")
                    );
                    break;

                case 4:
                    System.out.print("Ingrese el Id del detalle de la revision a borrar: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    revisiondetailService.deleteRevisiondetail(deleteId);
                    break;

                case 5:
                    revisiondetailService.getAllRevisions().forEach(p -> {
                        System.out.println("ID: " + p.getId() + ", Descripcion: " + p.getDescription() + " , Id empleado: " + p.getIdEmployee());
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
        System.out.println("1. Crear Detalle de revision");
        System.out.println("2. Actualizar Detalle de revision");
        System.out.println("3. Buscar Detalle de revision por ID");
        System.out.println("4. Eliminar Detalle de revision");
        System.out.println("5. Listar todos los Detalles de las revisiones");
        System.out.println("0. Salir");
        System.out.println("");
        System.out.print("Ingrese la opcion: ");
        int choice = -1;
        while (choice < 0 || choice > 6) {
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
