package com.campuslands.modules.payment.adapter.in;

import com.campuslands.modules.payment.application.PaymentService;
import com.campuslands.modules.payment.domain.Payment;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.sql.Date;

public class PaymentConsoleAdapter {
    private PaymentService paymentService;

    public PaymentConsoleAdapter(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int id;
        int idTripBooking;
        int idPaymentType;
        int cardNumber;
        Date date;
        while (true) {
            int choice = menu(scanner);
            
            switch (choice) {
                case 1:
                    List<Payment> payments = paymentService.findAllPayments();
                    System.out.println("Listado de pagos:");
                    payments.forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("Agregar Pago:");
                    System.out.print("Id de la Reserva de viaje: ");
                    idTripBooking = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Id tipo de pago: ");
                    idPaymentType = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Numero de la tarjeta: ");
                    cardNumber = scanner.nextInt();
                    scanner.nextLine();
                    date = Date.valueOf(java.time.LocalDate.now());
                    System.out.print("Fecha: " + date + "\n");
                    scanner.nextLine();
                    
                    Payment payment = new Payment(idTripBooking, idPaymentType, cardNumber, date);
                    paymentService.savePayment(payment);
                    break;
                case 3:
                    System.out.println("Actualizar pago:");
                    System.out.print("Ingrese el id del pago a actualizar: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    Optional<Payment> paymentToUpdate = paymentService.findPaymentById(idToUpdate);
                    paymentToUpdate.ifPresentOrElse(updatedPayment -> {
                        int optSubMenu = -1;
                        String submenu = "¿Qué desea actualizar?\n1. Id de Reserva\n2. Id del tipo de pago\n3. Numero De la tarjeta\\n" + //
                                                        "4. Fecha del pago\n0. Salir\n";
                        
                        while (optSubMenu != 0) {
                            System.out.println(submenu);
                            optSubMenu = Integer.parseInt(scanner.nextLine());
                            
                            switch (optSubMenu) {
                                case 1:
                                    System.out.print("Nuevo Id de Reserva: ");
                                    Integer idPaymentTypeUpdate = Integer.parseInt(scanner.nextLine());
                                    updatedPayment.setIdPaymentType(idPaymentTypeUpdate);
                                    break;
                                case 2:
                                    System.out.print("Nueva Id del tipo de pago: ");
                                    int idPaymenttypeUpdate = Integer.parseInt(scanner.nextLine());
                                    updatedPayment.setIdPaymentType(idPaymenttypeUpdate);
                                    break;
                                case 3:
                                    System.out.print("Nuevo Numero De la tarjeta: ");
                                    int cardNumberUpdate = Integer.parseInt(scanner.nextLine());
                                    updatedPayment.setCardNumber(cardNumberUpdate);
                                    break;
                                case 4:
                                    System.out.print("Nueva Fecha del pago: ");
                                    Date dateUpdate = Date.valueOf(scanner.nextLine());
                                    updatedPayment.setDate(dateUpdate);
                                    break;
                            }
                        }
                        paymentService.updatePayment(updatedPayment);
                    }, () -> System.out.println("No se encontró el pago con id " + idToUpdate));
                                        break;
                case 4:
                    System.out.println("Borrar pago:");
                    System.out.print("Id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    paymentService.deletePayment(id);
                    break;
                case 5:
                    System.out.println("Buscar pago:");
                    System.out.print("Id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    paymentService.findPaymentById(id).ifPresent(System.out::println);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Ingrese una opcion valida (1 - 5).");
            }
        }
    }

    private int menu(Scanner scanner) {
        System.out.println("Gestor de Pagos:");
        System.out.println("1. Listar Pagos");
        System.out.println("2. Agregar Pago");
        System.out.println("3. Actualizar Pago");
        System.out.println("4. Borrar Pago");
        System.out.println("5. Buscar Pago");
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

