package dev.group3.RevaturePointsAPI;

import dev.group3.entities.Prize;
import dev.group3.repos.EmployeeRepo;
import dev.group3.repos.PrizeRepo;
import dev.group3.services.EmployeeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Set;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeServiceTests {

    @Autowired
    EmployeeService employeeService;


    @Test
    void get_prizes_by_employee_id(){
        Set<Prize> prizes = this.employeeService.getEmployeePrizes(1);
        System.out.println(prizes);

    }



}
