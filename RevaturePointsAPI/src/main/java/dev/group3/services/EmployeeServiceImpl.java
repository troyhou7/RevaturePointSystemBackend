package dev.group3.services;

import dev.group3.entities.Employee;
import dev.group3.entities.Prize;
import dev.group3.repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;

@Component
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public Employee registerEmployee(Employee employee) {
        return null;
    }

    @Override
    public Employee getEmployeeById(int id) {
        return null;
    }

    @Override
    public Set<Employee> getEmployeesByBatch(int batchId) {
        return null;
    }

    @Override
    public Set<Employee> getAllEmployees() {
        return null;
    }

    public Set<Prize> getEmployeePrizes(int id) {
        return this.employeeRepo.findById(id).get().getPrizes();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        return false;
    }
}
