package dev.group3.RevaturePointsAPI;

import dev.group3.entities.Prize;

import dev.group3.repos.PrizeRepo;
import dev.group3.services.PrizeService;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.HashSet;
import java.util.Set;

import dev.group3.services.PrizeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PrizeServiceTests {

    @Autowired
    PrizeService prizeService;

    static int prizeId;

    @Test
    @Order(1)
    void create_prize(){
        Prize prize = new Prize(0,"Ford Mustang", 1000000, "A brand new car for one hard working associate!","x");
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

}
