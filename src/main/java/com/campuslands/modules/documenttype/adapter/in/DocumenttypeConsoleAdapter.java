package com.campuslands.modules.documenttype.adapter.in;

import java.util.Optional;
import java.util.Scanner;

import com.campuslands.modules.documenttype.domain.Documenttype;
import com.campuslands.modules.documenttype.application.DocumenttypeService;

public class DocumenttypeConsoleAdapter {
    private DocumenttypeService documenttypeService;
    
    public DocumenttypeConsoleAdapter(DocumenttypeService documenttypeService) {
        this.documenttypeService = documenttypeService;
    }
    public void start() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            int choice = menu(scanner);
            String desc;
            int id;
            switch (choice) {
                case 1:
                    System.out.print("Ingrese el nombre dele tipo de doc: ");
                    desc = scanner.nextLine();
                    Documenttype newDocumenttype = new Documenttype(desc);
                    documenttypeService.saveDocumenttype(newDocumenttype);

                    break;
                case 2:
                    System.out.print("Ingrese  ID a actualizar: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    Documenttype updatedDocumenttype = documenttypeService.findDocumenttypeById(updateId).orElse(null);
                    if(updatedDocumenttype != null){
                        System.out.println("Ingrese el nombre del tipo de documento:");
                        updatedDocumenttype.setName(scanner.nextLine());
                    }
                    documenttypeService.updateDocumenttype(updatedDocumenttype);
                    break;

                case 3:
                    System.out.print("Ingrese el Id del tipo de documento a buscar: ");
                    int findId = scanner.nextInt();
                    scanner.nextLine();

                    Optional<Documenttype> status = documenttypeService.findDocumenttypeById(findId);
                        status.ifPresentOrElse(
                        p -> System.out.println(p),
                        () -> System.out.println("Tipo de documento  no encontrado")
                    );

                    break;

                case 4:
                    System.out.print("Ingrese el Id del tipo de documento a borrar: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    documenttypeService.deleteDocumenttype(deleteId);
                    break;

                case 5:
                    documenttypeService.finAllDocumenttypes().forEach(System.out::println);
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
        System.out.println("1. Crear tipos de documento");
        System.out.println("2. Actualizar tipos de documento");
        System.out.println("3. Buscar tipos de documento por ID");
        System.out.println("4. Eliminar tipos de documento");
        System.out.println("5. Listar todos los tipos de documento");
        System.out.println("0. Salir");
        System.out.println("");
        System.out.print("Ingrese la opcion: ");
        int choice = -1;
        while (choice < 1 || choice > 6) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice > 6) {                    
                    System.out.println("Ingrese una opcion valida (1 -5).");
                }
            } catch (Exception e) {
                System.out.println("Ingrese una opcion valida (1 - 5).");
            }
        }
        return choice;
    }
}
