package dev.group3.services;

import dev.group3.entities.Employee;

import java.util.Set;

public interface EmployeeService {

    Employee registerEmployee(Employee employee);

    Employee getEmployeeById(int id);
    Set<Employee> getEmployeesByBatch(int batchId);
    Set<Employee> getAllEmployees();

    Employee updateEmployee(Employee employee);

    boolean deleteEmployeeById(int id);

}
