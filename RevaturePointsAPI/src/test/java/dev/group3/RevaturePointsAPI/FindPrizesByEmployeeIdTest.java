package dev.group3.RevaturePointsAPI;

import dev.group3.entities.Prize;
import dev.group3.repos.PrizeRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Set;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FindPrizesByEmployeeIdTest {

    @Autowired
    PrizeRepo prizeRepo;

    @Test
    @Order(1)
    @Commit
    void create_prizes(){
        Prize p1 = new Prize(0,"Trip",100000,"Trip to Hawaii");
        Prize p2 = new Prize(0,"Trip",100000,"Trip to Hawaii");
        Prize p3 = new Prize(0,"Trip",100000,"Trip to Hawaii");
        Prize p4 = new Prize(0,"Car",200000,"Honda Civic");

        prizeRepo.save(p1);
        prizeRepo.save(p2);
        prizeRepo.save(p3);
        prizeRepo.save(p4);

    }


    @Test
    @Order(2)
    @Rollback
    void find_prize_by_employee_id() {
        Set<Prize> prizes = prizeRepo.findPrizesByEmployeeId(1);
        System.out.println(prizes);
    }


}
