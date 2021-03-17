package dev.group3.services;

import dev.group3.entities.Prize;

import java.util.Set;

public interface PrizeService {

    Prize createPrize(Prize prize);

    Prize getPrizeById(int id);
    Set<Prize> getAllPrizes();

    Prize updatePrize(Prize prize);

    boolean deletePrizeById(int id);


}
