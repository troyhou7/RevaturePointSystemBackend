package dev.group3.controllers;

import dev.group3.entities.Prize;
import dev.group3.services.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class PrizeController {

    @Autowired
    PrizeService prizeService;

    @PostMapping("/prize")
    Prize createPrize(@RequestBody Prize prize) {
        this.prizeService.createPrize(prize);
        return prize;
    }

    @GetMapping("/prize/{id}")
    Prize getPrizeById(@PathVariable int id) {
        Prize prize = this.prizeService.getPrizeById(id);
        return prize;
    }

    @GetMapping("/prize")
    Set<Prize> getAllPrizes() {
        Set<Prize> prizeSet = this.prizeService.getAllPrizes();
        return prizeSet;
    }

    @PostMapping("/prize/{id}")
    Prize updatePrize(@PathVariable int id, @RequestBody Prize prize) {
        prize.setPrizeId(id);
        this.prizeService.updatePrize(prize);
        return prize;
    }

    @DeleteMapping("/prize/{id}")
    boolean deletePrizeById(@PathVariable int id) {
        Boolean result = this.prizeService.deletePrizeById(id);
        return result;
    }


}
