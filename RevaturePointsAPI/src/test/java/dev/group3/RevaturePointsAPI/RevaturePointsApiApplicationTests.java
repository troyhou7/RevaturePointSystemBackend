package dev.group3.RevaturePointsAPI;

import dev.group3.entities.Employee;
import dev.group3.entities.Prize;
import dev.group3.repos.EmployeeRepo;
import dev.group3.repos.PrizeRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RevaturePointsApiApplicationTests {

	@Autowired
	PrizeRepo prizeRepo;

	@Autowired
	EmployeeRepo employeeRepo;

	@Test
	void create_employee(){

		Employee e1 = new Employee(0,"associate","John","Tester","jtest","password",10,10,0, "www.imageLocation.com");

		this.employeeRepo.save(e1);
	}

	@Test
	void create_prize(){

		Prize p1 = new Prize(0,"Vacation",100000,"Trip to Hawaii!", "www.imageLocation.com");

		this.prizeRepo.save(p1);
	}

	@Test
	void get_employee_by_id(){
		Employee e1 = this.employeeRepo.findById(1).get();
		System.out.println(e1.getPrizes());
	}

	@Test
	void give_employee_prize(){
		Prize p1 = this.prizeRepo.findById(1).get();
		Employee e1 = this.employeeRepo.findById(1).get();
		e1.getPrizes().add(p1);

		this.employeeRepo.save(e1);

		// Should be saved in join table in database
		System.out.println(this.employeeRepo.findById(1).get().getPrizes());
	}

}
