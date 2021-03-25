package dev.group3.serviceTests;

import dev.group3.RevaturePointsAPI.RevaturePointsApiApplication;
import dev.group3.entities.Employee;
import dev.group3.entities.Prize;
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

        testEmployee = new Employee(0,"Associate", "Parker", "Hoskovec", "agasdsahash", "password", 128, 0, 1,"x");

        employeeService.registerEmployee(testEmployee);

        Assertions.assertNotEquals(0, testEmployee);
    }

    @Test
    @Order(2)
    void updateEmployeeTest() {
        testEmployee.setFname("TestName");
        testEmployee.getPrizes().add(new Prize(28,"1 Million Dollars",10000000,"Cash Money","x"));

        Employee updated = employeeService.updateEmployee(testEmployee);

        updated.getPrizes().add(new Prize(0,"New Prize",100,"Prize","x"));

        updated = employeeService.updateEmployee(updated);

        updated.getPrizes().add(new Prize(0,"New Prize2",100,"Prize","x"));

        updated = employeeService.updateEmployee(updated);

        updated.getPrizes().add(new Prize(0,"New Prize3",20,"Prize","x"));

        updated = employeeService.updateEmployee(updated);

        Assertions.assertEquals(2,updated.getPrizes().size());
        Assertions.assertNotNull(updated);
        //Assertions.assertTrue(updated.getFname().equals("TestName"));
    }

    @Test
    @Order(3)
    void deleteEmployeeByIdTest() {
        Assertions.assertTrue(employeeService.deleteEmployeeById(testEmployee.getEmployeeId()));

        Employee employee = employeeService.getEmployeeById(testEmployee.getEmployeeId());

        Assertions.assertNull(employee);
    }
}
