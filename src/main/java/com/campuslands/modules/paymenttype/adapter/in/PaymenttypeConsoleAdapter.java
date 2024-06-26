package com.campuslands.modules.paymenttype.adapter.in;

import com.campuslands.modules.paymenttype.domain.Paymenttype;
import com.campuslands.modules.paymenttype.application.PaymenttypeService;

import java.util.Optional;
import java.util.Scanner;

public class PaymenttypeConsoleAdapter {
    private final PaymenttypeService paymenttypeService;

    public PaymenttypeConsoleAdapter(PaymenttypeService paymenttype) {
        this.paymenttypeService = paymenttype;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int choice = menu(scanner);

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el nombre del tipo de pago: ");
                    String createName = scanner.nextLine();

                    Paymenttype newPaymenttype = new Paymenttype(createName);
                    paymenttypeService.savePaymenttype(newPaymenttype);

                    break;
                case 2:
                    System.out.print("Ingrese ID a actualizar: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese el nuevo nombre del tipo de pago: ");
                    String updateName = scanner.nextLine();

                    Paymenttype updatedPaymenttype = new Paymenttype(updateId, updateName);
                    paymenttypeService.updatePaymenttype(updatedPaymenttype);
                    break;

                case 3:
                    System.out.print("Ingrese el Id del tipo de pago a buscar: ");
                    int findId = scanner.nextInt();
                    scanner.nextLine();

                    Optional<Paymenttype> paymenttype = paymenttypeService.findPaymenttypeById(findId);
                        paymenttype.ifPresentOrElse(
                        p -> System.out.println("ID: " + p.getId() + ", Nombre: " + p.getName()),
                        () -> System.out.println("Tipo de pago no encontrado")
                    );
                    break;

                case 4:
                    System.out.print("Ingrese el Id del tipo de pago a borrar: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    paymenttypeService.deletePaymenttype(deleteId);
                    break;

                case 5:
                    paymenttypeService.findAllPaymenttypees().forEach(p -> {
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
        System.out.println("1. Crear tipo de pago");
        System.out.println("2. Actualizar tipo de pago");
        System.out.println("3. Buscar tipo de pago por ID");
        System.out.println("4. Eliminar tipo de pago");
        System.out.println("5. Listar todos los tipo de pagos");
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

