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
        Set<Employee> employees = new HashSet<>((Collection<? extends Employee>) this.employeeRepo.findAll());
        return employees;
    }


    @Logged
    @Override
    public Employee getEmployeeByUserPass(String username, String password) {
        return employeeRepo.findByUsernameAndPassword(username, password);
    }

    @Override
    public Set<Employee> getEmployeesByRole(String role) {
        return employeeRepo.findByRole(role);
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

    // Helper function to check if current prize is the New prize being added to the employee prize set
    private boolean checkPrizeId(int id, Set<Prize> prizes){
        for(Prize p: prizes){
            if(p.getPrizeId() == id){
                return false;
            }
        }
        return true;
    }

    @Logged
    @Override
    public Employee updateEmployee(Employee employee) {
        try{
            Employee employeeExists = employeeRepo.findById(employee.getEmployeeId()).get();
            if(employeeExists.getPrizes().size() != employee.getPrizes().size()) {
                Set<Prize> existingPrizes = employeeExists.getPrizes();
                Set<Prize> updatedPrizes = employee.getPrizes();

                for(Prize p : updatedPrizes){
                    boolean isNewPrize = checkPrizeId(p.getPrizeId(),existingPrizes);
                    if(isNewPrize){
                        int currentPoints = employeeExists.getCurrentRevaPoints();
                        if(currentPoints >= p.getCost()){
                            employee.setCurrentRevaPoints(currentPoints - p.getCost());
                            employeeExists.setCurrentRevaPoints(currentPoints - p.getCost());
                        }else{ // Return existing employee if employee does not have enough points
                            return employeeExists;
                        }
                    }
                }
            }
            return employeeRepo.save(employee);
            } catch (NoSuchElementException e){
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

    @Logged
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
