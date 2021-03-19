package dev.group3.controllers;

import dev.group3.aspects.AuthorizedAssociate;
import dev.group3.aspects.AuthorizedTrainer;
import dev.group3.entities.Prize;
import dev.group3.services.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Set;

@RestController
public class PrizeController {

    @Autowired
    PrizeService prizeService;

    @CrossOrigin
    @AuthorizedTrainer
    @PostMapping("/prize")
    Prize createPrize(@RequestBody Prize prize) {
        this.prizeService.createPrize(prize);
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setStatus(201);
        return prize;
    }

    @CrossOrigin
    @AuthorizedAssociate
    @GetMapping("/prize/{id}")
    Prize getPrizeById(@PathVariable int id) {
        Prize prize = this.prizeService.getPrizeById(id);
        return prize;
    }

    @CrossOrigin
    @AuthorizedAssociate
    @GetMapping("/prize")
    Set<Prize> getAllPrizes() {
        Set<Prize> prizeSet = this.prizeService.getAllPrizes();
        return prizeSet;
    }

    @CrossOrigin
    @AuthorizedTrainer
    @PostMapping("/prize/{id}")
    Prize updatePrize(@PathVariable int id, @RequestBody Prize prize) {
        prize.setPrizeId(id);
        this.prizeService.updatePrize(prize);
        return prize;
    }

    @CrossOrigin
    @AuthorizedTrainer
    @DeleteMapping("/prize/{id}")
    boolean deletePrizeById(@PathVariable int id) {
        Boolean result = this.prizeService.deletePrizeById(id);
        return result;
    }

}
