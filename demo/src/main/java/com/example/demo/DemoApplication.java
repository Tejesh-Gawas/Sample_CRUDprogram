package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
    @Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public void run(String... args) throws Exception {
       Employee employee = new Employee();
	   employee.setFirstName("Tejesh");
	   employee.setLastName("Gavas");
	   employee.setEmailId("tejesh.gavas@springernature.com");
	   employeeRepository.save(employee);


		Employee employee1 = new Employee();
		employee1.setFirstName("Trishal");
		employee1.setLastName("Mandrik");
		employee1.setEmailId("trishal.mandrik@springernature.com");
		employeeRepository.save(employee1);
	}
}
