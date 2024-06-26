package com.campuslands;

import java.util.Scanner;

import com.campuslands.modules.airline.adapter.in.AirlineConsoleAdapter;
import com.campuslands.modules.airline.adapter.out.AirlineMySQLRepository;
import com.campuslands.modules.airline.application.AirlineService;

import com.campuslands.modules.airport.adapter.in.AirportConsoleAdapter;
import com.campuslands.modules.airport.adapter.out.AirportMySQLRepository;
import com.campuslands.modules.airport.application.AirportService;

import com.campuslands.modules.airportAirline.adapter.in.AirportAirlineConsoleAdapter;
import com.campuslands.modules.airportAirline.adapter.out.AirportAirlineMySQLRepository;
import com.campuslands.modules.airportAirline.application.AirportAirlineService;

import com.campuslands.modules.revision.adapter.in.RevisionConsoleAdapter;
import com.campuslands.modules.revision.adapter.out.RevisionMySQLRepository;
import com.campuslands.modules.revision.application.RevisionService;

import com.campuslands.modules.revisiondetail.adapter.in.RevisiondetailConsoleAdapter;
import com.campuslands.modules.revisiondetail.adapter.out.RevisiondetailMySQLRepository;
import com.campuslands.modules.revisiondetail.application.RevisiondetailService;


import com.campuslands.modules.status.adapter.in.StatusConsoleAdapter;
import com.campuslands.modules.status.adapter.out.StatusMySQLRepository;
import com.campuslands.modules.status.application.StatusService;

import com.campuslands.modules.trip.adapter.in.TripConsoleAdapter;
import com.campuslands.modules.trip.adapter.out.TripMySQLRepository;
import com.campuslands.modules.trip.application.TripService;

import com.campuslands.modules.tripbooking.adapter.in.TripbookingConsoleAdapter;
import com.campuslands.modules.tripbooking.adapter.out.TripbookingMySQLRepository;
import com.campuslands.modules.tripbooking.application.TripbookingService;

import com.campuslands.modules.tripbookingdetail.adapter.in.TripbookingdetailConsoleAdapter;
import com.campuslands.modules.tripbookingdetail.adapter.out.TripbookingdetailMySQLRepository;
import com.campuslands.modules.tripbookingdetail.application.TripbookingdetailService;

import com.campuslands.modules.tripcrew.adapter.in.TripcrewConsoleAdapter;
import com.campuslands.modules.tripcrew.adapter.out.TripcrewMySQLRepository;
import com.campuslands.modules.tripcrew.application.TripcrewService;

import com.campuslands.modules.tripulationrol.adapter.in.TripulationrolConsoleAdapter;
import com.campuslands.modules.tripulationrol.adapter.out.TripulationrolMySQLRepository;
import com.campuslands.modules.tripulationrol.application.TripulationrolService;

import com.campuslands.modules.city.adapter.in.CityConsoleAdapter;
import com.campuslands.modules.city.adapter.out.CityMySQLRepository;
import com.campuslands.modules.city.application.CityService;

import com.campuslands.modules.country.adapter.in.CountryConsoleAdapter;
import com.campuslands.modules.country.adapter.out.CountryMySQLRepository;
import com.campuslands.modules.country.application.CountryService;

import com.campuslands.modules.customer.adapter.in.CustomerConsoleAdapter;
import com.campuslands.modules.customer.application.CustomerService;
import com.campuslands.modules.customer.adapter.out.CustomerMySQLRepository;

import com.campuslands.modules.documenttype.adapter.in.DocumenttypeConsoleAdapter;
import com.campuslands.modules.documenttype.adapter.out.DocumenttypeMySQLRepository;
import com.campuslands.modules.documenttype.application.DocumenttypeService;

import com.campuslands.modules.employee.adapter.in.EmployeeConsoleAdapter;
import com.campuslands.modules.employee.adapter.out.EmployeeMySQLRepository;
import com.campuslands.modules.employee.application.EmployeeService;


import com.campuslands.modules.flightconnection.adapter.in.FlightconnectionConsoleAdapter;
import com.campuslands.modules.flightconnection.application.FlightconnectionService;
import com.campuslands.modules.flightconnection.adapter.out.FlightconnectionMySQLRepository;

