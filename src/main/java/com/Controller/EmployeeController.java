package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Model.Employee;
import com.Repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		try {
			List<Employee> eList = new ArrayList<>();
			//
			employeeRepository.findAll().forEach(eList::add);
			
			
			if (eList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(eList, HttpStatus.OK);
			
		} catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Optional<Employee> employeeObj = employeeRepository.findById(id);
		if (employeeObj.isPresent()) {
			return new ResponseEntity<>(employeeObj.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping("/employees")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		try {
			if (employeeRepository.existsByEmployeeId(employee.getEmployeeId())) {
				
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			Employee employeeObj = employeeRepository.save(employee);
			return new ResponseEntity<>(employeeObj, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@PostMapping("/employees/{id}")
	public ResponseEntity<Employee> updateemployee(@PathVariable Long id, @RequestBody Employee employee) {
		try {
			Optional<Employee> employeeData = employeeRepository.findById(id);
			if (employeeData.isPresent()) {
				Employee updatedEmployeeData = employeeData.get();
				updatedEmployeeData.updateEmployee(employee);
				
				Employee employeeObj = employeeRepository.save(updatedEmployeeData);
				return new ResponseEntity<>(employeeObj, HttpStatus.CREATED);
				}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id) {
		try {
			Optional<Employee> employeeData = employeeRepository.findById(id);
			if (employeeData.isPresent()) {
				employeeRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}