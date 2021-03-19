package dev.group3.controllers;


import dev.group3.aspects.Logged;
import dev.group3.entities.Employee;
import dev.group3.models.UserAndPassword;
import dev.group3.models.UserAuthentication;
import dev.group3.services.EmployeeService;
import dev.group3.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LoginController {

    @Autowired
    EmployeeService employeeService;

    @CrossOrigin
    @Logged
    @PostMapping("/login")
    UserAuthentication login(@RequestBody UserAndPassword user) {
        String username = user.getUsername();
        Employee returnEmployee = this.employeeService.getEmployeeByUsername(username);
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        if (returnEmployee!= null) {
            if (returnEmployee.getPassword().equals(user.getPassword())) {
                int id = returnEmployee.getEmployeeId();
                String firstName = returnEmployee.getFname();
                String lastName = returnEmployee.getLname();
                String role = returnEmployee.getRole();
                String jwt = JwtUtil.generate(id, firstName, lastName, role);
                Cookie cookie = new Cookie("JWT", jwt);
                response.addCookie(cookie);
                response.setStatus(200);
                UserAuthentication userAuthentication = new UserAuthentication(id, role, firstName, lastName, username, jwt);
                return userAuthentication;
            } else {
                try {
                    response.sendError(400, "Incorrect username or password.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        } else {
            try {
                response.sendError(404, "User not found.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
