package com.example.H2JPAREST;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.Controller.EmployeeController;
import com.Model.Employee;
import com.Repository.EmployeeRepository;

@SpringBootTest
class H2JpaRestApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private EmployeeController ec = new EmployeeController();
	
	// JUnit for adding employees to the directory
	@Test
	public void addGetRemoveEmployeeTest() {
		Employee employee = new Employee(
				"A1", "James",
				"Smith", "Cashier", 
				"Department 1", "Manager 1", 
				"jamessmith@gmail.com", "123-123-1234", "Location 1"
				);
		
		/**
		 * Add, Get, then Remove Employee
		 * Get an empty List. 
		 */
		ResponseEntity<Employee> responsePost = ec.addEmployee(employee);
		assertEquals(HttpStatus.CREATED, responsePost.getStatusCode()); 
		
		ResponseEntity<List<Employee>> responseGet = ec.getAllEmployees();
		assertEquals(employee.getId(), responseGet.getBody().get(0).getId()); 
		
		ResponseEntity<HttpStatus> responseDelete= ec.deleteEmployee(employee.getId());
		assertEquals(HttpStatus.OK, responseDelete.getStatusCode()); 
		
		responseGet = ec.getAllEmployees();
		System.out.println(responseGet.getStatusCode());
		assertEquals(HttpStatus.NO_CONTENT, responseGet.getStatusCode()); 
		
		
	}
	
	@Test
	public void addEmployeeManyTest() {
		/**
		 * Add, Get, then Remove MultipleEmployee
		 */
		
		Employee employee = new Employee(
				"A1", "James",
				"Smith", "Cashier", 
				"Department 1", "Manager 1", 
				"jamessmith@gmail.com", "123-123-1234", "Location 1"
				);
		
		Employee employee2 = new Employee(
				"A2", "Jonathon",
				"Smith", "Secretary", 
				"Department 1", "Manager 1", 
				"jonathonSmith@gmail.com", "123-123-1235", "Location 1"
				);
		
		Employee employee3 = new Employee(
				"A3", "Jack",
				"Smith", "Secretary", 
				"Department 1", "Manager 1", 
				"jonathonSmith@gmail.com", "123-123-1235", "Location 1"
				);
		
		//Add tests
		ResponseEntity<Employee> response1 = ec.addEmployee(employee);
		assertEquals(HttpStatus.CREATED, response1.getStatusCode()); 

		ResponseEntity<Employee> response2 = ec.addEmployee(employee2);
		assertEquals(HttpStatus.CREATED, response2.getStatusCode()); 
		
		ResponseEntity<Employee> response3 = ec.addEmployee(employee3);
		assertEquals(HttpStatus.CREATED, response3.getStatusCode()); 
		
		
		//Get tests
		ResponseEntity<List<Employee>> responseGet = ec.getAllEmployees();
		assertEquals(HttpStatus.OK, responseGet.getStatusCode()); 
		
		
		ResponseEntity<Employee> responseGetID1 = ec.getEmployeeById(employee.getId());
		assertEquals(HttpStatus.OK, responseGetID1.getStatusCode()); 
		
		ResponseEntity<Employee> responseGetID2 = ec.getEmployeeById(employee2.getId());
		assertEquals(HttpStatus.OK, responseGetID2.getStatusCode()); 
		
		ResponseEntity<Employee> responseGetID3 = ec.getEmployeeById(employee3.getId());
		assertEquals(HttpStatus.OK, responseGetID3.getStatusCode()); 
		
		
		
		//Delete tests
		ResponseEntity<HttpStatus> responseDelete= ec.deleteEmployee(employee.getId());
		assertEquals(HttpStatus.OK, responseDelete.getStatusCode()); 
		
		ResponseEntity<HttpStatus> responseDelete2= ec.deleteEmployee(employee2.getId());
		assertEquals(HttpStatus.OK, responseDelete.getStatusCode()); 
		
		ResponseEntity<HttpStatus> responseDelete3= ec.deleteEmployee(employee3.getId());
		assertEquals(HttpStatus.OK, responseDelete.getStatusCode()); 
		

		//Check that list is empty
		ResponseEntity<List<Employee>> responseGet1 = ec.getAllEmployees();
		assertEquals(HttpStatus.NO_CONTENT, responseGet1.getStatusCode()); 
		
		
	}
	@Test
	public void getEmpty() {
		ResponseEntity<List<Employee>> responseGet = ec.getAllEmployees();
		assertEquals(HttpStatus.NO_CONTENT, responseGet.getStatusCode());
	}
	
	@Test
	public void addEmployeeCollisionTest() { 
		Employee employee = new Employee(
				"A1", "James",
				"Smith", "Cashier", 
				"Department 1", "Manager 1", 
				"jamessmith@gmail.com", "123-123-1234", "Location 1"
				);
		
		Employee employee2 = new Employee(
				"A1", "Jonathon",
				"Smith", "Secretary", 
				"Department 1", "Manager 1", 
				"jonathonSmith@gmail.com", "123-123-1235", "Location 1"
				);
		
		ResponseEntity<Employee> r1 = ec.addEmployee(employee);
		assertEquals(HttpStatus.CREATED, r1.getStatusCode()); 
		
		ResponseEntity<Employee> r2 = ec.addEmployee(employee);
		assertEquals(HttpStatus.UNAUTHORIZED, r2.getStatusCode()); 
		
		ResponseEntity<HttpStatus> responseDelete= ec.deleteEmployee(employee.getId());
		assertEquals(HttpStatus.OK, responseDelete.getStatusCode()); 
	}
	
	@Test
	public void removeTest() {
		Employee employee = new Employee(
				"A1", "James",
				"Smith", "Cashier", 
				"Department 1", "Manager 1", 
				"jamessmith@gmail.com", "123-123-1234", "Location 1"
				);
		
		ResponseEntity<Employee> r1 = ec.addEmployee(employee);
		assertEquals(HttpStatus.CREATED, r1.getStatusCode()); 
		
		ResponseEntity<HttpStatus> responseDelete= ec.deleteEmployee(employee.getId());
		assertEquals(HttpStatus.OK, responseDelete.getStatusCode()); 
		
		ResponseEntity<HttpStatus> responseDelete2= ec.deleteEmployee(employee.getId());
		assertEquals(HttpStatus.NOT_FOUND, responseDelete2.getStatusCode()); 
		
	}
	
	@Test
	public void getByIdTest() {
		Employee employee = new Employee(
				"A1", "James",
				"Smith", "Cashier", 
				"Department 1", "Manager 1", 
				"jamessmith@gmail.com", "123-123-1234", "Location 1"
				);
		
		
		ResponseEntity<Employee> responseGetID1 = ec.getEmployeeById(0l);
		assertEquals(HttpStatus.NOT_FOUND, responseGetID1.getStatusCode()); 
		
		ResponseEntity<Employee> r1 = ec.addEmployee(employee);
		assertEquals(HttpStatus.CREATED, r1.getStatusCode()); 
		
		ResponseEntity<Employee> responseGetID2 = ec.getEmployeeById(employee.getId());
		assertEquals(HttpStatus.OK, responseGetID2.getStatusCode()); 
		
		ResponseEntity<HttpStatus> responseDelete= ec.deleteEmployee(employee.getId());
		assertEquals(HttpStatus.OK, responseDelete.getStatusCode()); 
		
		
	}

}
