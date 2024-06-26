package com.campuslands.modules.revision.adapter.in;

import java.sql.Date;
import java.util.Optional;
import java.util.Scanner;


import com.campuslands.modules.revision.application.RevisionService;
import com.campuslands.modules.revision.domain.Revision;

public class RevisionConsoleAdapter {
    private final RevisionService revisionService;

    public RevisionConsoleAdapter(RevisionService revisionService) {
        this.revisionService = revisionService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Boolean flag = true;
        while (flag) {
            int choice = menu(scanner);
            Date revisionDate;
            int idPlane;
            switch (choice) {
                case 1:
                    System.out.print("Ingrese la fecha de la revision: ");
                    revisionDate = Date.valueOf(scanner.nextLine());
                    System.out.print("Ingrese la id del avion: ");
                    idPlane = scanner.nextInt();
                    
                    Revision newRevision = new Revision(revisionDate, idPlane);
                    revisionService.saveRevision(newRevision);
                    break;

                case 2:
                    System.out.print("Ingrese  ID a actualizar: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    Optional<Revision> optionalUpdatedRevision = revisionService.findRevisionById(updateId);
                    optionalUpdatedRevision.ifPresentOrElse(updatedRevision -> {
                        int optSubMenu = -1;
                        String submenu = "¿Qué desea actualizar?\n1. date\n2. idPlane\n0. Salir\n";
                
                        while (optSubMenu != 0) {
                            System.out.println(submenu);
                            optSubMenu = Integer.parseInt(scanner.nextLine());
                
                            switch (optSubMenu) {
                                case 1:
                                    System.out.print("Ingrese la fecha de la revision: ");
                                    Date revisionDateupdate = Date.valueOf(scanner.nextLine());
                                    updatedRevision.setRevisionDate(revisionDateupdate);
                                    break;
                                case 2:
                                    System.out.print("Ingrese el nuevo id del avion: ");
                                    int idPlaneUpdated = Integer.parseInt(scanner.nextLine());
                                    updatedRevision.setIdPlane(idPlaneUpdated);
                                    break;
                            }
                        }
                        revisionService.updateRevision(updatedRevision);
                    }, () -> System.out.println("No se encontró la revision con ID: " + updateId));
                    break;

                case 3:
                    System.out.print("Ingrese el Id de la revision a buscar: ");
                    int findId = scanner.nextInt();
                    scanner.nextLine();
                    Optional<Revision> revision = revisionService.findRevisionById(findId);
                    revision.ifPresentOrElse(
                        p -> System.out.println("ID: " + p.getId() + ", Revision Date: " + p.getRevisionDate() + ", Id Plane: "+p.getIdPlane()),
                        () -> System.out.println("Revision no encontrada")
                    );
                    break;
                    

                case 4:
                    System.out.print("Ingrese el Id de la revision a borrar: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    revisionService.deleteRevision(deleteId);
                    break;

                case 5:
                    revisionService.getAllRevisions().forEach(p -> {
                        System.out.println("ID: " + p.getId() + ", Revision Date: " + p.getRevisionDate() + ", Id Plane: "+p.getIdPlane());
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
        System.out.println("1. Crear Revision");
        System.out.println("2. Actualizar Revision");
        System.out.println("3. Buscar Revision por ID");
        System.out.println("4. Eliminar Revision");
        System.out.println("5. Listar todas las revisiones");
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
