package dev.group3.controllers;


import dev.group3.entities.Employee;
import dev.group3.services.EmployeeService;
import dev.group3.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/login")
    String login(@RequestBody Employee employee) {
        Employee returnEmployee = this.employeeService.getEmployeeByUsername(employee.getUsername());
        if (returnEmployee!= null && returnEmployee.getPassword().equals(employee.getPassword())) {
            int id = returnEmployee.getEmployeeId();
            String firstName = returnEmployee.getFname();
            String lastName = returnEmployee.getLname();
            String role = returnEmployee.getRole();
            String jwt = JwtUtil.generate(id, firstName, lastName, role);
            return jwt;
        } else {
            return "User not found";
        }
    }

}
