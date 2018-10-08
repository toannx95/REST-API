package com.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.api.dto.EmployeeDto;

public interface EmployeeService {

	List<EmployeeDto> getAllEmployees();

	EmployeeDto getEmployee(Integer employeeId);

	EmployeeDto createEmployee(EmployeeDto employeeDto);

	EmployeeDto updateEmployee(EmployeeDto employeeDto);

	void deleteEmployee(Integer employeeId);

	// search by name, birthday sex, departName
	Page<EmployeeDto> search(String employeeName, String sortField, String order, Pageable pageable);

}
