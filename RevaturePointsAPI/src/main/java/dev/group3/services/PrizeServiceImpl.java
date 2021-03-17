package dev.group3.services;

import dev.group3.entities.Prize;
import dev.group3.repos.PrizeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
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
<<<<<<< Updated upstream
        return this.prizeRepo.findById(id).get();
    }

=======
        try {
            Prize p = this.prizeRepo.findById(id).get();
            return p;
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return null;
        }
    }
>>>>>>> Stashed changes

    @Override
    public Set<Prize> getAllPrizes() {
        Set<Prize> prizes = new HashSet<>((Collection<? extends Prize>) this.prizeRepo.findAll());
        return prizes;
    }

    @Override
    public Prize updatePrize(Prize prize) {
        this.prizeRepo.save(prize);
        return prize;
    }

    @Override
    public boolean deletePrizeById(int id) {
        try {
            Prize prize = this.prizeRepo.findById(id).get(); // Throws exception if element does not exist
            this.prizeRepo.deleteById(id);
            return true;
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return false;
        }
    }
}
