package dev.group3.repos;

import dev.group3.entities.Prize;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Component
@Repository
public interface PrizeRepo extends CrudRepository<Prize,Integer> {

    Set<Prize> findPrizesByEmployeeId(int employeeId);
}
