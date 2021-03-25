package dev.group3.serviceTests;

import dev.group3.RevaturePointsAPI.RevaturePointsApiApplication;
import dev.group3.entities.Employee;
import dev.group3.services.EmployeeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest(classes= RevaturePointsApiApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceCreateDeleteTests {

    @Autowired
    EmployeeService employeeService;

    static Employee testEmployee;

    @Test
    @Order(1)
    void registerEmployeeTest() {
        testEmployee = new Employee(0,"Associate", "Parker", "Hoskovec", "PHoskovec", "password", 0, 0, 1, "www.imageLocation.com");

        employeeService.registerEmployee(testEmployee);

        Assertions.assertNotEquals(0, testEmployee);
    }

    @Test
    @Order(2)
    void updateEmployeeTest() {
        testEmployee.setFname("TestName");

        Employee updated = employeeService.updateEmployee(testEmployee);

        Assertions.assertNotNull(updated);
        Assertions.assertTrue(updated.getFname().equals("TestName"));
    }

    @Test
    @Order(3)
    void deleteEmployeeByIdTest() {
        Assertions.assertTrue(employeeService.deleteEmployeeById(testEmployee.getEmployeeId()));

        Employee employee = employeeService.getEmployeeById(testEmployee.getEmployeeId());

        Assertions.assertNull(employee);
    }
}
