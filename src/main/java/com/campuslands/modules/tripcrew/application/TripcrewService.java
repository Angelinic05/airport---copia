package com.campuslands.modules.tripcrew.application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.campuslands.modules.employee.domain.Employee;
import com.campuslands.modules.employee.infrastructure.EmployeeRepository;
import com.campuslands.modules.flightconnection.domain.Flightconnection;
import com.campuslands.modules.flightconnection.infrastructure.FlightconnectionRepository;
import com.campuslands.modules.tripcrew.domain.Tripcrew;
import com.campuslands.modules.tripcrew.infraestructure.TripcrewRepository;

public class TripcrewService{
    private final TripcrewRepository tripcrewRepository;
    private EmployeeRepository employeeRepository;
    private FlightconnectionRepository flightconnectionRepository;

    public TripcrewService(TripcrewRepository tripcrewRepository) {
        this.tripcrewRepository = tripcrewRepository;
    }

    public TripcrewService(TripcrewRepository tripcrewRepository, EmployeeRepository employeeRepository, FlightconnectionRepository flightconnectionRepository){
        this.tripcrewRepository = tripcrewRepository;
        this.employeeRepository = employeeRepository;
        this.flightconnectionRepository = flightconnectionRepository;
    }

    public void createTripcrew(Tripcrew tripcrew) {
        tripcrewRepository.save(tripcrew);
    }

    public void updateTripcrew(Tripcrew tripcrew) {
        tripcrewRepository.update(tripcrew);
    }

    public Optional<Tripcrew> getTripcrewById(int id) {
        return tripcrewRepository.findById(id);
    }

    public void deleteTripcrew(int id) {
        tripcrewRepository.delete(id);
    }

    public List<Tripcrew> getAllTripcrews() {
        return tripcrewRepository.findAll();
    }

    public int selectEmployee(){
        Scanner sc = new Scanner(System.in);
        List<Employee> lista = employeeRepository.selectAvaliableEmployee();
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + "- " + lista.get(i));
        }
        System.out.println("0 para salir.");
        System.out.println("\nSeleccione el empleado: ");
        while (true) {
            try {
                int opc = sc.nextInt() - 1;

                if (opc == -1) {
                    return -1;
                } 

                if (opc < 0 || opc >= lista.size()) {
                    System.out.println("Selección inválida. Por favor, ingrese un número entre 0 y " + (lista.size() - 1) + ".");
                }
                else {
                    Employee empleado = lista.get(opc);
                    return empleado.getId();
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                sc.next();
            }
        }

    }

    public int selectFlightconnection(){
        Scanner sc = new Scanner(System.in);
        List<Flightconnection> lista = flightconnectionRepository.avaliableFlights();
        
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + "- " + lista.get(i));
        }
        System.out.println("0 para salir.");
        System.out.println("\nSeleccione el trayecto: ");
        while (true) {
            try {
                int opc = sc.nextInt() - 1;

                if (opc == -1) {
                    return -1;
                }
                if (opc < 0 || opc >= lista.size()) {
                    System.out.println("Selección inválida. Por favor, ingrese un número entre 0 y " + (lista.size() - 1) + ".");
                }
                else {
                    Flightconnection trayecto = lista.get(opc);
                    return trayecto.getId();
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
                sc.next();
            }
        }

    }
}
