package dev.group3.controllers;

import dev.group3.aspects.AuthorizedAssociate;
import dev.group3.aspects.AuthorizedTrainer;
import dev.group3.entities.Employee;
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

    //TODO Add logging and security aspects

    @CrossOrigin
    @PostMapping("/employee")
    public Employee registerEmployee(@RequestBody Employee employee){
        this.employeeService.registerEmployee(employee);
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setStatus(201);
        return employee;
    }

    @AuthorizedAssociate
    @GetMapping("/employee")
    public Set<Employee> getAllEmployees(){
        Set<Employee> employees = this.employeeService.getAllEmployees();
        return employees;
    }

    @AuthorizedAssociate
    @CrossOrigin
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

    @AuthorizedAssociate
    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) throws IOException {
        employee.setEmployeeId(id);
        Employee updatedEmployee = this.employeeService.updateEmployee(employee);
        if(updatedEmployee == null){
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            response.sendError(404, "Employee of ID " + id + " not found");
            return null;
        }else{
            return updatedEmployee;
        }
    }

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
