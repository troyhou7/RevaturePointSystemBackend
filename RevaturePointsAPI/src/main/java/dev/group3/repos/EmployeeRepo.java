package dev.group3.repos;

import dev.group3.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Component
@Repository
public interface EmployeeRepo extends CrudRepository<Employee,Integer> {

    Set<Employee> findByBatchId(int batchId);
    Employee findEmployeeByUsername(String username);
    Employee findByUsernameAndPassword(String username, String password);
    Set<Employee> findByRole(String role);

}
