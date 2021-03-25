package dev.group3.controllers;

import dev.group3.aspects.AuthorizedAssociate;
import dev.group3.aspects.AuthorizedTrainer;
import dev.group3.entities.Employee;
import dev.group3.entities.Prize;
import dev.group3.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @CrossOrigin
    @PostMapping("/employee")
    public Employee registerEmployee(@RequestBody Employee employee) throws IOException {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        try {
            this.employeeService.registerEmployee(employee);
            response.setStatus(201);
            return employee;
        } catch (Exception e) {
            response.sendError(400, "Register failed");
            return null;
        }
    }

    @CrossOrigin
    @AuthorizedAssociate
    @GetMapping("/employee")
    public Set<Employee> getAllEmployees(){
        Set<Employee> employees = this.employeeService.getAllEmployees();
        return employees;
    }

    @CrossOrigin
    @AuthorizedAssociate
    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable int id) throws IOException {
        Employee employee = this.employeeService.getEmployeeById(id);
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if(employee != null){
            return employee;
        }
        else{
            response.sendError(404,"Employee of ID " + id + " not found");
            return null;
        }
    }

    @CrossOrigin
    @AuthorizedAssociate
    @GetMapping("/batch/{id}")
    public Set<Employee> getEmployeesByBatchId(@PathVariable int id) throws IOException {
        Set<Employee> employees = this.employeeService.getEmployeesByBatch(id);
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if(employees.size() == 0){
            response.sendError(404, "No employees in batch id " + id);
        }
        return employees;
    }

    @CrossOrigin
    @AuthorizedAssociate
    @GetMapping("/role/{role}")
    public Set<Employee> getEmployeesByRole(@PathVariable String role) throws IOException {
        Set<Employee> employees = this.employeeService.getEmployeesByRole(role);
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if(employees.size() == 0){
            response.sendError(404, "No employees with role " + role);
        }
        return employees;
    }
    
    @CrossOrigin
    @AuthorizedAssociate
    @GetMapping("/employee/{id}/prizes")
    public Set<Prize> getEmployeePrizes(@PathVariable int id) throws IOException {
        Set<Prize> prizes = this.employeeService.getEmployeePrizes(id);
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if(prizes != null) {
            if (prizes.size() == 0) {
                response.sendError(404, "This employee has no prizes");
            }
            return prizes;
        }
        response.sendError(404, "Employee not found");
        return null;
    }

    @CrossOrigin
    @AuthorizedAssociate
    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) throws IOException {
        employee.setEmployeeId(id);
        Employee updatedEmployee = this.employeeService.updateEmployee(employee);
        if(updatedEmployee == null){
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            response.sendError(404, "Employee of ID " + id + " not found");
            return null;
        }else if(updatedEmployee.getPrizes().size() != employee.getPrizes().size()){
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            response.sendError(400, "Employee " + id + "does not have enough points to redeem that prize");
        }
        return updatedEmployee;
    }

    @CrossOrigin
    @AuthorizedTrainer
    @DeleteMapping("/employee/{id}")
    public Boolean deleteEmployeeById(@PathVariable int id) throws IOException {
        Boolean result = this.employeeService.deleteEmployeeById(id);
        if(!result){
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            response.sendError(404, "Employee of ID " + id + " not found");
        }
        return result;
    }
}
