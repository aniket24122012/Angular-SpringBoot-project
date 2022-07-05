package com.gl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.exception.ResourceNotFoundException;
import com.gl.model.Employee;
import com.gl.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//get all employee
	@GetMapping("/employees")
	public List<Employee> getAllEmployee(){
		return employeeRepository.findAll();
	}
	
	//create employee rest api
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	//get employee through id
	
    @GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
    	
    	Employee employee = employeeRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Employee not found" +id));
    	return ResponseEntity.ok(employee);
		
	}
    
    //update employee 
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeUpdate) {
		
    	Employee employee = employeeRepository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Employee not found" +id));
    	employee.setFirstName(employeeUpdate.getFirstName());
    	employee.setLastName(employeeUpdate.getLastName());
    	employee.setEmailId(employeeUpdate.getEmailId());
    	
    	Employee updateEmployeeObejct = employeeRepository.save(employee);
		return ResponseEntity.ok(updateEmployeeObejct);
    }
		
		//delete employee rest api
		
//		@DeleteMapping("employees/{id}")
//		public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
//			Employee employee = employeeRepository.findById(id)
//	    			.orElseThrow(() -> new ResourceNotFoundException("Employee not found" +id));
//			employeeRepository.delete(employee);
//			
//			Map<String , Boolean> response = new HashMap<>();
//			
//			response.put("delete",Boolean.TRUE);
//			
//			
//			
//			return ResponseEntity.ok(response);
//	
//		
//	}
        @DeleteMapping("/employees/{id}")
		public void deleteEmployee(@PathVariable Long id){
        	Employee employee = employeeRepository.findById(id)
	    			.orElseThrow(() -> new ResourceNotFoundException("Employee not found" +id));
			employeeRepository.delete(employee);
//			
//			Map<String , Boolean> response = new HashMap<>();
//			response.put("delete",Boolean.TRUE);
//			return ResponseEntity.ok(response);
			
		}
    	
   
	}
	


