package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
