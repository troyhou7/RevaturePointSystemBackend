package dev.group3.controllers;


import dev.group3.aspects.Logged;
import dev.group3.entities.Employee;
import dev.group3.services.EmployeeService;
import dev.group3.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LoginController {

    @Autowired
    EmployeeService employeeService;

    @CrossOrigin
    @Logged
    @PostMapping("/login")
    String login(@RequestBody Employee employee) throws IOException {
        Employee returnEmployee = this.employeeService.getEmployeeByUsername(employee.getUsername());
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if (returnEmployee!= null){
            if(returnEmployee.getPassword().equals(employee.getPassword())) {
                int id = returnEmployee.getEmployeeId();
                String firstName = returnEmployee.getFname();
                String lastName = returnEmployee.getLname();
                String role = returnEmployee.getRole();
                String jwt = JwtUtil.generate(id, firstName, lastName, role);
                return jwt;
            } else{
                response.sendError(400, "Invalid password");
                return "Invalid password";
            }
        } else {
            response.sendError(404, "User: " + employee.getUsername() + " not found");
            return "User not found";
        }
    }

}
