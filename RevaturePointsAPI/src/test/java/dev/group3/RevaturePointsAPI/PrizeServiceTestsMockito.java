package dev.group3.RevaturePointsAPI;

import dev.group3.entities.Prize;
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

import static org.mockito.Mockito.lenient;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PrizeServiceTestsMockito {

    @Autowired
    PrizeService prizeService;

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


        Mockito.when(prizeRepo.findAll()).thenReturn(prizes);

    }

    @Test
    void get_all_prizes(){
        Set<Prize> prizes = this.prizeService.getAllPrizes();
        System.out.println(prizes);
    }


}
