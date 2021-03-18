package dev.group3.services;

import dev.group3.aspects.Logged;
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

    @Logged
    @Override
    public Employee registerEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Logged
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

    @Logged
    @Override
    public Set<Employee> getEmployeesByBatch(int batchId) {
        Set<Employee> batch = employeeRepo.findByBatchId(batchId);
        return batch;
    }

    @Logged
    @Override
    public Set<Employee> getAllEmployees() {
        return new HashSet<Employee>((Collection<Employee>) employeeRepo.findAll());
    }


    @Logged
    @Override
    public Set<Prize> getEmployeePrizes(int id) {
        try{
            Set<Prize> prizes = this.employeeRepo.findById(id).get().getPrizes();
            return prizes;
        }catch(NoSuchElementException e){
            e.printStackTrace();
            return null;
        }
    }

    @Logged
    @Override
    public Employee updateEmployee(Employee employee) {
        try{
            Employee employeeExists = employeeRepo.findById(employee.getEmployeeId()).get();
            return employeeRepo.save(employee);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return null;
        }
    }

    @Logged
    @Override
    public boolean deleteEmployeeById(int id) {
        try {
            Employee employee = employeeRepo.findById(id).get();
            employeeRepo.delete(employee);
            return true;
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return false;
        }
    }
}
