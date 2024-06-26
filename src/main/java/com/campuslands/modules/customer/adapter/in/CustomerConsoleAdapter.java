package com.campuslands.modules.customer.adapter.in;

import com.campuslands.modules.customer.application.CustomerService;
import com.campuslands.modules.customer.domain.Customer;

import java.util.Scanner;
import java.util.List;
import java.util.Optional;

public class CustomerConsoleAdapter {
    private CustomerService customerService;

    public CustomerConsoleAdapter(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int id;
        int age;
        int idDocumenttype;
        String name;
        while (true) {
            int choice = menu(scanner);
            
            switch (choice) {
                case 1:
                    List<Customer> customers = customerService.findAllCustomers();
                    System.out.println("Listado de clientes:");
                    customers.forEach(System.out::println);
                    break;
                case 2:
                    System.out.println("Agregar cliente:");
                    System.out.print("Id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nombre: ");
                    name = scanner.nextLine();
                    System.out.print("Edad: ");
                    age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("id Tipo de documento: ");
                    idDocumenttype = scanner.nextInt();
                    scanner.nextLine();
                    
                    Customer customer = new Customer(id, name, age, idDocumenttype);
                    customerService.createCustomer(customer);
                    break;
                case 3:
                    System.out.println("Actualizar cliente:");
                    System.out.print("Ingrese el id del cliente a actualizar: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    Optional<Customer> customerToUpdate = customerService.findCustomerById(idToUpdate);
                    customerToUpdate.ifPresentOrElse(updatedCustomer -> {
                        int optSubMenu = -1;
                        String submenu = "¿Qué desea actualizar?\n1. Nombre\n2. Edad\n3. id Tipo de documento\n0. Salir\n";
                        
                        while (optSubMenu != 0) {
                            System.out.println(submenu);
                            optSubMenu = Integer.parseInt(scanner.nextLine());
                            
                            switch (optSubMenu) {
                                case 1:
                                    System.out.print("Nuevo Nombre: ");
                                    String nameUpdate = scanner.nextLine();
                                    updatedCustomer.setName(nameUpdate);
                                    break;
                                case 2:
                                    System.out.print("Nueva Edad: ");
                                    int ageUpdate = Integer.parseInt(scanner.nextLine());
                                    updatedCustomer.setAge(ageUpdate);
                                    break;
                                case 3:
                                    System.out.print("Nuevo id Tipo de documento: ");
                                    int idDocumenttypeUpdate = Integer.parseInt(scanner.nextLine());
                                    updatedCustomer.setIdDocument(idDocumenttypeUpdate);
                                    break;
                            }
                        }
                        customerService.updateCustomer(updatedCustomer);
                    }, () -> System.out.println("No se encontró el cliente con id " + idToUpdate));
                                        break;
                case 4:
                    System.out.println("Borrar cliente:");
                    System.out.print("Id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    customerService.deleteCustomer(id);
                    break;
                case 5:
                    System.out.println("Buscar cliente:");
                    System.out.print("Id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    customerService.findCustomerById(id).ifPresent(System.out::println);
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
        System.out.println("Gestor de Cliente:");
        System.out.println("1. Listar Cliente");
        System.out.println("2. Agregar Cliente");
        System.out.println("3. Actualizar Cliente");
        System.out.println("4. Borrar Cliente");
        System.out.println("5. Buscar Cliente");
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

