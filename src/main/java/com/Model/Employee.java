package com.Model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

//import java.time.LocalDate;

import jakarta.persistence.*;

@Entity 
@Table(name="Employees")
public class Employee {
	/*
	 * No-arg constructor is required
	 * Primary key is also required. This is done through @Id and the strategy AUTO. 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private Long id;
	
	private String employeeId; 
	private String firstName; 
	private String lastName; 
	private String jobTitle; 
	private String department;
	private String managerReporting;
	private String email;
	private String phone;
	private String location;

	public Employee() {
	}
	
	/*
	public Employee(Employee employee) {
		this.employeeId = employee.getEmployeeId(); 
		this.firstName = employee.getFirstName(); 
		this.lastName = employee.getLastName(); 
		this.jobTitle = employee.getJobTitle(); 
		this.department = employee.getDepartment();
		this.managerReporting = employee.getManagerReporting();
		this.email = employee.getEmail();
		this.phone = employee.getPhone();
		this.location = employee.getLocation();
	}
	//*/
	
	public Employee(String employeeId, String firstName,
			String lastName, String jobTitle, 
			String department, String managerReporting, 
			String email, String phone, String location) { 
		this.employeeId = employeeId; 
		this.firstName = firstName; 
		this.lastName = lastName; 
		this.jobTitle = jobTitle; 
		this.department = department; 
		this.managerReporting = managerReporting; 
		this.email = email; 
		this.phone = phone; 
		this.location = location; 
	}
	
	public long getId() { 
		return this.id; 
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmployeeId() {
		return this.employeeId;
	}
	
	public String getJobTitle() {
		return this.jobTitle;
	}
	
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public String getDepartment() {
		return this.department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getManagerReporting() {
		return this.managerReporting;
	}
	
	public void setManagerReporting(String managerReporting) {
		this.managerReporting = managerReporting;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String toString(Employee employee) {
		return "id:" + employee.getId();
	}

	public void updateEmployee(Employee employee) {
		
		this.setJobTitle(employee.getJobTitle());
		this.setFirstName(employee.getFirstName());
		this.setLastName(employee.getLastName());
		this.setDepartment(employee.getDepartment());
		this.setManagerReporting(employee.getManagerReporting());
		this.setEmail(employee.getEmail());
		this.setPhone(employee.getPhone());
		this.setLocation(employee.getLocation());
		
	}
}