import com.campuslands.modules.flightfare.adapter.in.FlightfareConsoleAdapter;
import com.campuslands.modules.flightfare.application.FlightfareService;
import com.campuslands.modules.flightfare.adapter.out.FlightfareMySQLRepository;

import com.campuslands.modules.gate.adapter.in.GateConsoleAdapter;
import com.campuslands.modules.gate.adapter.out.GateMySQLRepository;
import com.campuslands.modules.gate.application.GateService;

import com.campuslands.modules.manufacture.adapter.in.ManufactureConsoleAdapter;
import com.campuslands.modules.manufacture.adapter.out.ManufactureMySQLRepository;
import com.campuslands.modules.manufacture.application.ManufactureService;

import com.campuslands.modules.model.application.ModelService;
import com.campuslands.modules.model.adapter.out.ModelMySQLRepository;
import com.campuslands.modules.model.adapter.in.ModelConsoleAdapter;

import com.campuslands.modules.plane.application.PlaneService;
import com.campuslands.modules.plane.adapter.out.PlaneMySQLRepository;
import com.campuslands.modules.plane.adapter.in.PlaneConsoleAdapter;

import com.campuslands.modules.revemployee.adapter.in.RevemployeeConsoleAdapter;
import com.campuslands.modules.revemployee.adapter.out.RevemployeeMySQLRepository;
import com.campuslands.modules.revemployee.application.RevemployeeService;

