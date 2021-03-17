package dev.group3.services;

import dev.group3.entities.Prize;
import dev.group3.repos.PrizeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
@Service
public class PrizeServiceImpl implements PrizeService{

    @Autowired
    PrizeRepo prizeRepo;

    @Override
    public Prize createPrize(Prize prize) {
        this.prizeRepo.save(prize);
        return prize;
    }

    @Override
    public Prize getPrizeById(int id) {
        return this.prizeRepo.findById(id).get();
    }


    @Override
    public Set<Prize> getAllPrizes() {
        Set<Prize> prizes = new HashSet<>((Collection<? extends Prize>) this.prizeRepo.findAll());
        return prizes;
    }

    @Override
    public Prize updatePrize(Prize prize) {
        return null;
    }

    @Override
    public boolean deletePrizeById(int id) {
        return false;
    }
}
