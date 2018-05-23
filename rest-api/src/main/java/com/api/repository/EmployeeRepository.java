package com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.entity.Employee;
import com.api.exception.BadRequestException;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query(value = "select * from employee where department_id = 1?", nativeQuery = true)
	List<Employee> findAllByDepartmentId(Integer departmentId) throws BadRequestException;

	@Query(value = "select * from department where department_id = ?1 and employee_id = ?2", nativeQuery = true)
	Employee findOneByDepartmentId(Integer departmentId, Integer employeeId) throws BadRequestException;

}
