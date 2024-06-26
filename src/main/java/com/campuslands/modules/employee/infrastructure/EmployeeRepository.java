package com.campuslands.modules.employee.infrastructure;

import java.util.List;
import java.util.Optional;

import com.campuslands.modules.employee.domain.Employee;

public interface EmployeeRepository {
    void save(Employee employee);
    void update(Employee employee);
    Optional<Employee> findById(int id);
    void delete(int id);
    List<Employee> findAll();
    List<Employee> selectAvaliableEmployee();
}
