package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import com.Repository.EmployeeRepository;


@SpringBootApplication
//@EnableSwagger2
//@Import(SpringDataRestConfiguration.class)
public class H2JpaRestApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(H2JpaRestApplication.class, args);
		EmployeeRepository employeeRepository = configurableApplicationContext.getBean(EmployeeRepository.class);
	}

}


/*
{
	"employeeId": "A1",
    "firstName" : "Person",
    "lastName": "One",
    "jobTitle" : "Job 1", 
    "department" : "Department 1", 
    "managerReporting" : "Manager 1",
    "email" : "fellowTwo@email.com", 
    "phone" : "111-111-111", 
    "location" : "City A"
} 
*/