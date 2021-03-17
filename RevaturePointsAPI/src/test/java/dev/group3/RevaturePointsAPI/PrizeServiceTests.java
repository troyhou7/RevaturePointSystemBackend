package dev.group3.RevaturePointsAPI;

import dev.group3.entities.Prize;
<<<<<<< Updated upstream
import dev.group3.repos.PrizeRepo;
import dev.group3.services.PrizeService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
=======
import dev.group3.services.PrizeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
>>>>>>> Stashed changes
public class PrizeServiceTests {

    @Autowired
    PrizeService prizeService;

<<<<<<< Updated upstream
    @MockBean
    PrizeRepo prizeRepo;

    @BeforeEach
    void setUp(){
        Prize p1 = new Prize(1,"Trip",1000000,"Trip to Hawaii");
        Prize p2 = new Prize(2,"Candle",100,"A nice smelling candle");
        Prize p3 = new Prize(3,"TV",10000,"A 40 inch 4k TV!");
        Prize p4 = new Prize(4,"Car",200000,"Honda Civic");

        Set<Prize> prizes = new HashSet<>();

        prizes.add(p1);
        prizes.add(p2);
        prizes.add(p3);
        prizes.add(p4);

        System.out.println(prizeRepo);
        Mockito.when(prizeRepo.findAll()).thenReturn(prizes);
    }

    @Test
    void get_all_prizes(){
        Set<Prize> prizes = this.prizeService.getAllPrizes();
        System.out.println(prizes);
    }

=======
    static int prizeId;

    @Test
    @Order(1)
    void create_prize(){
        Prize prize = new Prize(0,"Ford Mustang", 1000000, "A brand new car for one hard working associate!");
        this.prizeService.createPrize(prize);

        prizeId = prize.getPrizeId();
        Assertions.assertNotEquals(0,prize.getPrizeId());
    }

    @Test
    @Order(2)
    void get_prize_by_id(){
        Prize prize = this.prizeService.getPrizeById(prizeId);
        System.out.println(prize);
    }

    @Test
    @Order(3)
    void update_prize(){
        Prize prize = this.prizeService.getPrizeById(prizeId);

        prize.setCost(2222222);
        Prize updatedPrize = this.prizeService.updatePrize(prize);

        Assertions.assertTrue(prizeId == updatedPrize.getPrizeId());
        Assertions.assertNotEquals(1000000,updatedPrize.getCost());
    }

    @Test
    @Order(4)
    void delete_prize(){
        boolean deleted = this.prizeService.deletePrizeById(prizeId);
        Assertions.assertTrue(deleted);
    }
>>>>>>> Stashed changes



}
