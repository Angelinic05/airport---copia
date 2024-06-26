package com.campuslands.modules.city.adapter.in;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.campuslands.modules.city.application.CityService;
import com.campuslands.modules.city.domain.City;

public class CityConsoleAdapter {
    
    private CityService cityService;

    public CityConsoleAdapter(CityService cityService) {
        this.cityService = cityService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int id;
        String name;
        int idCountry;
        while (true) {
            int choice = menu(scanner);
            
            switch (choice) {
                case 1:
                    List<City> cities = cityService.findAllCities();
                    cities.forEach(System.out::println);
                    break;
                case 2:;
                    System.out.print("Ingrese el nombre de la ciudad: ");
                    name = scanner.nextLine();
                    System.out.print("Ingrese el ID del pais: ");
                    idCountry = scanner.nextInt();
                    scanner.nextLine();
                    City city = new City(name, idCountry);
                    cityService.saveCity(city);
                    break;
                case 3:
                    System.out.println("Ingrese el id del pais a actualizar");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    Optional <City> ciyToUpdate = cityService.findtCityById(idToUpdate);
                    ciyToUpdate.ifPresentOrElse(updatedCity  ->{
                        int optSubMenu = -1;
                        String submenu = "¿Qué desea actualizar?\n1. Nombre de la ciudad\n2. id del pais\n0.Salir\n";
                
                        while (optSubMenu != 0) {
                            System.out.println(submenu);
                            optSubMenu = Integer.parseInt(scanner.nextLine());
                
                            switch (optSubMenu) {
                                case 1:
                                    System.out.print("Ingrese el nombre de la ciudad: ");
                                    String nameUpdate = scanner.nextLine();
                                    updatedCity.setName(nameUpdate);
                                    break;
                                case 2:
                                    System.out.print("Ingrese el id del pais: ");
                                    int idCountryUpdate = Integer.parseInt(scanner.nextLine());
                                    updatedCity.setIdCountry(idCountryUpdate);
                                    break;
                            }
                        }
                        cityService.updateCity(updatedCity);
                    }, () -> System.out.println("No se encontro el id" + idToUpdate));
                    break;
                case 4:
                    System.out.print("Ingrese el ID de la ciudad a eliminar: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    cityService.deleteCity(id);
                    break;
                case 5:
                    System.out.print("Ingrese el ID de la ciudad a buscar: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    cityService.findtCityById(id).ifPresent(System.out::println);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Ingrese una opcion valida (1 - 5).");
            }
        }
    }

    public int menu(Scanner scanner) {
        System.out.println("\nMenu:");
        System.out.println("1. Mostrar todas las ciudades");
        System.out.println("2. Agregar una nueva ciudad");
        System.out.println("3. Actualizar una ciudad");
        System.out.println("4. Eliminar una ciudad");
        System.out.println("5. Buscar una ciudad por id");
        System.out.println("0. Salir");
        System.out.println("");
        System.out.print("Ingrese una opcion: ");
        int choice = -1;
        while (choice < 0 || choice > 5) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice > 5) {                    
                    System.out.println("Ingrese una opcion valida (1 - 5).");
                }
            } catch (Exception e) {
                System.out.println("Ingrese un numero entero.");
            }
        }
        return choice;
    }
}
