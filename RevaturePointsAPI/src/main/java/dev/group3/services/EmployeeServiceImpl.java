package dev.group3.services;

import dev.group3.entities.Employee;
import dev.group3.entities.Prize;
import dev.group3.repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Component
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public Employee registerEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        try{
            Employee employee = employeeRepo.findById(id).get();
            return employee;
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Employee> getEmployeesByBatch(int batchId) {
        Set<Employee> batch = employeeRepo.findByBatchId(batchId);
        return batch;
    }

    @Override
    public Set<Employee> getAllEmployees() {
        return new HashSet<Employee>((Collection<Employee>) employeeRepo.findAll());
    }

    @Override
    public Employee getEmployeeByUserPass(String username, String password) {
        return employeeRepo.findByUsernameAndPassword(username, password);
    }

    //TODO Add getEmployeeByUserPass

    @Override
    public Set<Prize> getEmployeePrizes(int id) {
        return this.employeeRepo.findById(id).get().getPrizes();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        boolean employeeExists = employeeRepo.existsById(employee.getEmployeeId());
        if(employeeExists) {
            return employeeRepo.save(employee);
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteEmployeeById(int id) {
        try {
            Employee employee = employeeRepo.findById(id).get();
            employeeRepo.delete(employee);
            return true;
        }
        catch (IllegalArgumentException e){
            //TODO do we need to logg this? Yes, we'll use an aspect
            e.printStackTrace();
            return false;
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Employee getEmployeeByUsername(String username) {
        try {
            Employee employee = employeeRepo.findEmployeeByUsername(username);
            return employee;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