public class Main {
    public static void main(String[] args) {
        // String url = "jdbc:mysql://localhost:3306/airport";
        String url = "jdbc:mysql://javaairportdb.cfecucemoghu.us-east-2.rds.amazonaws.com:3306/airport";
        String username = "airportDB";
        String password = "AngeliKikeMichi";

        RevisionMySQLRepository revisionRepository = new RevisionMySQLRepository(url, username, password);
        RevisiondetailMySQLRepository revisiondetailRepository = new RevisiondetailMySQLRepository(url, username, password);
        StatusMySQLRepository statusRepository = new StatusMySQLRepository(url, username, password);
        TripMySQLRepository tripRepository = new TripMySQLRepository(url, username, password);
        TripbookingMySQLRepository tripbookingRepository = new TripbookingMySQLRepository(url, username, password);
        TripbookingdetailMySQLRepository tripbookingdetailRepository = new TripbookingdetailMySQLRepository(url, username, password);
        TripcrewMySQLRepository tripcrewRepository = new TripcrewMySQLRepository(url, username, password);
        TripulationrolMySQLRepository tripulationrolRepository = new TripulationrolMySQLRepository(url, username, password);
        AirlineMySQLRepository airlineMySQLRepository = new AirlineMySQLRepository(url, username, password);
        AirportMySQLRepository airportMySQLRepository = new AirportMySQLRepository(url, username, password);
        AirportAirlineMySQLRepository airportAirlineMySQLRepository = new AirportAirlineMySQLRepository(url, username, password);
        CityMySQLRepository cityMySQLRepository = new CityMySQLRepository(url, username, password);
        CountryMySQLRepository countryMySQLRepository = new CountryMySQLRepository(url, username, password);
        CustomerMySQLRepository customerMySQLRepository = new CustomerMySQLRepository(url, username, password);
        DocumenttypeMySQLRepository documenttypeMySQLRepository = new DocumenttypeMySQLRepository(url, username, password);
        EmployeeMySQLRepository employeeMySQLRepository = new EmployeeMySQLRepository(url, username, password);
        FlightconnectionMySQLRepository flightconnectionMySQLRepository = new FlightconnectionMySQLRepository(url, username, password);
        FlightfareMySQLRepository flightfareMySQLRepository = new FlightfareMySQLRepository(url, username, password);
        GateMySQLRepository gateMySQLRepository = new GateMySQLRepository(url, username, password);
        ManufactureMySQLRepository manufactureMySQLRepository = new ManufactureMySQLRepository(url, username, password);
        ModelMySQLRepository modelMySQLRepository = new ModelMySQLRepository(url, username, password);
        PlaneMySQLRepository planeMySQLRepository = new PlaneMySQLRepository(url, username, password);
        RevemployeeMySQLRepository revemployeeMySQLRepository = new RevemployeeMySQLRepository(url, username, password);

        System.out.println("--------------- MENU PRINCIPAL ---------------");
        while (true) {
            System.out.println("1. airline");
            System.out.println("2. airport");
            System.out.println("3. airportairline");
            System.out.println("4. city");
            System.out.println("5. country");
            System.out.println("6. customer");
            System.out.println("7. documenttype");
            System.out.println("8. employee");
            System.out.println("9. flightconnection");
            System.out.println("10. flightfare");
            System.out.println("11. gate");
            System.out.println("12. manufacture");
            System.out.println("13. model");
            System.out.println("14. plane");
            System.out.println("15. revemployee");
            System.out.println("16. revision");
            System.out.println("17. revisiondetail");
            System.out.println("18. status");
            System.out.println("19. trip");
            System.out.println("20. tripbooking");
            System.out.println("21. tripbookingdetail");
            System.out.println("22. tripcrew");
            System.out.println("23. tripulationrol");
            System.out.println("");
            System.out.print("Ingrese la opcion: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("airline");
                    AirlineService airlineService = new AirlineService(airlineMySQLRepository);
                    AirlineConsoleAdapter airlineConsoleAdapter = new AirlineConsoleAdapter(airlineService);
                    airlineConsoleAdapter.start();
                    break;
                case 2:
                    System.out.println("airport");
                    AirportService airportService = new AirportService(airportMySQLRepository);
                    AirportConsoleAdapter airportConsoleAdapter = new AirportConsoleAdapter(airportService);
                    airportConsoleAdapter.start();
                    break;
                case 3:
                    System.out.println("airportairline");
                    AirportAirlineService airportAirlineService = new AirportAirlineService(airportAirlineMySQLRepository);
                    AirportAirlineConsoleAdapter airportAirlineConsoleAdapter = new AirportAirlineConsoleAdapter(airportAirlineService);
                    airportAirlineConsoleAdapter.start();
                    break;
                case 4:
                    System.out.println("city");
                    CityService cityService = new CityService(cityMySQLRepository);
                    CityConsoleAdapter cityConsoleAdapter = new CityConsoleAdapter(cityService);
                    cityConsoleAdapter.start();
                    break;
                case 5:
                    System.out.println("country");
                    CountryService countryService = new CountryService(countryMySQLRepository);
                    CountryConsoleAdapter countryConsoleAdapter = new CountryConsoleAdapter(countryService);
                    countryConsoleAdapter.start();
                    break;
                case 6:
                    System.out.println("customer");
                    CustomerService customerService = new CustomerService(customerMySQLRepository);
                    CustomerConsoleAdapter customerConsoleAdapter = new CustomerConsoleAdapter(customerService);
                    customerConsoleAdapter.start();
                    break;
                case 7:
                    System.out.println("documenttype");
                    DocumenttypeService documenttypeService = new DocumenttypeService(documenttypeMySQLRepository);
                    DocumenttypeConsoleAdapter documenttypeConsoleAdapter = new DocumenttypeConsoleAdapter(documenttypeService);
                    documenttypeConsoleAdapter.start();
                    break;
                case 8:
                    System.out.println("employee");
                    EmployeeService employeeService = new EmployeeService(employeeMySQLRepository);
                    EmployeeConsoleAdapter employeeConsoleAdapter = new EmployeeConsoleAdapter(employeeService);
                    employeeConsoleAdapter.start();
                    break;
                case 9:
                    System.out.println("flightconnection");
                    FlightconnectionService flightconnectionService = new FlightconnectionService(flightconnectionMySQLRepository);
                    FlightconnectionConsoleAdapter flightconnectionConsoleAdapter = new FlightconnectionConsoleAdapter(flightconnectionService);
                    flightconnectionConsoleAdapter.start();
                    break;
                case 10:
                    System.out.println("flightfare");
                    FlightfareService flightfareService = new FlightfareService(flightfareMySQLRepository);
                    FlightfareConsoleAdapter flightfareConsoleAdapter = new FlightfareConsoleAdapter(flightfareService);
                    flightfareConsoleAdapter.start();
                    break;
                case 11:
                    System.out.println("gate");
                    GateService gateService = new GateService(gateMySQLRepository);
                    GateConsoleAdapter gateConsoleAdapter = new GateConsoleAdapter(gateService);
                    gateConsoleAdapter.start();
                    break;
                case 12:
                    System.out.println("manufacture");
                    ManufactureService manufactureService = new ManufactureService(manufactureMySQLRepository);
                    ManufactureConsoleAdapter manufactureConsoleAdapter = new ManufactureConsoleAdapter(manufactureService);
                    manufactureConsoleAdapter.start();
                    break;
                case 13:
                    System.out.println("model");
                    ModelService modelService = new ModelService(modelMySQLRepository);
                    ModelConsoleAdapter modelConsoleAdapter = new ModelConsoleAdapter(modelService);
                    modelConsoleAdapter.start();
                    break;
                case 14:
                    System.out.println("plane");
                    PlaneService planeService = new PlaneService(planeMySQLRepository);
                    PlaneConsoleAdapter planeConsoleAdapter = new PlaneConsoleAdapter(planeService);
                    planeConsoleAdapter.start();
                    break;
                case 15:
                    System.out.println("revemployee");
                    RevemployeeService revemployeeService = new RevemployeeService(revemployeeMySQLRepository);
                    RevemployeeConsoleAdapter revemployeeConsoleAdapter = new RevemployeeConsoleAdapter(revemployeeService);
                    revemployeeConsoleAdapter.start();
                    break;
                case 16:
                    System.out.println("revision");
                    RevisionService revisionService = new RevisionService(revisionRepository);
                    RevisionConsoleAdapter revisionConsoleAdapter = new RevisionConsoleAdapter(revisionService);
                    revisionConsoleAdapter.start();
                    break;
                case 17:
                    System.out.println("revisiondetail");
                    RevisiondetailService revisiondetailService = new RevisiondetailService(revisiondetailRepository);
                    RevisiondetailConsoleAdapter revisiondetailConsoleAdapter = new RevisiondetailConsoleAdapter(revisiondetailService);
                    revisiondetailConsoleAdapter.start();
                    break;
                case 18:
                    System.out.println("status");
                    StatusService statusService = new StatusService(statusRepository);
                    StatusConsoleAdapter statusConsoleAdapter = new StatusConsoleAdapter(statusService);
                    statusConsoleAdapter.start();
                    break;
                case 19:
                    System.out.println("trip");
                    TripService tripService = new TripService(tripRepository);
                    TripConsoleAdapter tripConsoleAdapter = new TripConsoleAdapter(tripService);
                    tripConsoleAdapter.start();
                    break;
                case 20:
                    System.out.println("tripbooking");
                    TripbookingService tripbookingService = new TripbookingService(tripbookingRepository);
                    TripbookingConsoleAdapter tripbookingConsoleAdapter = new TripbookingConsoleAdapter(tripbookingService);
                    tripbookingConsoleAdapter.start();
                    break;
                case 21:
                    System.out.println("tripboTripbooking");
                    TripbookingdetailService tripbookingdetailService = new TripbookingdetailService(tripbookingdetailRepository);
                    TripbookingdetailConsoleAdapter tripbookingdetailConsoleAdapter = new TripbookingdetailConsoleAdapter(tripbookingdetailService);
                    tripbookingdetailConsoleAdapter.start();
                    break;
                case 22:
                    System.out.println("tripcrew");
                    TripcrewService tripcrewService = new TripcrewService(tripcrewRepository, employeeMySQLRepository, flightconnectionMySQLRepository);
                    TripcrewConsoleAdapter tripcrewConsoleAdapter = new TripcrewConsoleAdapter(tripcrewService);
                    tripcrewConsoleAdapter.start();
                    break;
                case 23:
                    System.out.println("tripulationrol");
                    TripulationrolService tripulationrolService = new TripulationrolService(tripulationrolRepository);
                    TripulationrolConsoleAdapter tripulationrolConsoleAdapter = new TripulationrolConsoleAdapter(tripulationrolService);
                    tripulationrolConsoleAdapter.start();
                    break;
                case 0:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcion invalida, intentelo de nuevo.");

        
                }
            }
    }
}

/*
 *  Crear una funcion para imprimir el menu y crear una funcion para majear el ingreso de los datos.
 * 
 */