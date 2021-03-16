package dev.group3.RevaturePointsAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"dev.group3"})
@EnableJpaRepositories(basePackages = {"dev.group3.repos"})
@EntityScan(basePackages = {"dev.group3.entities"})
@SpringBootApplication
public class RevaturePointsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RevaturePointsApiApplication.class, args);
	}

}
