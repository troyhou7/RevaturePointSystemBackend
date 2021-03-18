package dev.group3.serviceTests;

import dev.group3.RevaturePointsAPI.RevaturePointsApiApplication;
import dev.group3.entities.Employee;
import dev.group3.entities.Prize;
import dev.group3.repos.EmployeeRepo;
import dev.group3.services.EmployeeService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest(classes=RevaturePointsApiApplication.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTests {

    @Autowired
    EmployeeService employeeService;

    @MockBean
    EmployeeRepo employeeRepo;

    @BeforeEach
    void beforeAll() {
        Employee e1 = new Employee(1,"Associate", "Parker", "Hoskovec", "PHoskovec", "password", 0, 0, 1);
        Employee e2 = new Employee(1,"Associate", "Xianbin", "Zhen", "XZhen", "password", 0, 0, 1);
        Employee e3 = new Employee(1,"Associate", "Troy", "Houston", "THouston", "password", 0, 0, 2);

        Set<Employee> employees = new HashSet<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);

        Set<Employee> batch1 = new HashSet<>();
        batch1.add(e1);
        batch1.add(e2);

        Set<Employee> batch2 = new HashSet<>();
        batch2.add(e3);

        Mockito.when(employeeRepo.findAll()).thenReturn(employees);

        Mockito.when(employeeRepo.findById(1)).thenReturn(java.util.Optional.of(e1));
        Mockito.when(employeeRepo.findById(2)).thenReturn(java.util.Optional.of(e2));
        Mockito.when(employeeRepo.findById(3)).thenReturn(java.util.Optional.of(e3));

        Mockito.when(employeeRepo.findByBatchId(1)).thenReturn(batch1);
        Mockito.when(employeeRepo.findByBatchId(2)).thenReturn(batch2);

        //mocks the employeeRepo.save by saving the value to the mocked employees HashSet (in a lambda)
        Mockito.when(employeeRepo.save(Mockito.any(Employee.class)))
                .then(i -> {
                    Employee employee = (Employee) i.getArguments()[0];
                    employees.add(employee);
                    return employee;
                });

        //TODO Delete mocking
//        Mockito.when(employeeRepo.delete(Mockito.any(Employee.class)))
//                .then(i -> {
//                    return employees.remove(i.getArguments()[0]);
//                });

        Mockito.when(employeeRepo.findByUsernameAndPassword(anyString(), anyString()))
                .then(i -> {
                    String u = (String) i.getArguments()[0];
                    String p = (String) i.getArguments()[1];

                    if (u.equals("PHoskovec") && p.equals("password")) return e1;
                    if (u.equals("XZhen") && p.equals("password")) return e2;
                    if (u.equals("THouston") && p.equals("password")) return e3;
                    return null;
                });
    }

    @Test
    //make sure the test will run (checks for dependencies and correct annotations on the test class)
    void testTheTests() {
        Assertions.assertTrue(true);
    }

    @Test
    void registerEmployeeTest() {
        int batchsize1 = employeeRepo.findByBatchId(2).size();

        Employee mike = new Employee(0, "Associate", "Michael", "Bennett", "MBennett", "12345", 0, 0, 2);
        employeeRepo.save(mike);

        int batchsize2 = employeeRepo.findByBatchId(2).size();
        Assertions.assertEquals(1, batchsize2 -batchsize1);
    }

    @Test
    void getEmployeeByIdTest() {
        Employee e = employeeService.getEmployeeById(1);

        Assertions.assertNotNull(e);
        Assertions.assertEquals(1, e.getEmployeeId());
        Assertions.assertTrue(e.getFname().equals("Parker"));
    }

    @Test
    void getEmployeesByBatchTest() {
        Set<Employee> batch = employeeService.getEmployeesByBatch(1);

        Assertions.assertNotNull(batch);
        Assertions.assertEquals(2, batch.size());
    }

    @Test
    void getAllEmployeesTest() {
        Set<Employee> employees = employeeService.getAllEmployees();

        Assertions.assertNotNull(employees);
        Assertions.assertEquals(3, employees.size());
    }

    @Test
    void getEmployeePrizesTest() {
        //TODO
    }

    @Test
    void updateEmployeeTest() {
        Employee e = employeeService.getEmployeeById(1);

        e.setFname("Adam");

        Employee updated = employeeService.updateEmployee(e);

        Assertions.assertNotNull(updated);
        Assertions.assertTrue(updated.getFname().equals("Adam"));
    }

    @Test
    void deleteEmployeeByIdTest() {
        Assertions.assertTrue(employeeService.deleteEmployeeById(1));
        Assertions.assertNull(employeeService.getEmployeeById(1));
    }

    @Test
    void getEmployeeByUserPassTest() {
        Employee employee = employeeRepo.findByUsernameAndPassword("", "");

        Assertions.assertNull(employee);

        employee = employeeRepo.findByUsernameAndPassword("XZhen", "password");

        Assertions.assertNotNull(employee);
    }
}
