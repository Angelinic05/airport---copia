package com.campuslands.modules.model.adapter.in;

import java.sql.Date;
import java.util.Optional;
import java.util.Scanner;
import com.campuslands.modules.model.application.ModelService;
import com.campuslands.modules.model.domain.Model;
import com.campuslands.modules.trip.domain.Trip;



public class ModelConsoleAdapter {
    private final ModelService modelService;

    public ModelConsoleAdapter(ModelService modelService) {
        this.modelService = modelService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Boolean flag = true;
        while (flag) {
            int choice = menu(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el nombre del modelo: ");
                    String createName = scanner.nextLine();

                    System.out.print("Ingrese el ID de manufacturas del modelo: ");
                    int crateIdManufacturas = scanner.nextInt();

                    Model model = new Model(createName, crateIdManufacturas);
                    modelService.saveModel(model);
                    break;
                
                case 2:
                    System.out.print("Ingrese  ID a actualizar: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    Optional<Model> optionalUpdatedModel = modelService.findByIdModel(updateId);
                    optionalUpdatedModel.ifPresentOrElse(updatedModel -> {
                        int optSubMenu = -1;
                        String submenu = "¿Qué desea actualizar?\n1. name\n2. idManufactures\n0. Salir\n";
                
                        while (optSubMenu != 0) {
                            System.out.println(submenu);
                            optSubMenu = Integer.parseInt(scanner.nextLine());
                
                            switch (optSubMenu) {
                                case 1:
                                    System.out.print("Ingrese el nuevo nombre: ");
                                    String nameupdate = scanner.nextLine();
                                    updatedModel.setName(nameupdate);
                                    break;
                                case 2:
                                    System.out.print("Ingrese el nuevo ID de manufacturas del modelo: ");
                                    int idPriceTripUpdated = Integer.parseInt(scanner.nextLine());
                                    updatedModel.setIdManufactures(idPriceTripUpdated);
                                    break;
                            }
                        }
                        modelService.updateModel(updatedModel);
                    }, () -> System.out.println("No se encontró el aeropuerto con ID: " + updateId));
                    break;


                case 3:
                    System.out.print("Ingrese el Id del modelo a buscar: ");
                    int findId = scanner.nextInt();
                    Optional<Model> model1 = modelService.findByIdModel(findId);
                        model1.ifPresentOrElse(
                        p -> System.out.println("ID: " + p.getId() + ", Nombre: " + p.getName() + "IdManufacturas: " + p.getIdManufactures()),
                        () -> System.out.println("modelo no encontrado")
                    );
                    break;

                case 4:
                    System.out.print("Ingrese el Id del modelo a borrar: ");
                    int deleteId = scanner.nextInt();
                    modelService.deleteModel(deleteId);
                    break;

                case 5:
                    modelService.findAllModel().forEach(p -> {
                        System.out.println("ID: " + p.getId() + ", Nombre: " + p.getName() + " IdManufacturas: " + p.getIdManufactures());
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
        System.out.println("1. Crear un modelo");
        System.out.println("2. Actualizar un modelo");
        System.out.println("3. Buscar un modelo por ID");
        System.out.println("4. Eliminar un modelo");
        System.out.println("5. Listar todos los modelos");
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
