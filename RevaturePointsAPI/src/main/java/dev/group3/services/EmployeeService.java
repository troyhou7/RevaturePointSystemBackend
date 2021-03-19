package dev.group3.services;

import dev.group3.entities.Employee;
import dev.group3.entities.Prize;

import java.util.Set;

public interface EmployeeService {

    Employee registerEmployee(Employee employee);

    Employee getEmployeeById(int id);
    Set<Employee> getEmployeesByBatch(int batchId);
    Set<Employee> getAllEmployees();
    Employee getEmployeeByUserPass(String username, String password);
    Set<Employee> getEmployeesByRoll(String roll);

    Set<Prize> getEmployeePrizes(int id);

    Employee updateEmployee(Employee employee);

    boolean deleteEmployeeById(int id);

    Employee getEmployeeByUsername(String username);

}
