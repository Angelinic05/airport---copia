package com.campuslands.modules.employee.adapter.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.sql.Date;
import com.campuslands.modules.employee.application.EmployeeService;
import com.campuslands.modules.employee.domain.Employee;
import com.campuslands.modules.trip.domain.Trip;

public class EmployeeConsoleAdapter {

    private final EmployeeService employeeService;

    public EmployeeConsoleAdapter(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Boolean flag = true;
        while (flag) {
            int choice = menu(scanner);
            switch (choice) {
                case 1:
                    System.out.print("Ingrese el id del empleado: ");
                    int createId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Ingrese el nombre del empleado: ");
                    String createName = scanner.nextLine();

                    System.out.print("Ingrese el ID del rol del empleado: ");
                    int createIdRol = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Ingrese la fecha de entrada del empleado (formato: yyyy-mm-dd): ");
                    String createEntryDate = scanner.nextLine();
                    Date sqlDate = Date.valueOf(createEntryDate);

                    System.out.print("Ingrese el ID de la aerolinea del empleado: ");
                    int createIdAirline = scanner.nextInt();

                    System.out.print("Ingrese el ID del aeropuerto del empleado: ");
                    int createIdAirport = scanner.nextInt();

                    Employee employee = new Employee(createId, createName, createIdRol, sqlDate, createIdAirline, createIdAirport);
                    employeeService.saveEmployee(employee);
                    break;

                case 2:
                    System.out.print("Ingrese  ID a actualizar: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    Optional<Employee> optionalUpdatedEmployee = employeeService.findByIdEmployee(updateId);
                    optionalUpdatedEmployee.ifPresentOrElse(updatedEmployee -> {
                        int optSubMenu = -1;
                        String submenu = "¿Qué desea actualizar?\n1. name\n2. idRol\n3. entryDate\n4. idAirline\n5. idAirport\n0. Salir\n";
                
                        while (optSubMenu != 0) {
                            System.out.println(submenu);
                            optSubMenu = Integer.parseInt(scanner.nextLine());
                
                            switch (optSubMenu) {
                                case 1:
                                    System.out.print("Ingrese el nuevo nombre: ");
                                    String nameupdate = scanner.nextLine();
                                    updatedEmployee.setName(nameupdate);
                                    break;
                                case 2:
                                    System.out.print("Ingrese el nuevo ID del rol del empleado: ");
                                    int idRolUpdated = Integer.parseInt(scanner.nextLine());
                                    updatedEmployee.setIdRol(idRolUpdated);
                                    break;
                                case 3:
                                    System.out.print("Ingrese la nueva fecha de entrada del empleado (formato: yyyy-mm-dd): ");
                                    Date entryDateUpdated = Date.valueOf(scanner.nextLine());
                                    updatedEmployee.setEntryDate(entryDateUpdated);
                                    break;
                                case 4:
                                    System.out.print("Ingrese el nuevo ID de la aerolinea del empleado: ");
                                    int idAirlineUpdated = Integer.parseInt(scanner.nextLine());
                                    updatedEmployee.setIdAirline(idAirlineUpdated);
                                    break;
                                case 5:
                                    System.out.print("Ingrese el nuevo ID del aeropuerto del empleado: ");
                                    int idAirportUpdated = Integer.parseInt(scanner.nextLine());
                                    updatedEmployee.setIdAirpot(idAirportUpdated);
                                    break;
                            }
                        }
                        employeeService.updateEmployee(updatedEmployee);
                    }, () -> System.out.println("No se encontró el empleado con ID: " + updateId));
                    break;



                case 3:
                    System.out.print("Ingrese el Id del empleado a buscar: ");
                    int findId = scanner.nextInt();
                    scanner.nextLine();

                    Optional<Employee> employee1 = employeeService.findByIdEmployee(findId);
                    employee1.ifPresentOrElse( //ifPresentOrElse es un método de la clase Optional que permite manejar el caso en el que el Optional contiene un valor y el caso en el que no contiene un valor.
                        p -> System.out.println("ID:" + p.getId() + ", Nombre : " + p.getName() + ", idRol: " + p.getIdRol() + ", entryDate: " + p.getEntryDate() + ", idAirline: " + p.getIdAirline() + ", idAirport: " + p.getIdAirpot()), // Este método recibe dos argumentos: una acción a realizar si el valor está presente
                        () -> System.out.println("Empleado no encontrado")); //y una acción a realizar si el valor no esta presente
                    break;
                    /*  p: Es un parámetro que representa el objeto contenido en el Optional si está presente.
                        ->: Es el operador lambda que separa los parámetros de la implementación.
                        System.out.println("ID: " + p.getId() + ", Nombre: " + p.getNombre()): Es la acción a realizar si el Optional contiene un valor.
                        (): Indica que no hay parámetros para la segunda acción.
                        System.out.println("Status no encontrado"): Es la acción a realizar si el Optional no contiene un valor. */

                case 4:
                    System.out.print("Ingrese el Id del empleado a borrar: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    employeeService.deleteEmployee(deleteId);
                    break;

                case 5:
                    employeeService.findAllEmployee().forEach(p -> {
                        System.out.println("ID:" + p.getId() + ", Nombre : " + p.getName() + ", idRol: " + p.getIdRol() + ", entryDate: " + p.getEntryDate() + ", idAirline: " + p.getIdAirline() + ", idAirport: " + p.getIdAirpot());
                    });
                    break;

                case 0:
                    System.out.println("Saliendo..");
                    scanner.nextLine();
                    return;
                default:
                    System.out.println("Opcion invalida, intentelo de nuevo.");
            }
        }
    }

    private int menu(Scanner scanner){
        System.out.println("1. Crear empleado");
        System.out.println("2. Actualizar empleado");
        System.out.println("3. Buscar empleado por ID");
        System.out.println("4. Eliminar empleado");
        System.out.println("5. Listar todos empleados");
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
