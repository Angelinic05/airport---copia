package com.campuslands.modules.employee.application;

import java.util.List;
import java.util.Optional;
import com.campuslands.modules.employee.domain.Employee;
import com.campuslands.modules.employee.infrastructure.EmployeeRepository;

public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService (EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee){
        employeeRepository.update(employee);
    }

    public Optional<Employee> findByIdEmployee(int id){
        return  employeeRepository.findById(id);
    }

    public void deleteEmployee(int id){
        employeeRepository.delete(id);
    }

    public List<Employee> findAllEmployee(){
        return employeeRepository.findAll();
    }

    public List<Employee> selectEmployee(){
        return employeeRepository.selectAvaliableEmployee();
    }
}
